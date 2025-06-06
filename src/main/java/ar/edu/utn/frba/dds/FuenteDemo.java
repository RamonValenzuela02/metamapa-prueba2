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
  List<Hecho> hechos = new ArrayList<Hecho>();
  Conexion conexion;
  URL urlBase;
  DateTime ultimaConexion = new DateTime();
  // Buffer para almacenar hechos obtenidos automáticamente
  private Queue<Hecho> bufferHechos = new LinkedList<>();           // Buffer para hechos
  private ScheduledExecutorService scheduler;                       // El "reloj" automático
  private boolean schedulerIniciado = false;                        // Control de estado

  public FuenteDemo(URL urlBase, Conexion conexion) {
    this.conexion = conexion;
    this.urlBase = urlBase;
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

  // Obtener varios hechos del servicio externo
  private void consultarServicioAutomaticamente() {
    for (int i = 0; i < 5; i++) { // Ejemplo: obtener hasta 5 hechos

      Map<String, Object> mapConHecho = conexion.siguienteHecho(urlBase, ultimaConexion);
      if (mapConHecho != null) {
        Hecho hecho = getHechoDeMap(mapConHecho);
        // Nuevo hecho agregado al buffer
        synchronized (bufferHechos) {
          bufferHechos.offer(hecho);
        }
      } else {
        break; // No hay más hechos disponibles
      }
    }
    ultimaConexion = DateTime.now();
  }

  // Toma hechos del buffer el lugar de consultar directamente el servicio externo
  public void obtenerSiguienteHecho() {
    synchronized (bufferHechos) {
      if (!bufferHechos.isEmpty()) {
        Hecho hecho = bufferHechos.poll(); // Toma y remueve del buffer
        hechos.add(hecho);
      }
    }
  }

  private Hecho getHechoDeMap(Map<String, Object> datos) {

    return new Hecho((String) datos.get("titulo"),
        (String) datos.get("descripcion"),
        Categoria.valueOf((String) datos.get("categoria")),
        (String) datos.get("latitud"),
        (String) datos.get("longitud"),
        (LocalDate) datos.get("fechaHecho"),
        (LocalDate) datos.get("fechaCarga"));
  }

  @Override
  public List<Hecho> obtenerHechosConCriterio(Criterio criterio) {
    return hechos.stream().filter(criterio::cumpleCriterio).collect(Collectors.toList());
  }

  // Método para detener el scheduler (importante para cleanup)
  public void detenerTareaCalendarizada() {
    if (scheduler != null && !scheduler.isShutdown()) {
      scheduler.shutdown();
      schedulerIniciado = false;
    }
  }

}