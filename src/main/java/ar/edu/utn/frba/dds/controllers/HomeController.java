package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.model.Hecho.Hecho;
import ar.edu.utn.frba.dds.model.Usuario.TipoUsuario;
import ar.edu.utn.frba.dds.model.coleccion.Coleccion;
import ar.edu.utn.frba.dds.model.coleccion.ModoNavegacion;
import ar.edu.utn.frba.dds.model.consenso.AlgoritmoConsenso;
import ar.edu.utn.frba.dds.model.criterio.Criterio;
import ar.edu.utn.frba.dds.model.criterio.CriterioCumplidorSiempre;
import ar.edu.utn.frba.dds.model.fuente.Fuente;
import ar.edu.utn.frba.dds.model.solicitud.DetectorDeSpamBasico;
import ar.edu.utn.frba.dds.model.solicitud.ServicioDeSolicitudesEliminacion;
import ar.edu.utn.frba.dds.model.solicitud.SolicitudDeEliminacion;
import ar.edu.utn.frba.dds.repositorios.*;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;

public class HomeController{
  public Map<String,Object> index(@NotNull Context ctx) {

    Long user_id = ctx.sessionAttribute("user_id");
    String busqueda = ctx.queryParam("likeTexto");
    String coleccionQuery = ctx.queryParam("coleccionId");
    String modoQuery = ctx.queryParam("modoNavegacion");
    ModoNavegacion modoNavegacion = "CURADA".equalsIgnoreCase(modoQuery) ? ModoNavegacion.CURADA : ModoNavegacion.IRRESTRICTA;

    List<String> provinciasSeleccionadas = new ArrayList<>(ctx.queryParams("provincias"));

    String fechaDesdeString = ctx.queryParam("fechaDesde");
    String fechaHastaString = ctx.queryParam("fechaHasta");

    LocalDateTime desde = parsearFechaComoInicioDeDia(fechaDesdeString);
    LocalDateTime hasta = parsearFechaComoFinDeDia(fechaHastaString);

    RepoDeColecciones repoColecciones = RepoDeColecciones.getInstance();
    List<Coleccion> colecciones = repoColecciones.getColecciones();

    Coleccion coleccionElegida = null;
    if (coleccionQuery != null) {
      try {
        Long coleccionId = Long.parseLong(coleccionQuery);
        coleccionElegida = repoColecciones.getColeccionPorId(coleccionId);
      } catch (NumberFormatException e) {

      }
    }
    if (coleccionElegida == null && !colecciones.isEmpty()) {
      coleccionElegida = colecciones.get(0);
    }

    List<Hecho> hechosDeColeccion = new ArrayList<>();
    if (coleccionElegida != null) {
      hechosDeColeccion = coleccionElegida.getHechosConNavegacion(modoNavegacion);
    }

    List<Hecho> hechosFiltrados = hechosDeColeccion.stream()
            .filter(hecho -> provinciasSeleccionadas.isEmpty() || provinciasSeleccionadas.contains(hecho.getProvincia()))
            .filter(hecho -> desde == null || !hecho.getFechaHecho().isBefore(desde))
            .filter(hecho -> hasta == null || !hecho.getFechaHecho().isAfter(hasta))
            .filter(h -> {
              if(busqueda == null || busqueda.isBlank()) return true;
              String titulo =h.getTitulo().toLowerCase();
              String descripcion = h.getDescripcion().toLowerCase();
              return titulo.contains(busqueda.toLowerCase()) || descripcion.contains(busqueda.toLowerCase());
            })
            .toList();


    Set<String> provinciasDisponibles = hechosDeColeccion.stream()
            .map(Hecho::getProvincia)
            .filter(Objects::nonNull)
            .collect(Collectors.toCollection(TreeSet::new));

    Map<String,Object> model = new HashMap<>();
    model.put("modoSeleccionado", modoNavegacion.name());
    model.put("hechos", hechosFiltrados);
    model.put("provincias", provinciasDisponibles);
    model.put("provSeleccionadas", provinciasSeleccionadas);
    model.put("fechaDesde", desde);
    model.put("fechaHasta", hasta);
    model.put("colecciones", colecciones);
    model.put("coleccionSeleccionada", coleccionElegida != null ? coleccionElegida.getId() : null);
    model.put("busqueda", busqueda);
    model.put("usuarioLogueado", user_id != null);

    if (user_id != null) {
      var usuario = RepoUsuarios.getInstance().buscarPorId(user_id);
      model.put("nombreUsuario", usuario != null ? usuario.getNombre() : null);
    }
    return model;
  }

  public void showHome(Context ctx) {
    TipoUsuario tipo = ctx.sessionAttribute("tipo_usuario");

    if(tipo == TipoUsuario.ADMINISTRADOR) {
      ctx.redirect("/admin");
      return;
    }

    boolean esHtmx = "true".equalsIgnoreCase(ctx.header("HX-Request"));

    if (esHtmx) {
      ctx.render("partials/resultados-y-provincias.hbs",index(ctx));
      return;
    }

    ctx.render("home/home.hbs", index(ctx));
  }

  public Map<String,Object> modeloHomeAdmin(Context ctx){
    Map<String,Object> modelo = new HashMap<>();
    Long user_id = ctx.sessionAttribute("user_id");
    modelo.put("usuarioLogueado", user_id != null);

    if (user_id != null) {
      var usuario = RepoUsuarios.getInstance().buscarPorId(user_id);
      modelo.put("nombreUsuario", usuario != null ? usuario.getNombre() : null);
    }
    List<SolicitudDeEliminacion> pendientes = RepoSolicitudesDeEliminacion.getInstance().getSolicitudesPendientes();

    modelo.put("solicitudesPendientes", pendientes);
    modelo.put("cantidadSolicitudesPendientes", pendientes.size());
    modelo.put("tieneSolicitudesPendientes", !pendientes.isEmpty());
    return modelo;
  }

  public void showAdminHome(Context ctx) {
    TipoUsuario tipo = ctx.sessionAttribute("tipo_usuario");

    if(tipo != TipoUsuario.ADMINISTRADOR){
      ctx.redirect("/home");
      return;
    }

    ctx.render("home/home.administrador.hbs", modeloHomeAdmin(ctx));
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
