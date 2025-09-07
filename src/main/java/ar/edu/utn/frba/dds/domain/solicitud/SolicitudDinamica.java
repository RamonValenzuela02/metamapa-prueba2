package ar.edu.utn.frba.dds.domain.solicitud;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.repo.RepoHechosDinamicos;
import ar.edu.utn.frba.dds.repo.RepoSolicitudesDinamicas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
public class SolicitudDinamica {
  @Id
  @GeneratedValue
  private Long id;
  @Transient
  @Getter
  private Hecho hecho;
  @Enumerated(EnumType.STRING)
  private EstadoSolicitudDinamica estado;
  @Column
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