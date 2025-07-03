package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FuenteEstaticaTest {

  @Test
  public void lecturaCorrectaDeArchivoCSV() throws Exception {
    URL resource = getClass().getClassLoader().getResource("prueba.csv");
    if (resource == null) {
      throw new RuntimeException("No se encontró el archivo prueba.csv");
    }

    File csvFile = Paths.get(resource.toURI()).toFile();
    FuenteEstatica fuenteEstatica = new FuenteEstatica(csvFile.getAbsolutePath());

    List<Hecho> hechos = fuenteEstatica.obtenerHechos();

    assertEquals(4, hechos.size());

    Hecho hecho1 = hechos.get(0);
    assertEquals("Incendio Forestal en Bariloche", hecho1.getTitulo());
    assertEquals("Hubo un incendio en Bariloche", hecho1.getDescripcion());
    assertEquals(Categoria.INCENDIO_FORESTAL, hecho1.getCategoria());
    assertEquals("11.2222", hecho1.getLatitud());
    assertEquals("33.4444", hecho1.getLongitud());
    assertEquals(LocalDate.parse("2025-04-05"), hecho1.getFechaHecho());

    Hecho hecho2 = hechos.get(1);
    assertEquals("Accidente Fatal en General Paz", hecho2.getTitulo());
    assertEquals("Accidente fatal en General Paz. Hubo 3 victimas.", hecho2.getDescripcion());
    assertEquals(Categoria.ACCIDENTE_VIAL, hecho2.getCategoria());
    assertEquals("55.6666", hecho2.getLatitud());
    assertEquals("77.8888", hecho2.getLongitud());
    assertEquals(LocalDate.parse("2025-04-05"), hecho2.getFechaHecho());

    Hecho hecho3 = hechos.get(2);
    assertEquals("Incendio Forestal en Peru", hecho3.getTitulo());
    assertEquals("Hubo un incendio en la capital", hecho3.getDescripcion());
    assertEquals(Categoria.INCENDIO_FORESTAL, hecho3.getCategoria());
    assertEquals("55.6666", hecho3.getLatitud());
    assertEquals("77.8888", hecho3.getLongitud());
    assertEquals(LocalDate.parse("2025-05-07"), hecho3.getFechaHecho());

    Hecho hecho4 = hechos.get(3);
    assertEquals("Incendio Forestal en Cordoba", hecho4.getTitulo());
    assertEquals("Hubo un incendio en Cordoba", hecho4.getDescripcion());
    assertEquals(Categoria.INCENDIO_FORESTAL, hecho4.getCategoria());
    assertEquals("55.6666", hecho4.getLatitud());
    assertEquals("98.5521", hecho4.getLongitud());
    assertEquals(LocalDate.parse("2025-04-05"), hecho4.getFechaHecho());
  }

}


