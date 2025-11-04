package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.model.coleccion.Coleccion;
import ar.edu.utn.frba.dds.model.consenso.AlgoritmoConsenso;
import ar.edu.utn.frba.dds.model.criterio.Criterio;
import ar.edu.utn.frba.dds.model.criterio.CriterioCumplidorSiempre;
import ar.edu.utn.frba.dds.model.fuente.Fuente;
import ar.edu.utn.frba.dds.repositorios.RepoDeColecciones;
import ar.edu.utn.frba.dds.repositorios.RepoFuentesDelSistema;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColeccionController {

    public void formNuevaColeccion(Context ctx) {
        List<Fuente> fuentes = RepoFuentesDelSistema.getInstance().obtenerFuentes();
        List<AlgoritmoConsenso> algoritmos = Arrays.asList(AlgoritmoConsenso.values());

        Map<String,Object> model = new HashMap<>();
        model.put("algoritmos",algoritmos);
        model.put("fuentes",fuentes);

        ctx.render("coleccion/coleccion.nueva.hbs", model);
    }

    public void crearColeccion(@NotNull Context context) {

        try {
            String handle = context.formParam("handle");
            String titulo = context.formParam("titulo");
            String descripcion = context.formParam("descripcion");
            String fuenteStr = context.formParam("fuente");
            String algoritmoStr = context.formParam("algoritmo");
            //List<String> criteriosStr = context.formParams("criterio");

            if (titulo == null || titulo.isBlank() ||
                    descripcion == null || descripcion.isBlank()) {
                context.status(400).result("Faltan campos obligatorios");
                return;
            }

            AlgoritmoConsenso algoritmoConsenso;
            try {
                algoritmoConsenso = AlgoritmoConsenso.valueOf(algoritmoStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                context.status(400).result("Algoritmo inválido: " + algoritmoStr);
                return;
            }

            long fuenteId = Long.parseLong(fuenteStr);
            Fuente fuente = RepoFuentesDelSistema.getInstance().obtenerFuenteConId(fuenteId);

            List<Criterio> criterios = List.of(new CriterioCumplidorSiempre());

            Coleccion coleccion = new Coleccion(handle, titulo,descripcion, fuente, criterios, algoritmoConsenso);

            RepoDeColecciones repo = RepoDeColecciones.getInstance();
            repo.withTransaction(() -> repo.agregarColeccion(coleccion));

            context.redirect("/admin");

        } catch (Exception e) {
            e.printStackTrace();
            context.status(500).result("Ocurrió un error al crear la coleccion: " + e.getMessage());
        }
    }

    public void listarColecciones(Context ctx) {

        List<Coleccion> colecciones = RepoDeColecciones.getInstance().getColecciones();
        Map<String, Object> model = new HashMap<>();
        model.put("colecciones", colecciones);
        ctx.render("coleccion/colecciones.hbs", model);
    }

}
