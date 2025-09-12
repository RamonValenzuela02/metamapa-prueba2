package ar.edu.utn.frba.dds.domain.tareasExternas;

import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;

public class ActualizarFuenteDemo {

  public static void main(String[] args) {
    RepoFuentesDelSistema repoFuente = RepoFuentesDelSistema.getInstance();
    repoFuente.obtenerFuentes().forEach(Fuente::actualizarHechos);
  }

}
