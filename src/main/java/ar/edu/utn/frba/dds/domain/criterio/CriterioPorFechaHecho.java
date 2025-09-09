
package ar.edu.utn.frba.dds.domain.criterio;

import ar.edu.utn.frba.dds.domain.Hecho;
import javax.persistence.Entity;
import javax.persistence.Transient;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class CriterioPorFechaHecho extends Criterio {
  @Transient
  private final LocalDate fechaDesde;
  @Transient
  private final LocalDate fechaHasta;
  @Transient
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
