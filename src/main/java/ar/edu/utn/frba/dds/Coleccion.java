package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

  public void navegar() {
    hechos.forEach( this::mostrarHecho );
  }

  public void navegarConFiltro(Criterio criterio){
    hechos.stream().filter( criterio::cumpleCriterio ).forEach( this::mostrarHecho );
  }

  public void mostrarHecho(Hecho hecho){
    System.out.println("Titulo: " + hecho.getTitulo());
    System.out.println("Descripcion: " + hecho.getDescripcion());
    System.out.println("Categoria: " + hecho.getCategoria().toString());
    System.out.println("Latitud: " + hecho.getLatitud());
    System.out.println("Longitud: " + hecho.getLongitud());
    System.out.println("Fecha del Hecho: " + hecho.getFechaHecho());
    System.out.println("\n");
  }
}
