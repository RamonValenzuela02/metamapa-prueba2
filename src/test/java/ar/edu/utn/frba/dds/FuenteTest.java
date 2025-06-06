package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FuenteTest {

  @Test
  public void lecturaCorrectaDeArchivoCSV() throws Exception { // req 2
    URL resource = getClass().getClassLoader().getResource("prueba.csv");
    if (resource == null) {
      throw new RuntimeException("No se encontró el archivo prueba.csv");
    }
    File csvFile = Paths.get(resource.toURI()).toFile();
    FuenteEstatica fuenteEstatica = new FuenteEstatica(csvFile.getAbsolutePath());
    Criterio criterio = new CriterioCumplidorSiempre();
    List<Hecho> hechos = fuenteEstatica.obtenerHechosConCriterio(criterio);


    assertEquals("Incendio Forestal en Bariloche", hechos.get(0).getTitulo());
    assertEquals("Hubo un incendio en Bariloche", hechos.get(0).getDescripcion());
    assertEquals(Categoria.INCENDIO_FORESTAL, hechos.get(0).getCategoria());
    assertEquals("11.2222", hechos.get(0).getLatitud());
    assertEquals("33.4444", hechos.get(0).getLongitud());
    assertEquals(LocalDate.parse("2025-04-05"), hechos.get(0).getFechaHecho());

    assertEquals("Accidente Fatal en General Paz", hechos.get(1).getTitulo());
    assertEquals("Accidente fatal en General Paz. Hubo 3 victimas.", hechos.get(1).getDescripcion());
    assertEquals(Categoria.ACCIDENTE_VIAL, hechos.get(1).getCategoria());
    assertEquals("55.6666", hechos.get(1).getLatitud());
    assertEquals("77.8888", hechos.get(1).getLongitud());
    assertEquals(LocalDate.parse("2025-04-05"), hechos.get(1).getFechaHecho());
  }
}
