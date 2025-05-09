package ar.edu.utn.frba.dds;

import java.time.LocalDate;

/**
 * Representa un criterio que filtra hechos por una fecha específica.
 */
public class CriterioPorFecha implements Criterio {
  private final LocalDate fecha;

  /**
   * Crea un criterio que filtra por fecha exacta.
   */
  public CriterioPorFecha(String fecha) {
    this.fecha = LocalDate.parse(fecha);
  }

  /**
   * Verifica si el hecho ocurrió en la fecha especificada.
   */
  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return hecho.getFechaHecho().equals(fecha);
  }
}
