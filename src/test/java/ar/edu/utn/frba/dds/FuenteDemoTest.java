package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
/*
class FuenteDemoTest {

  private Conexion conexion;
  private FuenteDemo fuenteDemo;

  @BeforeEach
  void iniciarConexion() throws MalformedURLException {
    conexion = mock(Conexion.class);
    fuenteDemo = new FuenteDemo(new URL("http://hola"), conexion);
    fuenteDemo.detenerTareaCalendarizada(); // detenemos la tarea programada para evitar interferencias
  }

  private Map<String, Object> crearMapConHechoDePrueba() {
    Map<String, Object> rta = new HashMap<>();
    rta.put("titulo", "demolicion");
    rta.put("descripcion", "demolicion de camiones");
    rta.put("categoria", "INCENDIO_FORESTAL");
    rta.put("latitud", "10.0");
    rta.put("longitud", "20.0");
    rta.put("fechaHecho", LocalDate.now());
    rta.put("fechaCarga", LocalDate.now());
    return rta;
  }

  @Test
  @Timeout(5) // falla si demora más de 5 segundos
  void agregarHechoAFuenteDemo() throws MalformedURLException {
    Map<String, Object> rta = crearMapConHechoDePrueba();
    when(conexion.siguienteHecho(eq(new URL("http://hola")), any(DateTime.class))).thenReturn(rta).thenReturn(null);

    System.out.println("Antes de consultarServicioAutomaticamente");
    fuenteDemo.consultarServicioAutomaticamente();
    System.out.println("Después de consultarServicioAutomaticamente");

    assertEquals(1, fuenteDemo.getSizeHechosBuffer());
  }

  @Test
  @Timeout(5)
  void nosDevuelteNullSiguienteHecho() throws MalformedURLException {
    when(conexion.siguienteHecho(eq(new URL("http://hola")), any(DateTime.class))).thenReturn(null);

    System.out.println("Antes de consultarServicioAutomaticamente");
    fuenteDemo.consultarServicioAutomaticamente();
    System.out.println("Después de consultarServicioAutomaticamente");

    assertEquals(0, fuenteDemo.getSizeHechosBuffer());
  }

  @DisplayName("Como persona usuaria, quiero poder obtener todos los hechos de una fuente proxy demo configurada en una colección, con un nivel de antigüedad máximo de una hora")
  @Test
  @Timeout(5)
  void obtenerHechosDeColeccionConFuenteDemo() throws MalformedURLException {
    fuenteDemo.detenerTareaCalendarizada(); // evitamos interferencias

    Map<String, Object> hechoReciente = new HashMap<>();
    hechoReciente.put("titulo", "Hecho Reciente");
    hechoReciente.put("descripcion", "desc");
    hechoReciente.put("categoria", "INCENDIO_FORESTAL");
    hechoReciente.put("latitud", "1.0");
    hechoReciente.put("longitud", "2.0");
    hechoReciente.put("fechaHecho", LocalDate.now());
    hechoReciente.put("fechaCarga", LocalDate.now());

    Map<String, Object> hechoViejo = new HashMap<>();
    hechoViejo.put("titulo", "Hecho Viejo");
    hechoViejo.put("descripcion", "desc");
    hechoViejo.put("categoria", "INCENDIO_FORESTAL");
    hechoViejo.put("latitud", "1.0");
    hechoViejo.put("longitud", "2.0");
    hechoViejo.put("fechaHecho", LocalDate.now().minusDays(2));
    hechoViejo.put("fechaCarga", LocalDate.now().minusDays(2));

    when(conexion.siguienteHecho(eq(new URL("http://hola")), any(DateTime.class)))
        .thenReturn(hechoReciente)
        .thenReturn(hechoViejo)
        .thenReturn(null);

    System.out.println("Antes de consultarServicioAutomaticamente");
    fuenteDemo.consultarServicioAutomaticamente();
    System.out.println("Después de consultarServicioAutomaticamente");

    Criterio criterio = new CriterioPorAntiguedad(LocalDateTime.now(), 1);
    List<Hecho> hechosFiltrados = fuenteDemo.obtenerHechosConCriterio(criterio);

    assertEquals(1, hechosFiltrados.size());
    assertEquals("Hecho Reciente", hechosFiltrados.get(0).getTitulo());
  }
}
*/