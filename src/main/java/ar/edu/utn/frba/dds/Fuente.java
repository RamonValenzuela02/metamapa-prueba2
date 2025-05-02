package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.util.List;

public class Fuente {
  String nombre;
  @Getter
  List<Hecho> hechos;

  public Fuente(String nombre, List<Hecho> hechos) {
    this.nombre = nombre;
    this.hechos = hechos;
  }
}
