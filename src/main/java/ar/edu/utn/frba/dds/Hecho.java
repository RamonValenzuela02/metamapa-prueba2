package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

public class Hecho {
  //Set<Etiqueta> etiquetas; //el set es un tipo de lista que no permite repeticion
  @Getter
  private String titulo;
  @Getter
  private String descripcion;
  @Getter
  private Categoria categoria;
  @Getter
  private String latitud;
  @Getter
  private String longitud;
  @Getter
  private LocalDate fechaHecho;
  private LocalDate fechaCarga;
  //origen;
  //tipo;


  // Preguntar si convendria hacer un
  public Hecho (String titulo, String descripcion, Categoria categoria, String latitud, String longitud, LocalDate fecha){
    validarNotNuLL(titulo, descripcion, categoria, latitud, longitud, fecha);
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaHecho = fecha;
  }

  private void validarNotNuLL(String titulo, String descripcion, Categoria categoria, String latitud, String longitud, LocalDate fecha) {
    if(titulo == null) {
      throw new HechoInvalidoExeption("titulo");
    }
    if(descripcion == null) {
      throw new HechoInvalidoExeption("descripcion");
    }
    if(categoria == null) {
      throw new HechoInvalidoExeption("categoria");
    }
    if(null == latitud) {
      throw new HechoInvalidoExeption("latitud");
    }
    if(null == longitud) {
      throw new HechoInvalidoExeption("longitud");
    }
    if(fecha == null) {
      throw new HechoInvalidoExeption("fecha");
    }
  }

  //TODO
  void agregarMultimedia(){}

  /*public void addEtiqueta(Etiqueta nuevaEtiqueta){ //es publico ya que el que va a etiquetarlo mas adelante es el administrador
    etiquetas.add(nuevaEtiqueta);
  }*/
}

class HechoInvalidoExeption extends RuntimeException {
  public HechoInvalidoExeption(String causa) {
    super("No selecciono " + causa + " del hecho.");
    }

}
