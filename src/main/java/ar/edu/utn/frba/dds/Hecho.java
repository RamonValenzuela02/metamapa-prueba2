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
  private LocalDate fechaCarga;
  //origen;
  //tipo;

  // Preguntar si convendria hacer un
  public Hecho (String titulo, String descripcion, Categoria categoria,
                String latitud, String longitud, LocalDate fecha){
    validarNotNull (titulo, descripcion, categoria, latitud, longitud, fecha);
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaHecho = fecha;
  }

  private void validarNotNull(String titulo, String descripcion, Categoria categoria,
                              String latitud, String longitud, LocalDate fecha) {
    if (titulo == null) {
      throw new HechoInvalidoExeption("titulo");
    }
    if (descripcion == null) {
      throw new HechoInvalidoExeption("descripcion");
    }
    if (categoria == null) {
      throw new HechoInvalidoExeption("categoria");
    }
    if (null == latitud) {
      throw new HechoInvalidoExeption("latitud");
    }
    if (null == longitud) {
      throw new HechoInvalidoExeption("longitud");
    }
    if (fecha == null) {
      throw new HechoInvalidoExeption("fecha");
    }
  }

  //TODO
  //void agregarMultimedia(){}

  /*public void addEtiqueta(Etiqueta nuevaEtiqueta){ //es publico ya que el que va a etiquetarlo mas adelante es el administrador
    etiquetas.add(nuevaEtiqueta);
  }*/
}

class HechoInvalidoExeption extends RuntimeException {
  public HechoInvalidoExeption(String causa) {
    super("No selecciono " + causa + " del hecho.");
    }

}
