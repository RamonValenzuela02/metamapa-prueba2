package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * Representa una colección de hechos basada en un criterio y una fuente.
 */
public class Coleccion {
  @Getter
  private final String titulo;
  @Getter
  private final List<Hecho> hechos;
  @Getter
  private final String descripcion;
  private final Fuente fuente;
  private final Criterio criterio;


  // public Set<Hecho> mostrarHechos() {return hechos;  }
  //void eliminarColeccion(){}
  //void cambiarCriterio(){}
  /**
   * Crea una nueva colección y carga los hechos de la fuente .
   */
  public Coleccion(String titulo, String descripcion, Fuente fuente, Criterio criterio) {
    validarNotNull(titulo, descripcion, fuente, criterio);
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    this.criterio = criterio;
    this.hechos = new ArrayList<>();
    cargarHechos();
  }

  //TODO chequear esto:

  /**
   * carga los hechos de la fuente .
   */
  public void cargarHechos() {
    hechos.addAll(
        fuente.getHechos().stream()
            .filter(criterio::cumpleCriterio)
            .toList()
    );
  }

  /**
   * nagega/muestra todos los hechos .
   */
  public void navegar() {
    hechos.forEach(this::mostrarHecho);
  }

  /**
   * nagega/muetra los hechos que cumplen con criterio .
   */
  public void navegarConFiltro(Criterio criterio) {
    hechos.stream().filter(criterio::cumpleCriterio).forEach(this::mostrarHecho);
  }

  /**
   * muestra un hecho .
   */
  public void mostrarHecho(Hecho hecho) {
    System.out.println("Titulo: " + hecho.getTitulo());
    System.out.println("Descripcion: " + hecho.getDescripcion());
    System.out.println("Categoria: " + hecho.getCategoria().toString());
    System.out.println("Latitud: " + hecho.getLatitud());
    System.out.println("Longitud: " + hecho.getLongitud());
    System.out.println("Fecha del Hecho: " + hecho.getFechaHecho());
    System.out.println("\n");
  }

  /**
   * valida que los datos ingresados no sean NULL.
   */
  private void validarNotNull(String titulo, String descripcion, Fuente fuente, Criterio criterio) {
    if (titulo == null) {
      throw new ColeccionInvalidaException("titulo");
    }
    if (descripcion == null) {
      throw new ColeccionInvalidaException("descripcion");
    }
    if (fuente == null) {
      throw new ColeccionInvalidaException("fuente");
    }
    if (criterio == null) {
      throw new ColeccionInvalidaException("criterio");
    }
  }
}