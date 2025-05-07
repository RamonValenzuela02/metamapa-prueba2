package ar.edu.utn.frba.dds;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static ar.edu.utn.frba.dds.Categoria.INCENDIO_FORESTAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColeccionTest {

  Coleccion coleccion1;
  Hecho unHecho;
  Fuente unaFuente;

  @Test
  @DisplayName("Creo Coleccion")

  void crearColeccion() {
    FuenteCSV fuenteCSV = new FuenteCSV("D:\\UTN pc\\3er año\\Diseño de Sistemas de Informacion\\tpa-2025-26\\src\\test\\java\\ar\\edu\\utn\\frba\\dds\\prueba.csv");
    List<Hecho> hechos = fuenteCSV.getHechos();
    Criterio criterio = new CriterioPorCategoria(INCENDIO_FORESTAL);
    coleccion1 = new Coleccion("Incendios Forestales","Test", fuenteCSV, criterio);
    coleccion1.cargarHechos();
    assertEquals(2, coleccion1.hechos.size());
  }

}
