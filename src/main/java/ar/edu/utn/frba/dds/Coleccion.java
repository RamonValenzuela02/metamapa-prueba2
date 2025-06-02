package ar.edu.utn.frba.dds;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

/**
 * Representa una colección de hechos basada en un criterio y una fuente.
 */
public class Coleccion {
  @Getter
  private final String titulo;
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
  }

  public List<Hecho> getHechos() {
    return fuente.obtenerHechos();
  }

  //TODO chequear esto:

  /**
   * nagega/muestra todos los hechos .
   */

  public void navegar() {
    fuente.obtenerHechos().forEach(this::mostrarHecho);
  }

  /**
   * nagega/muetra los hechos que cumplen con criterio .
   */
  public void navegarConFiltro(Criterio criterio) {
    fuente.obtenerHechos().stream().filter(criterio::cumpleCriterio).forEach(this::mostrarHecho);
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
    requireNonNull(titulo);
    requireNonNull(descripcion);
    requireNonNull(fuente);
    requireNonNull(criterio);
  }
}