package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class CriterioPorFechaCarga implements Criterio {

  private final LocalDate fechaDesde;
  private final LocalDate fechaHasta;

  public CriterioPorFechaCarga(String fechaFiltro, String fechaDesde, String fechaHasta) {
    this.fechaDesde = LocalDate.parse(fechaDesde);
    this.fechaHasta = LocalDate.parse(fechaHasta);
  }

  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return !(hecho.getFechaCarga().isAfter(fechaHasta)) && !(hecho.getFechaCarga().isBefore(fechaDesde));
  }
}
