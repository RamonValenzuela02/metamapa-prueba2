package ar.edu.utn.frba.dds;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static ar.edu.utn.frba.dds.Categoria.ACCIDENTE_VIAL;
import static ar.edu.utn.frba.dds.Categoria.INCENDIO_FORESTAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class ColeccionTest {

  public Coleccion coleccionSegunCategoria(Categoria categoria) throws Exception {
    URL resource = getClass().getClassLoader().getResource("prueba.csv");
    if (resource == null) {
      throw new RuntimeException("No se encontró el archivo prueba.csv");
    }
    File csvFile = Paths.get(resource.toURI()).toFile();
    FuenteCsv fuenteCSV = new FuenteCsv(csvFile.getAbsolutePath());
     //Aca creamos una fuente que contenga todos los hechos que se encuentran en nuestro archivo

    Criterio criterio = new CriterioPorCategoria(categoria);
    //Aca creamos un criterio que contiene un filtro por categoría = INCENDIO_FORESTAL

    return new Coleccion("incendio2013","Incendios Forestales", "Test", fuenteCSV, criterio);
    //Aca creamos una colección, que tiene la fuente pero filtrados por el criterio recién

    /*
    Explicación de porque no hace falta usar la función cargarHechos() manualmente: si vemos en el
    constructor de la clase colección, podemos ver que ya está utilizada dentro, por lo que al crear la
    colección ya ejecuta esa función automáticamente
     */
  }

  @DisplayName("Como persona administradora, deseo crear una colección") // req 1
  @Test
  public void crearColeccion() throws Exception {
    Coleccion coleccion1 = coleccionSegunCategoria(INCENDIO_FORESTAL);
    assertEquals(4, coleccion1.getHechos().size());
  }

  @DisplayName("Como persona visualizadora, deseo navegar todos los hechos disponibles de una colección.") // req 3
  @Test
  public void cantidadDeIncendiosForestalesEs3() throws Exception {
    Coleccion coleccion = coleccionSegunCategoria(INCENDIO_FORESTAL);
    coleccion.navegar();
  }

  @Test
  public void navegacionColeccion() throws Exception { // req 4

    Coleccion coleccion2 = coleccionSegunCategoria(INCENDIO_FORESTAL);
    String fechaString = "2025-04-05";
    Criterio criterio = new CriterioPorFecha(fechaString);
    coleccion2.navegarConFiltro(criterio);;
  }

  @Test
  public void noSeHaberTenerDosColeccionesConMismoHandle() throws Exception {
    Coleccion coleccion1 = coleccionSegunCategoria(INCENDIO_FORESTAL);

    assertThrows(IllegalArgumentException.class, () -> {
      Coleccion coleccion2 = coleccionSegunCategoria(ACCIDENTE_VIAL);
    });
  }

}

