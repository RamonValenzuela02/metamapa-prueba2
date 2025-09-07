package ar.edu.utn.frba.dds.domain.solicitud;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.repo.RepoHechosDinamicos;
import ar.edu.utn.frba.dds.repo.RepoSolicitudesDinamicas;
import lombok.Getter;
import lombok.Setter;

public class SolicitudDinamica {
  @Getter
  private Hecho hecho;
  private EstadoSolicitudDinamica estado;
  @Setter
  private String sugerencia;

  public SolicitudDinamica(Hecho hecho /*, Runnable cuandoEsAceptada*/) {
    this.hecho = hecho;
    this.estado = EstadoSolicitudDinamica.PENDIENTE;
  }

  public void aceptar() {
    this.estado = EstadoSolicitudDinamica.ACEPTADA;
    RepoHechosDinamicos.getInstance().agregarHecho(hecho);
  }

  public void aceptarConSugerencia(String sugerencia) {
    this.sugerencia = sugerencia;
    this.estado = EstadoSolicitudDinamica.ACEPTADA_CON_SUGERENCIA;
    RepoHechosDinamicos.getInstance().agregarHecho(hecho);
  }

  public void rechazar() {
    this.estado = EstadoSolicitudDinamica.RECHAZADA;
  }

}