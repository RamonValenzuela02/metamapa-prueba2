package ar.edu.utn.frba.dds;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static ar.edu.utn.frba.dds.Categoria.INCENDIO_FORESTAL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ColeccionTest {
/*
  public Coleccion coleccionSegunCategoria(Categoria categoria) throws Exception {
    URL resource = getClass().getClassLoader().getResource("prueba.csv");
    if (resource == null) {
      throw new RuntimeException("No se encontró el archivo prueba.csv");
    }
    File csvFile = Paths.get(resource.toURI()).toFile();
    FuenteEstatica fuenteEstatica = new FuenteEstatica(csvFile.getAbsolutePath());


    Criterio criterio = new CriterioPorCategoria(categoria);


    return new Coleccion("Incendios","Incendios Forestales", "Test", fuenteEstatica, criterio);
    //Aca creamos una colección, que tiene la fuente pero filtrados por el criterio recién

    }


  private FuenteMetaMapa fuenteMock;
  private Coleccion coleccion;

  @BeforeEach
  public void setup() {
    // Creamos el mock de la fuente
    fuenteMock = mock(FuenteMetaMapa.class);

    // Creamos dos hechos
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

    // Definimos qué devuelve el mock cuando se llama a obtenerHechosConCriterio
    when(fuenteMock.obtenerHechosConCriterio(any(Criterio.class)))
        .thenReturn(List.of(hecho1, hecho2));

    // Creamos un criterio dummy para usar en la coleccion
    Criterio criterioDummy = mock(Criterio.class);

    coleccion = new Coleccion("handle_test","Titulo Test", "Descripcion Test", fuenteMock, criterioDummy);
  }
*/

  @Test
  @DisplayName("Como persona administradora, deseo crear una colección")
  public void crearColeccion() throws Exception {
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
  public void cantidadDeIncendiosForestalesEs3() throws Exception {
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


/*
  @Test
  public void navegacionColeccion() throws Exception { // req 4

    Coleccion coleccion2 = coleccionSegunCategoria(INCENDIO_FORESTAL);
    String fechaString = "2025-04-05";
    Criterio criterio = new CriterioPorFecha(fechaString);
    coleccion2.navegarConFiltro(criterio);;
  }

*/
}
