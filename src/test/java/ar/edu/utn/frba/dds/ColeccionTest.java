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
    //---------------------------------------------------------------------
    //ACUERDENSE DE SIEMPRE CAMBIAR EL PATH POR EL SUYO !!!!!!!!!!!!!!!!!!!
    //---------------------------------------------------------------------

    FuenteCSV fuenteCSV = new FuenteCSV("D:\\UTN pc\\3er año\\Diseño de Sistemas de Informacion\\tpa-2025-26\\src\\test\\java\\ar\\edu\\utn\\frba\\dds\\prueba.csv");
    //Aca creamos una fuente que contenga todos los hechos que se encuentran en nuestro archivo

    Criterio criterio = new CriterioPorCategoria(Categoria.INCENDIO_FORESTAL);
    //Aca creamos un criterio que contiene un filtro por categoría = INCENDIO_FORESTAL

    Coleccion coleccion = new Coleccion("Incendios Forestales", "Test", fuenteCSV, criterio);
    //Aca creamos una colección, que tiene todos nuestros hechos de la fuente pero filtrados por el criterio recién
    //creado

    /*
    Explicación de porque no hace falta usar la función cargarHechos() manualmente: si vemos en el
    constructor de la clase colección, podemos ver que ya está utilizada dentro, por lo que al crear la
    colección ya ejecuta esa función automáticamente
     */

    assertEquals(2, coleccion.getHechos().size());
    //aca preguntamos si los hechos que están en la colección son iguales a dos

    for (Hecho hecho : coleccion.getHechos()) {
      System.out.println("Título: " + hecho.getTitulo());
    }
    //este es un print que hice de gede para que muestre los hechos
  }
}
