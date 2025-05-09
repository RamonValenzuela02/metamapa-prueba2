package ar.edu.utn.frba.dds;

import java.time.LocalDate;
import lombok.Getter;

/**
 * Representa un HECHO. Estos van a estar disponibles en las fuentes
 * y van a poder pertenecer a Colecciones y ser consultados por usuarios.
 */
public class Hecho {
  //Set<Etiqueta> etiquetas;
  @Getter
  private final String titulo;
  @Getter
  private final String descripcion;
  @Getter
  private final Categoria categoria;
  @Getter
  private final String latitud;
  @Getter
  private final String longitud;
  @Getter
  private final LocalDate fechaHecho;
  @Getter
  private LocalDate fechaCarga;
  //origen;
  //tipo;

  /**
   * Constructor.
   *
   * @param titulo Es el titulo del Hecho.
   * @param descripcion La descripcion del Hecho.
   * @param categoria La Categoria del Hecho. Por ejemplo: INCENDIO_FORESTAL
   * @param latitud Latitud del origen del Hecho.
   * @param longitud Longitud del origen del Hecho. Parte de su ubicacion geografica.
   * @param fecha Fecha del Hecho.
   */
  public Hecho(String titulo, String descripcion, Categoria categoria,
                String latitud, String longitud, LocalDate fecha) {
    validarNotNull(titulo, descripcion, categoria, latitud, longitud, fecha);
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaHecho = fecha;
    this.fechaCarga = LocalDate.now();
  }

  private void validarNotNull(String titulo, String descripcion, Categoria categoria,
                              String latitud, String longitud, LocalDate fecha) {
    if (titulo == null) {
      throw new HechoInvalidoException("titulo");
    }
    if (descripcion == null) {
      throw new HechoInvalidoException("descripcion");
    }
    if (categoria == null) {
      throw new HechoInvalidoException("categoria");
    }
    if (null == latitud) {
      throw new HechoInvalidoException("latitud");
    }
    if (null == longitud) {
      throw new HechoInvalidoException("longitud");
    }
    if (fecha == null) {
      throw new HechoInvalidoException("fecha");
    }
  }

  //TODO
  //void agregarMultimedia(){}

  /*public void addEtiqueta(Etiqueta nuevaEtiqueta){
    es publico ya que el que va a etiquetarlo  mas adelante es el administrador
    etiquetas.add(nuevaEtiqueta);
  }*/
}
