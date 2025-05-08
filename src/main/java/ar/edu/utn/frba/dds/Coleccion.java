package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Coleccion {
  @Getter
  String titulo;
  @Getter
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

  public void cargarHechos() {
    for (Hecho hecho : fuente.getHechos()) {
      if(criterio.cumpleCriterio(hecho)){
        hechos.add(hecho);
      }
    }
  }

  public void navegar(Coleccion coleccion) {
    List<Hecho> hechos = coleccion.getHechos();
    for (Hecho hecho : hechos) {
      System.out.println("Titulo: " + hecho.getTitulo());
      System.out.println("Descripcion: " + hecho.getDescripcion());
      System.out.println("Categoria: " + hecho.getCategoria().toString());
      System.out.println("Latitud: " + hecho.getLatitud());
      System.out.println("Longitud: " + hecho.getLongitud());
      System.out.println("Fecha del Hecho: " + hecho.getFechaHecho());
    }
  }

  public void navegarConFiltro(Coleccion coleccion, Criterio criterio){}

}
