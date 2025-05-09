package ar.edu.utn.frba.dds;

import java.time.LocalDate;

public class CriterioXfecha implements Criterio {
  LocalDate fecha;
  public CriterioXfecha(String fecha) {
    this.fecha = LocalDate.parse(fecha);
  }
  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return hecho.getFechaHecho().equals(fecha);
  }
}
