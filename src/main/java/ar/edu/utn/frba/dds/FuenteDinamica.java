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
    //en donde cada vez que se agreguen hechos los va a agregar un repo que si es aceptado los pasa a repoDeSolAcep
    RepoSolicitudesDinamicasPendientes.getInstance().agregarSolicitud(hecho);
  }

  public List<Hecho> obtenerHechos() {
    return repositorio.getHechos();
  }
}