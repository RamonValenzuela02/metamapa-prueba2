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
  private final String handle;

  private static List<String> handlesUsados = new ArrayList<>();

  public Coleccion(String handle, String titulo, String descripcion, Fuente fuente, Criterio criterio) {
    validarColeccion(handle, titulo, descripcion, fuente, criterio);
    this.handle = handle;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    this.criterio = criterio;
    handlesUsados.add(handle);
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

  public void navegarConFiltro(Criterio criterio) {
    fuente.obtenerHechos().stream().filter(criterio::cumpleCriterio).forEach(this::mostrarHecho);
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

  private void validarColeccion(String handle, String titulo, String descripcion, Fuente fuente, Criterio criterio) {
    validarHandle(handle);
    requireNonNull(titulo);
    requireNonNull(descripcion);
    requireNonNull(fuente);
    requireNonNull(criterio);
  }

  private void validarHandle(String handle) {
    if (handle.contains(" ")) {
      throw new IllegalArgumentException("El handle no puede contain espacions");
    }
    if(handlesUsados.contains(handle)) {
      throw new IllegalArgumentException("El handle ya esta usado");
    }
  }
}