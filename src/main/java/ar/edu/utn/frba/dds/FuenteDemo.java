package ar.edu.utn.frba.dds;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FuenteDemo extends  Fuente {
  private final Conexion conexion;
  private final URL url;
  private LocalDateTime fechaUltimaConsulta;
  private ScheduledExecutorService scheduler;
  private RepoFuenteDemo repoFuenteDemo;

  public FuenteDemo(Conexion conexion, URL url) {
    this.conexion = conexion;
    this.url = url;
    this.fechaUltimaConsulta = LocalDateTime.now().minusHours(1);
    repoFuenteDemo = RepoFuenteDemo.getInstance();
  }

  public void iniciarActualizacionPeriodica() {
    if (scheduler == null || scheduler.isShutdown()) {
      scheduler = Executors.newSingleThreadScheduledExecutor();
      scheduler.scheduleAtFixedRate(this::actualizarHechos, 0, 1, TimeUnit.HOURS);
    }
  }

  public void detenerActualizacion() {
    if (scheduler != null && !scheduler.isShutdown()) {
      scheduler.shutdown();
    }
  }

  public void actualizarHechos() {
    Map<String, Object> datosHecho = conexion.siguienteHecho(url, fechaUltimaConsulta);
    while (datosHecho != null) {
      Hecho hecho = mapearHecho(datosHecho);
      repoFuenteDemo.agregarHecho(hecho);
      fechaUltimaConsulta = LocalDateTime.now();
      datosHecho = conexion.siguienteHecho(url, fechaUltimaConsulta);
    }
  }

  private Hecho mapearHecho(Map<String, Object> datos) {
    String titulo = (String) datos.get("titulo");
    String descripcion = (String) datos.get("descripcion");
    Categoria categoria = (Categoria) datos.get("categoria"); // Requiere casteo válido
    String latitud = (String) datos.get("latitud");
    String longitud = (String) datos.get("longitud");
    LocalDate fechaHecho = (LocalDate) datos.get("fechaHecho");
    LocalDate fechaCarga = (LocalDate) datos.get("fechaCarga");

    return new Hecho(titulo, descripcion, categoria, latitud, longitud, fechaHecho, fechaCarga);
  }

  //tengo que buscar una manera que solamente me devuelva los hechos de esa fuente, no todos los hechos
  public List<Hecho> obtenerHechos() {
    return repoFuenteDemo.getHechos();
  }
}