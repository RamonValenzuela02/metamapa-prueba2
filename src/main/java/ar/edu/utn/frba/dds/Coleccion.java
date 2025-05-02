package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Coleccion {
  @Getter
  String titulo;
  List<Hecho> hechos;
  String descripcion;
  Fuente fuente;
  Criterio criterio;

  // public Set<Hecho> mostrarHechos() {return hechos;  }

  //void eliminarColeccion(){}

  //void cambiarCriterio(){}

  public Coleccion(String titulo, String descripcion, Fuente fuente, Criterio criterio){
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    this.criterio = criterio;
    this.hechos = new ArrayList<>();

    cargarHechos();
  }

  private void cargarHechos() {
    for (Hecho hecho : fuente.getHechos()) {
      if(criterio.cumpleCriterio(hecho)){
        hechos.add(hecho);
      }
    }
  }
}
