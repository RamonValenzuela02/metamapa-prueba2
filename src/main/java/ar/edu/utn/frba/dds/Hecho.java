package ar.edu.utn.frba.dds;

import java.time.LocalDate;
import java.util.Set;

public class Hecho {
  Set<Etiqueta> etiquetas; //el set es un tipo de lista que no permite repeticion
  String titulo;
  String descripcion;
  String categoria;
  Ubicacion lugarAcontecimiento;
  LocalDate fechaAcontecimiento;
  LocalDate fechaCarga;
  //origen;
  //tipo;

  void agregarMultimedia(){}

  public void addEtiqueta(Etiqueta nuevaEtiqueta){ //es publico ya que el que va a etiquetarlo mas adelante es el administrador
    etiquetas.add(nuevaEtiqueta);
  }
}
