package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class FuenteDinamica extends Fuente{
  @Getter
  private List<SolicitudDinamica> pendientes;
  private RepoHechosDinamicos repositorio;

  public FuenteDinamica() {
    hechos = new ArrayList<>();
    pendientes = new ArrayList<>();
    repositorio = RepoHechosDinamicos.getInstance();
  }

  //modificar despues
  public void agregarHecho(Hecho hecho) {
    //en donde una vez que se acepte ese hecho se agregue a la lista de hechos.
    SolicitudDinamica solicitud = new SolicitudDinamica(hecho, () -> repositorio.agregarHecho(hecho));

    solicitud.setCallbackCuandoEsTratada(()-> pendientes.remove(solicitud));
    pendientes.add(solicitud);
  }

  public List<Hecho> obtenerHechos() {
    return repositorio.getHechos();
  }
}