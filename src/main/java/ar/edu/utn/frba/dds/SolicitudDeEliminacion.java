package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.util.List;

@Getter
public class SolicitudDeEliminacion {
  String tituloHecho;
  String motivo;
  Estado estado = Estado.PENDIENTE;
  FuenteCSV fuenteCSV;
  public Hecho buscarHecho() {
    for (Hecho hechoBuscado : fuenteCSV.getHechos()) {
      if (hechoBuscado.getTitulo().equals(tituloHecho)) {
        return hechoBuscado;
      }
    }
    return null;
  }

  void aceptar(){
    this.estado = Estado.ACEPTADA;
  }

  void rechazar(){
    this.estado = Estado.RECHAZADA;
  }

  public SolicitudDeEliminacion(String tituloHecho, String motivo, List<SolicitudDeEliminacion> solicitudes, FuenteCSV fuenteCSV) {
    this.tituloHecho = tituloHecho;
    this.motivo = motivo;
    this.fuenteCSV = fuenteCSV;
    solicitudes.add(this);
}

}
