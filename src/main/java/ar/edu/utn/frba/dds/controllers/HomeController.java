package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.model.Hecho;
import ar.edu.utn.frba.dds.model.criterio.Categoria;
import ar.edu.utn.frba.dds.model.fuente.Fuente;
import ar.edu.utn.frba.dds.model.solicitud.DetectorDeSpamBasico;
import ar.edu.utn.frba.dds.model.solicitud.SolicitudDeEliminacion;
import ar.edu.utn.frba.dds.model.ubicacion.Ubicacion;
import ar.edu.utn.frba.dds.repositorios.RepoFuentesDelSistema;
import ar.edu.utn.frba.dds.repositorios.RepoHechosDinamicos;
import ar.edu.utn.frba.dds.repositorios.RepoSolicitudesDeEliminacion;
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
    model.put("hechos", hechos);

    return model;
  }

  public void crearHecho(@NotNull Context ctx) {
    String titulo = ctx.formParam("titulo");
    String descripcion = ctx.formParam("descripcion");
    Categoria categoria = Categoria.valueOf(ctx.formParam("categoria"));
    String latitud = ctx.formParam("latitud");
    String longitud = ctx.formParam("longitud");
    String fechaHechoSt = ctx.formParam("fechaHecho");

    LocalDateTime fechaHecho;
    try {
      fechaHecho = LocalDateTime.parse(fechaHechoSt);
    } catch (Exception e) {
      fechaHecho = LocalDateTime.parse(fechaHechoSt + ":00");
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


    RepoHechosDinamicos repo = RepoHechosDinamicos.getInstance();
    repo.withTransaction(() -> repo.agregarHecho(hecho));

    ctx.redirect("/home");
  }


  public Map<String, Object> listarSolicitudes(@NotNull Context ctx) {
    RepoSolicitudesDeEliminacion repo = RepoSolicitudesDeEliminacion.getInstance(new DetectorDeSpamBasico());
    repo.getSolicitudes();

    Map<String, Object> model = new HashMap<>();
    model.put("solicitudes",repo.getSolicitudes());
    model.put("cantidadSpam",repo.getCantidadDeSpam());
    return model;
  }


  public static void aprobarSolicitud(@NotNull Context context) {
    RepoSolicitudesDeEliminacion repo = RepoSolicitudesDeEliminacion.getInstance(new DetectorDeSpamBasico());
    SolicitudDeEliminacion solicitud = repo.getSolicitudPorId(Long.valueOf(context.pathParam("id")));

    if (solicitud != null && solicitud.getEstado().equals("PENDIENTE")) {
      solicitud.aceptar();
      //no se si tendria que sacarlo de la fuente a ese hecho
    }

    context.redirect("/solicitudesEliminacion");
  }

  public static void rechazarSolicitud(@NotNull Context context) {
    RepoSolicitudesDeEliminacion repo = RepoSolicitudesDeEliminacion.getInstance(new DetectorDeSpamBasico());
    SolicitudDeEliminacion solicitud = repo.getSolicitudPorId(Long.valueOf(context.pathParam("id")));

    if (solicitud != null && solicitud.getEstado().equals("PENDIENTE")) {
      solicitud.aceptar();
    }
    context.redirect("/solicitudesEliminacion");
  }



  public Map<String,Object> solicitarEliminacionForm(@NotNull Context ctx) {
    int fuenteId = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("fuenteId")));
    int hechoId = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("hechoId")));

    Fuente fuente = RepoFuentesDelSistema.getInstance().obtenerFuenteConId(fuenteId);
    Hecho hecho = fuente.obtenerHechoConId(hechoId);

    Map<String,Object> model = new HashMap<>();
    model.put("fuente",fuente);
    model.put("hecho",hecho);
    return model;
  }

  public void solicitarEliminacion(@NotNull Context context) {

    //SolicitudDeEliminacion solicitudDeEliminacion = new SolicitudDeEliminacion();
    //RepoSolicitudesDeEliminacion.getInstance(new DetectorDeSpamBasico()).registrarSolicituDeEliminacion(solicitudDeEliminacion);
    context.redirect("/solicitudes");
  }


  /*
  public void crearColeccion(@NotNull Context ctx) {
  }
     */
}
