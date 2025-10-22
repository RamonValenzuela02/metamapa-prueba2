package ar.edu.utn.frba.dds.model.solicitud;

import static java.util.Objects.requireNonNull;

import ar.edu.utn.frba.dds.model.Hecho.Hecho;
import ar.edu.utn.frba.dds.model.fuente.Fuente;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;

@Entity
public class SolicitudDeEliminacion {
  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne
  @Getter
  private Hecho hecho;
  @Column
  @Getter
  private String motivo;
  @Enumerated(EnumType.STRING)
  @Getter
  private Estado estado = Estado.PENDIENTE;
  @OneToOne
  private Fuente fuente;

  protected SolicitudDeEliminacion() {};

  public SolicitudDeEliminacion(Hecho hecho, String motivo, Fuente fuenteCsv) {
    this.hecho = hecho;
    this.motivo = motivo;
    this.fuente = fuenteCsv;
    validarSolicitud();
  }

  private void validarSolicitud() {
    requireNonNull(hecho);
    requireNonNull(motivo);
    requireNonNull(fuente);
  }

  public void aceptar() {
    this.estado = Estado.ACEPTADA;
  }

  public void rechazar() {
    this.estado = Estado.RECHAZADA;
  }

}