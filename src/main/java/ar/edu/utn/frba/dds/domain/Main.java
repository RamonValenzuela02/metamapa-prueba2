package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.domain.coleccion.Coleccion;
import ar.edu.utn.frba.dds.domain.fuente.FuenteAgregadora;
import ar.edu.utn.frba.dds.repo.RepoDeColecciones;
import java.util.List;


public class Main {
  public static void main(String[] args) {
    FuenteAgregadora agregadora = new FuenteAgregadora();

    agregadora.actualizarCache();
    System.out.println("Cache actualizado exitosamente.");
  }
}
