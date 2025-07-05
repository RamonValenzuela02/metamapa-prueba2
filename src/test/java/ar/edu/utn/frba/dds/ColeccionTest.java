package ar.edu.utn.frba.dds;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static ar.edu.utn.frba.dds.Categoria.INCENDIO_FORESTAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ColeccionTest {

  @Test
  @DisplayName("Como persona administradora, deseo crear una colección")
  public void crearColeccion() {
    Criterio criterio = new CriterioCumplidorSiempre();
    List<Criterio> criterios = List.of(criterio);

    FuenteEstatica fuente = new FuenteEstatica("src/test/resources/prueba.csv");

    Coleccion coleccion = new ColeccionBuilder()
        .conHandle("ak1fjd1")
        .conTitulo("Incendios")
        .conDescripcion("Incendios en el norte")
        .conFuente(fuente)
        .conCriterios(criterios)
        .conModoNavegacion(ModoNavegacion.IRRESTRICTA)
        .crear();

    assertEquals(4, coleccion.getHechos().size());
  }

  @DisplayName("Como persona visualizadora, deseo navegar todos los hechos disponibles de una colección, con algun filtro.")
  @Test
  public void cantidadDeIncendiosForestalesEs3() {

    Criterio criterio = new CriterioPorCategoria(Categoria.INCENDIO_FORESTAL);
    List<Criterio> criterios = List.of(criterio);

    FuenteEstatica fuente = new FuenteEstatica("src/test/resources/prueba.csv");

    Coleccion coleccion = new ColeccionBuilder()
        .conHandle("ak1fjd1")
        .conTitulo("Incendios")
        .conDescripcion("Incendios en el norte")
        .conFuente(fuente)
        .conCriterios(criterios)
        .conModoNavegacion(ModoNavegacion.IRRESTRICTA)
        .crear();

    assertEquals(3, coleccion.getHechos().size());
  }
  @Test
  public void noSeMuestranHechosEliminadosEnUnaColeccion() {

    Criterio criterio = new CriterioPorCategoria(Categoria.INCENDIO_FORESTAL);
    List<Criterio> criterios = List.of(criterio);

    Hecho hecho = new Hecho("habia una vez",
            "holaholhola",
            Categoria.INCENDIO_FORESTAL,
            "1234",
            "5678",
            LocalDate.now(),
            LocalDate.now());
    hecho.marcarComoEliminado();

    FuenteDinamica fuente = new FuenteDinamica();
    fuente.agregarHecho(hecho);

    Coleccion coleccion = new ColeccionBuilder()
            .conHandle("ak1fjd1")
            .conTitulo(hecho.getTitulo())
            .conDescripcion(hecho.getDescripcion())
            .conFuente(fuente)
            .conCriterios(criterios)
            .conModoNavegacion(ModoNavegacion.IRRESTRICTA)
            .crear();

    List<Hecho> hechosColeccion = coleccion.getHechos();

    assertTrue(hechosColeccion.isEmpty());
  }
}
  /*
  @Test
  public void navegacionColeccion() {

    Coleccion coleccion2 = coleccionSegunCategoria(INCENDIO_FORESTAL);
    String fechaString = "2025-04-05";
    Criterio criterio = new CriterioPorFecha(fechaString);
    coleccion2.navegarConFiltro(criterio);;
  }
  */
}
