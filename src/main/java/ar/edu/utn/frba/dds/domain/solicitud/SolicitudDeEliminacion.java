package ar.edu.utn.frba.dds.domain.solicitud;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import lombok.Getter;

@Entity
public class SolicitudDeEliminacion {
  @Id
  @GeneratedValue
  private Long id;
  @Column
  @Getter
  private final String tituloHecho;
  @Column
  @Getter
  private final String motivo;
  @Enumerated(EnumType.STRING)
  @Getter
  private Estado estado = Estado.PENDIENTE;
  @OneToOne
  private final Fuente fuente;
  //@Transient
  //private static final List<SolicitudDeEliminacion> solicitudes = new ArrayList<>();

  public SolicitudDeEliminacion(String tituloHecho, String motivo, Fuente fuenteCsv) {
    this.tituloHecho = tituloHecho;
    this.motivo = motivo;
    this.fuente = fuenteCsv;
    validarSolicitud();
  }

  private void validarSolicitud() {
    requireNonNull(tituloHecho);
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