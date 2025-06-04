package ar.edu.utn.frba.dds;

import java.time.LocalDate;


public class CriterioPorFecha implements Criterio {
  private final LocalDate fecha;

  public CriterioPorFecha(String fecha) {
    this.fecha = LocalDate.parse(fecha);
  }

  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return hecho.getFechaHecho().equals(fecha);
  }
}
