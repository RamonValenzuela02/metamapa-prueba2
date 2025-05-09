package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.util.List;

/**
 * Clase que representa la solicitud de eliminacion de un Hecho.
 */
@Getter
public class SolicitudDeEliminacion {
  private String tituloHecho;
  private String motivo;
  private Estado estado = Estado.PENDIENTE;
  private FuenteCSV fuenteCSV;

  /**
   * Contructor de objetos, en donde una vez creado lo agrega a la lista de solicitudes.
   */
  public SolicitudDeEliminacion(String tituloHecho, String motivo, List<SolicitudDeEliminacion> solicitudes, FuenteCSV fuenteCSV) {
    this.tituloHecho = tituloHecho;
    this.motivo = motivo;
    this.fuenteCSV = fuenteCSV;
    solicitudes.add(this);
  }

  /**
   * Retorna y busca un hecho en su fuente por su titulo.
   */
  public Hecho buscarHecho() {
    for (Hecho hechoBuscado : fuenteCSV.getHechos()) {
      if (hechoBuscado.getTitulo().equals(tituloHecho)) {
        return hechoBuscado;
      }
    }
    return null;
  }

  /**
   * Aceptar una solicitud imploca cambiarle el estado a ACEPTADA.
   */
  void aceptar(){
    this.estado = Estado.ACEPTADA;
  }

  /**
   * Rechazar una solicitud implica cambiarle el estado a RECHAZADA.
   */
  void rechazar(){
    this.estado = Estado.RECHAZADA;
  }
}
