package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.domain.coleccion.Coleccion;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import ar.edu.utn.frba.dds.domain.fuente.FuenteAgregadora;
import ar.edu.utn.frba.dds.repo.RepoDeColecciones;
import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;
import java.util.List;


public class Main {
  public static void main(String[] args) {
    FuenteAgregadora agregadora = new FuenteAgregadora();

    agregadora.actualizarCache();
    System.out.println("Cache actualizado exitosamente.");

    //esto seria en tiempos de carga baja
    RepoDeColecciones repo = RepoDeColecciones.getInstance();
    repo.obtenerColecciones().forEach(Coleccion::actualizarHechosConsensuados);

    //actualizacion periodica de las fuentesDemos
    RepoFuentesDelSistema repoFuente = RepoFuentesDelSistema.getInstance();
    repoFuente.obtenerFuentes().forEach(Fuente::actualizarHechos);
  }
}
