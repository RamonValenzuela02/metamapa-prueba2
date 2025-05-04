package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

public class Hecho {
  Set<Etiqueta> etiquetas; //el set es un tipo de lista que no permite repeticion
  @Getter
  private String titulo;
  @Getter
  private String descripcion;
  @Getter
  private Categoria categoria;
  @Getter
  private double latitud;
  @Getter
  private double longitud;
  @Getter
  private LocalDate fechaHecho;
  private LocalDate fechaCarga;
  //origen;
  //tipo;

  public Hecho (String titulo, String descripcion, Categoria categoria, double latitud, double longitud, LocalDate fecha){
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaHecho = fecha;
  }
  void agregarMultimedia(){}

  public void addEtiqueta(Etiqueta nuevaEtiqueta){ //es publico ya que el que va a etiquetarlo mas adelante es el administrador
    etiquetas.add(nuevaEtiqueta);
  }
}
