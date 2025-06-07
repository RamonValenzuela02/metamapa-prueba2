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
  private final String handle;
  @Getter
  private final String titulo;
  @Getter
  private final String descripcion;
  private final Fuente fuente;
  private final Criterio criterio;


  public Coleccion(String handle, String titulo, String descripcion, Fuente fuente, Criterio criterio) {
    validarColeccion(handle, titulo, descripcion, fuente, criterio);
    this.handle = handle; //es un alias que se le da a una coleccion que sirve para identificarla cuando la exponemos por API REST
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    this.criterio = criterio;
  }

  public List<Hecho> obtenerHechos() {
    return fuente.obtenerHechosConCriterio(criterio);
  }

  private void validarColeccion(String handle, String titulo, String descripcion, Fuente fuente, Criterio criterio) {
    requireNonNull(handle);
    requireNonNull(titulo);
    requireNonNull(descripcion);
    requireNonNull(fuente);
    requireNonNull(criterio);

    if (!handle.matches("^[a-zA-Z0-9_-]+$")) {
      throw new IllegalArgumentException("El handle debe ser alfanumérico, sin espacios.");
    } //Aca nos fijamos si cumple con las condiciones que debe tener el handler
  }

}