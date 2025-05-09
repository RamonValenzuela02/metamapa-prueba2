package ar.edu.utn.frba.dds;

import java.util.List;
import lombok.Getter;

/**
 * Clase que representa la solicitud de eliminacion de un Hecho.
 */
@Getter
public class SolicitudDeEliminacion {
  private final String tituloHecho;
  private final String motivo;
  private Estado estado = Estado.PENDIENTE;
  private final FuenteCsv fuenteCsv;

  /**
   * Contructor de objetos, en donde una vez creado lo agrega a la lista de solicitudes.
   */
  public SolicitudDeEliminacion(String tituloHecho, String motivo,
                                List<SolicitudDeEliminacion> solicitudes, FuenteCsv fuenteCsv) {
    this.tituloHecho = tituloHecho;
    this.motivo = motivo;
    this.fuenteCsv = fuenteCsv;
    solicitudes.add(this);
  }

  /**
   * Retorna y busca un hecho en su fuente por su titulo.
   */
  public Hecho buscarHechoporTitulo() {
    return fuenteCsv.getHechos().stream().filter(hecho -> hecho.getTitulo().equals(tituloHecho)).findFirst().orElse(null);
  }

  /**
   * Aceptar una solicitud imploca cambiarle el estado a ACEPTADA.
   */
  void aceptar() {
    this.estado = Estado.ACEPTADA;
  }

  /**
   * Rechazar una solicitud implica cambiarle el estado a RECHAZADA.
   */
  void rechazar() {
    this.estado = Estado.RECHAZADA;
  }
}
