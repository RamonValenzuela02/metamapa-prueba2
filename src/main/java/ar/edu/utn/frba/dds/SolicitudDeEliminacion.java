package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.util.List;

@Getter
public class SolicitudDeEliminacion {
  String tituloHecho;
  String motivo;
  Estado estado = Estado.PENDIENTE;

  void aceptar(){
    this.estado = Estado.ACEPTADA;
  }

  void rechazar(){
    this.estado = Estado.RECHAZADA;
  }

  public SolicitudDeEliminacion(String tituloHecho, String motivo, List<SolicitudDeEliminacion> solicitudes) {
    this.tituloHecho = tituloHecho;
    this.motivo = motivo;
    solicitudes.add(this);
}

}
