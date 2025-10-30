package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.model.Hecho.Hecho;
import ar.edu.utn.frba.dds.model.Multimedia.ArchivoMultimedia;
import ar.edu.utn.frba.dds.model.Usuario.TipoUsuario;
import ar.edu.utn.frba.dds.model.coleccion.Coleccion;
import ar.edu.utn.frba.dds.model.consenso.AlgoritmoConsenso;
import ar.edu.utn.frba.dds.model.criterio.Categoria;
import ar.edu.utn.frba.dds.model.criterio.Criterio;
import ar.edu.utn.frba.dds.model.criterio.CriterioCumplidorSiempre;
import ar.edu.utn.frba.dds.model.fuente.Fuente;
import ar.edu.utn.frba.dds.model.solicitud.DetectorDeSpamBasico;
import ar.edu.utn.frba.dds.model.solicitud.ServicioDeSolicitudesEliminacion;
import ar.edu.utn.frba.dds.model.solicitud.SolicitudDeEliminacion;
import ar.edu.utn.frba.dds.repositorios.RepoDeColecciones;
import ar.edu.utn.frba.dds.repositorios.RepoUsuarios;
import ar.edu.utn.frba.dds.repositorios.RepoFuentesDelSistema;
import ar.edu.utn.frba.dds.repositorios.RepoHechosDinamicos;
import ar.edu.utn.frba.dds.repositorios.RepoSolicitudesDeEliminacion;
import ar.edu.utn.frba.dds.repositorios.RepoArchivosMultimedia;
import io.javalin.http.Context;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.javalin.http.UploadedFile;
import org.jetbrains.annotations.NotNull;

public class HomeController{
  public Map<String,Object> index(@NotNull Context ctx) {

    String modo = ctx.queryParam("modoNavegacion");

    List<String> provinciasSeleccionadas = new ArrayList<>();
    provinciasSeleccionadas.addAll(ctx.queryParams("provincias"));

    String fechaDesdeString = ctx.queryParam("fechaDesde");
    String fechaHastaString = ctx.queryParam("fechaHasta");

    System.out.println("Fecha Desde Str: " + fechaDesdeString);
    System.out.println("Fecha Hasta Str: " + fechaHastaString);

    LocalDateTime desde = parsearFechaComoInicioDeDia(fechaDesdeString);
    LocalDateTime hasta = parsearFechaComoFinDeDia(fechaHastaString);

    System.out.println("Fecha Desde: " + desde);
    System.out.println("Fecha Hasta: " + hasta);

    List<Fuente> fuentes = RepoFuentesDelSistema.getInstance().obtenerFuentes();

    List<Map<String, Object>> fuentesConHechos = fuentes.stream()
      .map(f -> {

        Stream<Hecho> hechos = f.obtenerHechos().stream()
                .filter(h -> !h.estaEliminado());

        
        if(!provinciasSeleccionadas.isEmpty()){
          hechos = hechos.filter(h -> provinciasSeleccionadas.contains(h.getProvincia()));
        }

        if(desde != null){
          hechos = hechos.filter(h -> !h.getFechaHecho().isBefore(desde));
        }
        if(hasta != null){
          hechos = hechos.filter(h -> !h.getFechaHecho().isAfter(hasta));
        }

        Map<String, Object> datos = new HashMap<>();
        datos.put("fuenteID", f.getId());
        datos.put("hechos", hechos.toList());
        return datos;
      }).toList();


    Set<String> provincias = fuentes.stream()
            .flatMap(f -> f.obtenerHechos().stream())
            .filter(h -> !h.estaEliminado())
            .map(Hecho::getProvincia)
            .filter(Objects::nonNull)
            .collect(Collectors.toCollection(TreeSet::new));

    Map<String,Object> model = new HashMap<>();
    model.put("fuentes", fuentesConHechos);
    model.put("provincias", provincias);
    model.put("modoSeleccionado", modo);
    model.put("fechaDesde", desde);
    model.put("fechaHasta", hasta);
    model.put("provSeleccionadas", provinciasSeleccionadas);
    model.put("usuarioLogueado", ctx.sessionAttribute("user_id") != null);

    if (ctx.sessionAttribute("user_id") != null) {
      var usuario = RepoUsuarios.getInstance().buscarPorId(ctx.sessionAttribute("user_id"));
      model.put("nombreUsuario", usuario != null ? usuario.getNombre() : null);
    }

    return model;
  }
  public void showHome(Context ctx) {
    TipoUsuario tipo = ctx.sessionAttribute("tipo_usuario");
    boolean esHtmx = "true".equalsIgnoreCase(ctx.header("HX-Request"));

    if (esHtmx) {
      ctx.render("partials/resultados.hbs",index(ctx));
    }else {
      ctx.render("home/home.hbs", index(ctx));
    }

    if (tipo == null) {
      ctx.render("home/home.hbs", index(ctx));
      return;
    }

    switch (tipo) {
      case ADMINISTRADOR:
        ctx.render("home/home.administrador.hbs", index(ctx));
        break;
      case CONTRIBUYENTE:
        ctx.render("home/home.contribuyente.hbs", index(ctx));
        break;
      default:
        ctx.redirect("/login");
    }
  }

  public Map<String, Object> listarSolicitudes(@NotNull Context ctx) {
    RepoSolicitudesDeEliminacion repo = RepoSolicitudesDeEliminacion.getInstance();

    Map<String, Object> model = new HashMap<>();
    model.put("solicitudes",repo.getSolicitudes());
    model.put("cantidadSpam",repo.getCantidadDeSpam());
    return model;
  }


  public static void aprobarSolicitud(@NotNull Context context) {
    RepoSolicitudesDeEliminacion repo = RepoSolicitudesDeEliminacion.getInstance();

      repo.withTransaction(()->{
        SolicitudDeEliminacion solicitud = repo.getSolicitudPorId(Long.valueOf(context.pathParam("id")));
        if (solicitud != null) {
          solicitud.aceptar();
          solicitud.getHecho().setEliminado(true);
          repo.entityManager().merge(solicitud);
        }
      });

    context.redirect("/solicitudesEliminacion");
  }

  public static void rechazarSolicitud(@NotNull Context context) {
    RepoSolicitudesDeEliminacion repo = RepoSolicitudesDeEliminacion.getInstance();
    repo.withTransaction(()->{
      SolicitudDeEliminacion solicitud = repo.getSolicitudPorId(Long.valueOf(context.pathParam("id")));
      if (solicitud != null) {
        solicitud.rechazar();
        repo.entityManager().merge(solicitud);
      }
    });
    context.redirect("/solicitudesEliminacion");
  }


  public Map<String,Object> solicitarEliminacionForm(@NotNull Context ctx) {
    int fuenteId = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("fuenteId")));
    int hechoId = Integer.parseInt(Objects.requireNonNull(ctx.queryParam("hechoId")));

    Fuente fuente = RepoFuentesDelSistema.getInstance().obtenerFuenteConId((long) fuenteId);
    Hecho hecho = fuente.obtenerHechoConId(hechoId);

    Map<String,Object> model = new HashMap<>();
    model.put("fuente",fuente);
    model.put("hecho",hecho);
    return model;
  }

  public void solicitarEliminacion(@NotNull Context context) {
    int hechoId = Integer.parseInt(context.formParam("hechoId"));
    int fuenteId = Integer.parseInt(Objects.requireNonNull(context.formParam("fuenteId")));
    String motivo = context.formParam("motivo");

    Fuente fuente = RepoFuentesDelSistema.getInstance().obtenerFuenteConId((long) fuenteId);
    Hecho hecho = fuente.obtenerHechoConId(hechoId);

    SolicitudDeEliminacion solicitudDeEliminacion = new SolicitudDeEliminacion(hecho,motivo,fuente);
    ServicioDeSolicitudesEliminacion servicio = new ServicioDeSolicitudesEliminacion(new DetectorDeSpamBasico());

    RepoSolicitudesDeEliminacion.getInstance().withTransaction(() -> {
      servicio.registrarSolicituDeEliminacion(solicitudDeEliminacion);
    });

    context.redirect("/home");
  }

  //CREAR COLECCION
  public void crearColeccion(@NotNull Context context) {
    try {
      String titulo = context.formParam("titulo");
      String descripcion = context.formParam("descripcion");
      String fuenteStr = context.formParam("fuente");
      String algoritmoStr = context.formParam("algoritmo");
      List<Criterio> criterios = List.of(new CriterioCumplidorSiempre());
      //List<String> criteriosStr = context.formParams("criterio");

      if (titulo == null || titulo.isBlank() ||
        descripcion == null || descripcion.isBlank()) {
        context.status(400).result("Faltan campos obligatorios");
        return;
      }

      /*
      List<Criterio> criterios = criteriosStr.stream()
        .map(this::crearCriterioDesdeTexto)
        .toList();
      */

      AlgoritmoConsenso algoritmoConsenso;
      try {
        algoritmoConsenso = AlgoritmoConsenso.valueOf(algoritmoStr.toUpperCase());
      } catch (IllegalArgumentException e) {
        context.status(400).result("Algoritmo inválido: " + algoritmoStr);
        return;
      }
      Fuente fuente = RepoFuentesDelSistema.getInstance().obtenerFuenteConId((long) Integer.parseInt(fuenteStr));

      Coleccion coleccion = new Coleccion(titulo,descripcion, fuente, criterios, algoritmoConsenso );

      RepoDeColecciones repo = RepoDeColecciones.getInstance();
      repo.withTransaction(() -> repo.agregarColeccion(coleccion));

      context.redirect("/home");

    } catch (Exception e) {
      e.printStackTrace();
      context.status(500).result("Ocurrió un error al crear la coleccion: " + e.getMessage());
    }
  }

  public Map<String,Object> formularioNuevaColeccion() {
    List<Fuente> fuentes = RepoFuentesDelSistema.getInstance().obtenerFuentes();
    List<AlgoritmoConsenso> algoritmos = Arrays.asList(AlgoritmoConsenso.values());

    Map<String,Object> model = new HashMap<>();
    model.put("algoritmos",algoritmos);
    model.put("fuentes",fuentes);
    return model;
  }

  private LocalDateTime parsearFechaComoInicioDeDia(String fecha) {
    if (fecha == null || fecha.isBlank()) return null;
    LocalDate d = LocalDate.parse(fecha);
    return d.atStartOfDay(); // 2025-10-20T00:00:00
  }

  private LocalDateTime parsearFechaComoFinDeDia(String fecha) {
    if (fecha == null || fecha.isBlank()) return null;
    LocalDate d = LocalDate.parse(fecha); // "2025-10-20"
    return d.plusDays(1).atStartOfDay().minusSeconds(1); // 2025-10-20T23:59:59
  }
}
