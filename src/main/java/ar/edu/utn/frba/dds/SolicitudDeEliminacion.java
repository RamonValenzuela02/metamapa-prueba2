package ar.edu.utn.frba.dds;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;


public class SolicitudDeEliminacion {
  private final String tituloHecho;
  @Getter
  private final String motivo;
  @Getter
  private Estado estado = Estado.PENDIENTE;
  private final Fuente fuente;

  private static final List<SolicitudDeEliminacion> solicitudes = new ArrayList<>();

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

  void aceptar() {
    this.estado = Estado.ACEPTADA;
  }

  void rechazar() {
    this.estado = Estado.RECHAZADA;
  }

}
