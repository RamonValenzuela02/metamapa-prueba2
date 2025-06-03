package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FuenteDemoTest {
  Conexion conexion;

  @BeforeEach
  void iniciarConexion() {
    conexion = mock(Conexion.class);
  }


  @Test
  void agregarHechoAFuenteDemo() throws MalformedURLException {
    URL url = crearUrlPrueba();
    Map<String, Object> rta = crearMapConHechoDePrueba();
    when(conexion.siguienteHecho(eq(url), any(DateTime.class))).thenReturn(rta);

    FuenteDemo fuenteDemo = new FuenteDemo(url, conexion);
    fuenteDemo.obtenerSiguienteHecho();

    assertFalse(fuenteDemo.hechos.isEmpty());
  }

  @Test
  void nosDevuelteNullSiguienteHecho() throws MalformedURLException {
    URL url = crearUrlPrueba();
    when(conexion.siguienteHecho(eq(url),  any(DateTime.class))).thenReturn(null);
    FuenteDemo fuenteDemo = new FuenteDemo(url, conexion);
    fuenteDemo.obtenerSiguienteHecho();

    assertTrue(fuenteDemo.hechos.isEmpty());
  }

  private URL crearUrlPrueba() throws MalformedURLException {
    return new URL("http://hola");
  }

  private Map<String, Object> crearMapConHechoDePrueba() {
    Map<String, Object> rta = new HashMap<>();
    rta.put("titulo", "demolicion");
    rta.put("descripcion", "demolicion de camiones");
    rta.put("categoria", "INCENDIO_FORESTAL");
    rta.put("latitud", "10.0");
    rta.put("longitud", "20.0");
    rta.put("fechaHecho", LocalDate.now());

    return rta;
  }

}