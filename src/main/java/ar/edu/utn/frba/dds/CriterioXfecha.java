package ar.edu.utn.frba.dds;

import java.time.LocalDate;

/**
 * Representa un criterio que filtra hechos por una fecha específica.
 */
public class CriterioXfecha implements Criterio {
  private LocalDate fecha;

  /**
   * Crea un criterio que filtra por fecha exacta.
   */
  public CriterioXfecha(String fecha) {
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
