package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.io.FileReader;
import java.util.List;

public abstract class Fuente {
//  String nombre;
//  @Getter
//  List<Hecho> hechos;
//
//  public Fuente(String nombre, List<Hecho> hechos) {
//    this.nombre = nombre;
//    this.hechos = hechos;
//  }

  public List<Hecho> getHechos() {
    FileReader file = openFile();
    List<Hecho> hechos = guardarHechos(file);
    closeFile(file);
    return hechos;
  }

  protected abstract FileReader openFile();
  protected abstract List<Hecho> guardarHechos(FileReader file);
  protected abstract void closeFile(FileReader file);
  }
