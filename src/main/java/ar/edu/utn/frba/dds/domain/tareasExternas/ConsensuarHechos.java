package ar.edu.utn.frba.dds.domain.tareasExternas;

import ar.edu.utn.frba.dds.domain.coleccion.Coleccion;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import ar.edu.utn.frba.dds.repo.RepoDeColecciones;
import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;


public class ConsensuarHechos {

  public static void main(String[] args) {
    RepoDeColecciones repo = RepoDeColecciones.getInstance();
    repo.obtenerColecciones().forEach(Coleccion::actualizarHechosConsensuados);
  }

}
