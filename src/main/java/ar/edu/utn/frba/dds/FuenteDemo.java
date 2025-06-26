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

public class FuenteDemo extends Fuente {
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

  public void detenerTareaCalendarizada() {
    if (scheduler != null && !scheduler.isShutdown()) {
      scheduler.shutdown();
      schedulerIniciado = false;
    }
  }

  public void consultarServicioAutomaticamente() {
    DateTime fechaConsulta = ultimaConexion;

    // 2. Llama al método 'siguienteHecho' del objeto 'conexion', pasando la URL base y la fechaConsulta.
    //    Este método probablemente consulta el próximo hecho después de fechaConsulta y devuelve un Map con los datos.
    Map<String, Object> hechoMap = conexion.siguienteHecho(urlBase, fechaConsulta);

    // 3. Mientras que el mapa 'hechoMap' no sea nulo (o sea, mientras haya hechos nuevos para procesar)
    while (hechoMap != null) {

      // 4. Convierte el Map recibido a un objeto 'Hecho' usando el método 'getHechoDeMap'
      Hecho hecho = getHechoDeMap(hechoMap);

      // 5. Agrega ese objeto 'Hecho' a la lista de hechos (suponiendo que 'hechos' es un atributo de la clase)
      hechos.add(hecho);

      // 6. Obtiene la fecha del hecho como un objeto LocalDate
      LocalDate fechaHechoLocal = hecho.getFechaHecho();

      // 7. Convierte esa fecha LocalDate a DateTime (añadiendo hora 00:00), para facilitar comparación con fechaConsulta
      DateTime fechaHechoDateTime = new DateTime(fechaHechoLocal.getYear(),
              fechaHechoLocal.getMonthValue(),
              fechaHechoLocal.getDayOfMonth(),
              0, 0);

      // 8. Compara si la fecha del hecho es posterior a la fechaConsulta actual
      if (fechaHechoDateTime.isAfter(fechaConsulta)) {
        // 9. Si es posterior, actualiza fechaConsulta a la fecha del hecho actual para la próxima consulta
        fechaConsulta = fechaHechoDateTime;
      }

      // 10. Consulta el siguiente hecho después de la fechaConsulta actualizada,
      //     para seguir iterando si hay más hechos nuevos
      hechoMap = conexion.siguienteHecho(urlBase, fechaConsulta);
    }

    // 11. Finalmente, actualiza 'ultimaConexion' a la fecha y hora actual para registrar cuándo fue la última consulta
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
  public List<Hecho> obtenerHechos() {
    return hechos.stream().filter(criterio::cumpleCriterio).collect(Collectors.toList());
  }

  public int getSizeHechosBuffer() {
    return hechos.size();
  }

}



