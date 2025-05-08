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


  public Coleccion ColeccionDeIncendiosForestales(Categoria categoria) {
    //---------------------------------------------------------------------
    //ACUERDENSE DE SIEMPRE CAMBIAR EL PATH POR EL SUYO !!!!!!!!!!!!!!!!!!!
    //---------------------------------------------------------------------

    FuenteCSV fuenteCSV = new FuenteCSV("D:\\Facultad\\3er año\\Diseño de Sistemas\\TPA\\tpa-2025-26\\src\\test\\java\\ar\\edu\\utn\\frba\\dds\\prueba.csv");
    //Aca creamos una fuente que contenga todos los hechos que se encuentran en nuestro archivo

    Criterio criterio = new CriterioPorCategoria(categoria);
    //Aca creamos un criterio que contiene un filtro por categoría = INCENDIO_FORESTAL

    return new Coleccion("Incendios Forestales", "Test", fuenteCSV, criterio);
    //Aca creamos una colección, que tiene todos nuestros hechos de la fuente pero filtrados por el criterio recién
    //creado

    /*
    Explicación de porque no hace falta usar la función cargarHechos() manualmente: si vemos en el
    constructor de la clase colección, podemos ver que ya está utilizada dentro, por lo que al crear la
    colección ya ejecuta esa función automáticamente
     */

  }

  coleccion1 = ColeccionDeIncendiosForestales(INCENDIO_FORESTAL);

  @DisplayName("Viendo si se creo la coleccion segun el criterio")
  @Test
  public void hechosDeColeccionSegunLaCategoria() {
    assertEquals(3, .getHechos().size());
    //aca preguntamos si los hechos que están en la colección son iguales a dos

    coleccion.navegar()
  }

  }

