package ar.edu.utn.frba.dds;

public class SolicitudDeEliminacion {
  Hecho hecho;
  String motivo;
  Estado estado = Estado.PENDIENTE;

  void aceptar(){
    this.estado = Estado.ACEPTADA;
  }

  void rechazar(){
    this.estado = Estado.RECHAZADA;
  }
public SolicitudDeEliminacion(Hecho hecho, String motivo) {
    this.hecho = hecho;
    this.motivo = motivo;
}

}
