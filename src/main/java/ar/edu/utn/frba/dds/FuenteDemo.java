package ar.edu.utn.frba.dds;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.joda.time.DateTime;

public class FuenteDemo implements Fuente {
  private List<Hecho> bufferHechos = new ArrayList<Hecho>();
  private Conexion conexion;
  private URL urlBase;
  private DateTime ultimaConexion = new DateTime();
  // Buffer para almacenar hechos obtenidos automáticamente   // Buffer para hechos
  private ScheduledExecutorService scheduler;                       // El "reloj" automático
  private boolean schedulerIniciado = false;                        // Control de estado

  public FuenteDemo(URL urlBase, Conexion conexion) {
    this.urlBase = urlBase;
    this.conexion = conexion;
    iniciarTareaCalendarizada();
  }
  /**
   Ejecuta automaticamente cada 1 hora.
   Delay inicial 0, empieza inmediatamente.
   Período (cada 1 hora)
   */
  private void iniciarTareaCalendarizada() {
    if (!schedulerIniciado) {
      scheduler = Executors.newScheduledThreadPool(1);

      scheduler.scheduleAtFixedRate(this::consultarServicioAutomaticamente, 0, 1, TimeUnit.HOURS);

      schedulerIniciado = true;
      // Tarea calendarizada iniciada: consultará el servicio cada hora
    }
  }
/**
   Método para detener el scheduler (importante para cleanup)
 */
  public void detenerTareaCalendarizada() {
    if (scheduler != null && !scheduler.isShutdown()) {
      scheduler.shutdown();
      schedulerIniciado = false;
    }
  }

  public void consultarServicioAutomaticamente() {
    synchronized (bufferHechos) {
      bufferHechos.clear();
    }

    DateTime fechaConsulta = ultimaConexion;

    Map<String, Object> hechoMap = conexion.siguienteHecho(urlBase, fechaConsulta);

    while (hechoMap != null) {
      Hecho hecho = getHechoDeMap(hechoMap);

      synchronized (bufferHechos) {
        bufferHechos.add(hecho);
      }

      LocalDate fechaHechoLocal = hecho.getFechaHecho();
      DateTime fechaHechoDateTime = new DateTime(fechaHechoLocal.getYear(),
          fechaHechoLocal.getMonthValue(),
          fechaHechoLocal.getDayOfMonth(),
          0, 0);
      if (fechaHechoDateTime.isAfter(fechaConsulta)) {
        fechaConsulta = fechaHechoDateTime;
      }

      hechoMap = conexion.siguienteHecho(urlBase, fechaConsulta);
    }

    ultimaConexion = DateTime.now();
  }



  /**
   * Transforma el map con los atributos de un hecho en un heho
   */
  private Hecho getHechoDeMap(Map<String, Object> datos) {
    return new Hecho(
        (String) datos.get("titulo"),
        (String) datos.get("descripcion"),
        Categoria.valueOf((String) datos.get("categoria")),
        (String) datos.get("latitud"),
        (String) datos.get("longitud"),
        (LocalDate) datos.get("fechaHecho"),
        (LocalDate) datos.get("fechaCarga"));
  }

  @Override
  public List<Hecho> obtenerHechosConCriterio(Criterio criterio) {
    return bufferHechos.stream().filter(criterio::cumpleCriterio).collect(Collectors.toList());
  }

  @Override
  public List<Hecho> obtenerHechosConVariosCriterios(List<Hecho> hechosAfiltrar, List<Criterio> criterios) {
    return Fuente.super.obtenerHechosConVariosCriterios(bufferHechos, criterios);
  }

  public int getSizeHechosBuffer() {
    return bufferHechos.size();
  }

}



