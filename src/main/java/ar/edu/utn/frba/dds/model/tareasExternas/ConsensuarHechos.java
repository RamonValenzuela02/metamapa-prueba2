package ar.edu.utn.frba.dds.model.tareasExternas;

import ar.edu.utn.frba.dds.model.coleccion.Coleccion;
import ar.edu.utn.frba.dds.repositorios.RepoDeColecciones;


public class ConsensuarHechos {

  public static void main(String[] args) {
    RepoDeColecciones repo = RepoDeColecciones.getInstance();
    repo.obtenerColecciones().forEach(Coleccion::actualizarHechosConsensuados);
  }

}
