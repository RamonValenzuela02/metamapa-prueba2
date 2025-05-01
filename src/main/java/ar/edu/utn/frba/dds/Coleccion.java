package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.util.Set;

public class Coleccion {
  @Getter
  String titulo;
  Set<Hecho> hechos;
  String descripcion;
  Fuente fuente;
  String criterio;

  void traerHechos(){
    Set<Hecho> hechosFuente;
    //hechos = hechosFuente.filter(criterio);
  }
  public Set<Hecho> mostrarHechos() {
    return hechos;
  }

  void eliminarColeccion(){}

  void cambiarCriterio(){}

  public Coleccion(String titulo, String descripcion, Fuente fuente){
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    //criterio = "";
  }
}
