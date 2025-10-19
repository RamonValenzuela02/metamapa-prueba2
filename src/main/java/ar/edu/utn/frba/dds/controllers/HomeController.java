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
    //falta logica de aprobacion
    context.redirect("/solicitudes");
  }

  public static void rechazarSolicitud(@NotNull Context context) {
    //falta logica de rechazo
    context.redirect("/solicitudes");
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


}
