package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.model.Hecho;
import ar.edu.utn.frba.dds.model.criterio.Categoria;
import ar.edu.utn.frba.dds.model.fuente.Fuente;
import ar.edu.utn.frba.dds.model.ubicacion.Ubicacion;
import ar.edu.utn.frba.dds.repositorios.RepoFuentesDelSistema;
import ar.edu.utn.frba.dds.repositorios.RepoHechosDinamicos;
import io.javalin.http.Context;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class HomeController{
  public Map<String,Object> index(@NotNull Context ctx) {
    List<Fuente> fuentes = RepoFuentesDelSistema.getInstance().obtenerFuentes();
    List<Hecho> hechos = fuentes.stream()
      .flatMap(x-> x.obtenerHechos().stream())
      .toList();

    Map<String,Object> model = new HashMap<>();
    model.put("fuentes",fuentes);

    return model;
  }

  public void crearHecho(@NotNull Context ctx) {
    String titulo = ctx.formParam("titulo");
    String descripcion = ctx.formParam("descripcion");
    Categoria categoria = Categoria.valueOf(ctx.formParam("categoria"));
    String latitud = ctx.formParam("latitud");
    String longitud = ctx.formParam("longitud");
    LocalDateTime fechaHecho = LocalDateTime.parse(Objects.requireNonNull(ctx.formParam("fechaHecho")));

    Hecho hecho = new Hecho(
      titulo,
      descripcion,
      categoria,
      latitud,
      longitud,
      fechaHecho,
      LocalDateTime.now()
    );
    //RepoHechosDinamicos repo = RepoHechosDinamicos.getInstance();
    //repo.withTransaction(() -> repo.agregarHecho(hecho));

    ctx.redirect("/home");
  }

  /*
  public Map<String,Object> solicitarEliminacionForm(@NotNull Context ctx) {
  }

  public void solicitarEliminacion(@NotNull Context context) {
  }
    */

}
