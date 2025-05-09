package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.time.LocalDate;
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
    validarNotNuLL( titulo, descripcion, fuente, criterio);
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    this.criterio = criterio;
    this.hechos = new ArrayList<>();
    cargarHechos();
  }
 //TODO chequear esto:
  public void cargarHechos() {
    hechos.addAll( fuente.getHechos().stream().filter(hecho -> criterio.cumpleCriterio(hecho)).toList() );
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

  private void validarNotNuLL(String titulo, String descripcion, Fuente fuente, Criterio criterio) {
    if(titulo == null) {
      throw new ColeccionInvalidoExeption("titulo");
    }
    if(descripcion == null) {
      throw new ColeccionInvalidoExeption("descripcion");
    }
    if(fuente == null) {
      throw new ColeccionInvalidoExeption("fuente");
    }
    if(criterio == null) {
      throw new ColeccionInvalidoExeption("criterio");
    }

  }

}

class ColeccionInvalidoExeption extends RuntimeException {

  public ColeccionInvalidoExeption(String causa) {
    super("No selecciono " + causa + " de la coleccion.");
  }

}