package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CriterioPorFechaHecho implements Criterio {

  private final LocalDate fechaDesde;
  private final LocalDate fechaHasta;
  private final LocalDate fechaFiltro;

  public CriterioPorFechaHecho(String fechaFiltro, String fechaDesde, String fechaHasta) {
    this.fechaDesde = LocalDate.parse(fechaDesde);
    this.fechaHasta = LocalDate.parse(fechaHasta);
    this.fechaFiltro = LocalDate.parse(fechaFiltro);
  }

  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return !(hecho.getFechaHecho().isAfter(fechaHasta)) && !(hecho.getFechaHecho().isBefore(fechaDesde));
  }
}
