package ar.edu.utn.frba.dds.model.tareasExternas;

import ar.edu.utn.frba.dds.model.fuente.Fuente;
import ar.edu.utn.frba.dds.repositorios.RepoFuentesDelSistema;

public class ActualizarFuenteDemo {

  public static void main(String[] args) {
    RepoFuentesDelSistema repoFuente = RepoFuentesDelSistema.getInstance();
    repoFuente.obtenerFuentes().forEach(Fuente::actualizarHechos);
  }

}
