package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.model.Hecho.Hecho;
import ar.edu.utn.frba.dds.model.Multimedia.ArchivoMultimedia;
import ar.edu.utn.frba.dds.model.criterio.Categoria;
import ar.edu.utn.frba.dds.repositorios.RepoArchivosMultimedia;
import ar.edu.utn.frba.dds.repositorios.RepoHechosDinamicos;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;

public class HechoController {

    public void formHechoNuevo(Context ctx){
        Map<String, Object> model = new HashMap<>();
        // Sugerencia: si necesitás selects (categorías, provincias), cargalos acá
        model.put("categorias", Arrays.asList("INCENDIO_FORESTAL", "HOMICIDOS_DOLOSOS", "ACCIDENTE_VIAL"));
        model.put("values", Collections.emptyMap());
        model.put("errors", Collections.emptyList());
        ctx.render("hecho/hecho.nuevo.hbs", model);
    }

    public void crearHecho(Context ctx){
        try {

            List<String> errors = new ArrayList<>();

            String titulo = ctx.formParam("titulo");
            String descripcion = ctx.formParam("descripcion");
            String categoriaStr = ctx.formParam("categoria");
            String latitud = ctx.formParam("latitud");
            String longitud = ctx.formParam("longitud");
            String fechaStr = ctx.formParam("fecha");
            List<UploadedFile> archivos = ctx.uploadedFiles("multimedia[]");

            if (titulo.isBlank()) {
                errors.add("El título es obligatorio.");
            }
            if (descripcion.isBlank()) {
                errors.add("La descripción es obligatoria.");
            }
            if (categoriaStr.isBlank()){
                errors.add("La categoría es obligatoria.");
            }
            if (fechaStr.isBlank()) {
                errors.add("La fecha del hecho es obligatoria.");
            }

            Categoria categoria = null;
            if(!categoriaStr.isBlank()){
                try {
                    categoria = Categoria.valueOf(categoriaStr.toUpperCase());
                } catch (IllegalArgumentException e) {
                    errors.add("Categoría inválida: " + categoriaStr);
                }
            }

            LocalDateTime fechaHecho = null;
            try {
                fechaHecho = LocalDateTime.parse(fechaStr);
            } catch (Exception e) {
                    errors.add("Fecha del hecho inválida.");
            }

            if (!errors.isEmpty()) {
                Map<String,Object> model = new HashMap<>();
                Map<String,String> values = new HashMap<>();
                values.put("titulo", titulo);
                values.put("descripcion", descripcion);
                values.put("categoria", categoriaStr);
                values.put("latitud", latitud);
                values.put("longitud", longitud);
                values.put("fecha", fechaStr);

                model.put("categorias", Arrays.asList("INCENDIO_FORESTAL", "HOMICIDOS_DOLOSOS", "ACCIDENTE_VIAL"));
                model.put("values", values);
                model.put("errors", errors);

                ctx.status(400).render("hecho/hecho.nuevo.hbs", model);
                return;
            }

            Hecho hecho = new Hecho(
                    titulo,
                    descripcion,
                    categoria,
                    latitud,
                    longitud,
                    fechaHecho,
                    LocalDateTime.now()
            );

            RepoHechosDinamicos repoHechosDinamicos = RepoHechosDinamicos.getInstance();
            repoHechosDinamicos.withTransaction(() -> repoHechosDinamicos.agregarHecho(hecho));

            for (UploadedFile archivo : archivos) {
                String nombreArchivo = archivo.filename();
                String uploads = "uploads/";
                String rutaDestino = uploads + nombreArchivo;

                // Guardar el archivo físico
                try (InputStream in = archivo.content()) {
                    Files.copy(in, Paths.get(rutaDestino), StandardCopyOption.REPLACE_EXISTING);
                }
                String rutaVista = "/uploads/" + nombreArchivo;

                // Crear entidad y asociar
                ArchivoMultimedia nuevoArchivo = new ArchivoMultimedia(
                        nombreArchivo,
                        rutaVista,
                        hecho
                );

                hecho.agregarArchivo(nuevoArchivo);
                RepoArchivosMultimedia repoArchivosMultimedia = RepoArchivosMultimedia.getInstance();
                repoArchivosMultimedia.withTransaction(() -> repoArchivosMultimedia.agregarArchivo(nuevoArchivo));
            }

            ctx.redirect("/hecho/" + hecho.getId());

        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Ocurrió un error al crear el hecho: " + e.getMessage());
        }
    }

    public void ver(Context ctx) {
        final Long id;
        try {
            id = Long.parseLong(ctx.pathParam("id"));
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de hecho inválido");
            return;
        }

        RepoHechosDinamicos repo = RepoHechosDinamicos.getInstance();
        Hecho hecho = repo.obtenerHechoPorId(id);

        if (hecho == null) {
            ctx.status(404).render("errors/404.hbs", Map.of(
                    "mensaje", "No se encontró el hecho con ID " + id
            ));
            return;
        }

        if (hecho.estaEliminado()) {
            ctx.status(410).render("errors/410.hbs", Map.of(
                    "mensaje", "El hecho fue eliminado."
            ));
            return;
        }

        Map<String, Object> model = new HashMap<>();
        model.put("hecho", hecho);
        model.put("fuenteID", ctx.queryParam("fuenteID"));
        ctx.render("hecho/hecho.detalle.hbs", model);
    }
}