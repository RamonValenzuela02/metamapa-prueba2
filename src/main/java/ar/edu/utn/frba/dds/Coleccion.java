package ar.edu.utn.frba.dds;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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


  public Coleccion(String titulo, String descripcion, Fuente fuente, Criterio criterio) {
    validarColeccion(titulo, descripcion, fuente, criterio);
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    this.criterio = criterio;
  }

  public List<Hecho> obtenerHechos() {
    return fuente.obtenerHechosConCriterio(criterio);
  }

  //TODO chequear esto:
  /*
  public void navegar() {
    fuente.obtenerHechosConCriterio(criterio).forEach(this::mostrarHecho);
  }

  public void navegarConFiltro(Criterio criterio) {
    fuente.obtenerHechosConCriterio(criterio);
  }

  public void mostrarHecho(Hecho hecho) {
    System.out.println("Titulo: " + hecho.getTitulo());
    System.out.println("Descripcion: " + hecho.getDescripcion());
    System.out.println("Categoria: " + hecho.getCategoria().toString());
    System.out.println("Latitud: " + hecho.getLatitud());
    System.out.println("Longitud: " + hecho.getLongitud());
    System.out.println("Fecha del Hecho: " + hecho.getFechaHecho());
    System.out.println("\n");
  }
   */

  private void validarColeccion(String titulo, String descripcion, Fuente fuente, Criterio criterio) {
    requireNonNull(titulo);
    requireNonNull(descripcion);
    requireNonNull(fuente);
    requireNonNull(criterio);
  }

}