package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.function.Supplier;


class FuenteDemoTest {
  private Conexion conexion;
  private FuenteDemo fuenteDemo;
  private Supplier mockTiempo;

  @BeforeEach
  void iniciarConexion() throws MalformedURLException {
    conexion = mock(Conexion.class);
    fuenteDemo = new FuenteDemo(new URL("http://hola"), conexion);
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
  void agregarHechoAFuenteDemo() throws MalformedURLException {

    Map<String, Object> rta = crearMapConHechoDePrueba();
    when(conexion.siguienteHecho(eq(new URL("http://hola")), any(DateTime.class))).thenReturn(rta);

    fuenteDemo.consultarServicioAutomaticamente();
    Assertions.assertEquals(1, fuenteDemo.getSizeHechosBuffer());
  }

  @Test
  void nosDevuelteNullSiguienteHecho() throws MalformedURLException {

    when(conexion.siguienteHecho(eq(new URL("http://hola")), any(DateTime.class))).thenReturn(null);

    Assertions.assertEquals(0 ,fuenteDemo.getSizeHechosBuffer());
  }

  @DisplayName("Como persona usuaria, quiero poder obtener todos los hechos de una fuente proxy demo configurada en una colección,con un nivel de antigüedad máximo de una hora") //req 2
  @Test
  void obtenerHechosDeColeccionConFuenteDemo() throws MalformedURLException {
    /*
    Coleccion coleccion;

    // Creamos una lista de hechos simulados
    Hecho hecho1 = new Hecho(
        "Hecho 1", "Descripcion 1", Categoria.INCENDIO_FORESTAL,
        "-34.61", "-58.38",
        LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 2)
    );

    Hecho hecho2 = new Hecho(
        "Hecho 2", "Descripcion 2", Categoria.INCENDIO_FORESTAL,
        "-34.60", "-58.37",
        LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 20)
    );

    // Creamos un criterio dummy para usar en la coleccion
    Criterio criterioDummy = mock(Criterio.class);

    // Definimos qué devuelve el mock cuando se llama a obtenerHechosConCriterio
    when(fuenteDemo.obtenerHechosConCriterio(any(Criterio.class))).thenReturn(List.of(hecho1, hecho2));

    // Creamos la coleccion con la fuente mockeada
    coleccion = new Coleccion("Titulo Test", "Descripcion Test", fuenteDemo, criterioDummy);

    //Criterio criterioPorAntiguedad = new CriterioPorAntiguedad(LocalDateTime.now(),1);
    //coleccion = new Coleccion("fuego34", "fuego patagonico", fuenteDemo, criterioPorAntiguedad);

    Map<String, Object> rta = crearMapConHechoDePrueba();
    when(conexion.siguienteHecho(eq(new URL("http://hola")), any(DateTime.class))).thenReturn(rta);

    Clock relojBase = Clock.systemDefaultZone();
    LocalDateTime ahora = LocalDateTime.now(relojBase);


    // Crea un Supplier para obtener el tiempo actual basado en un reloj mutable
    interface TiempoAvanzable extends Supplier<LocalDateTime> {
        void avanzarTiempo(Duration duracion);
    }

    TiempoAvanzable tiempoFicticio = new TiempoAvanzable() {
      private Clock reloj = relojBase; // Inicializa con el reloj fijo inicial

      @Override
      public LocalDateTime get() {
        return LocalDateTime.now(reloj);
      }

      @Override
      public void avanzarTiempo(Duration duracion) {
        reloj = Clock.offset(reloj, duracion);
      }
    };

    // Avanza una hora
    ((TiempoAvanzable) tiempoFicticio).avanzarTiempo(Duration.ofHours(1));

    LocalDateTime tiempoActual = tiempoFicticio.get();
    assertEquals(LocalDateTime.of(2023, 10, 1, 10, 0), tiempoActual);
    assertEquals(LocalDateTime.of(2023, 10, 1, 11, 0), tiempoFicticio.get());
  */
  }

}



