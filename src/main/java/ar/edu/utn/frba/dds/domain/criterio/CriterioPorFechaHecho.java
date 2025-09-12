
package ar.edu.utn.frba.dds.domain.criterio;

import ar.edu.utn.frba.dds.domain.Hecho;
import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
@DiscriminatorValue("FechaHecho")
public class CriterioPorFechaHecho extends Criterio {
  @Transient
  private final LocalDateTime fechaDesde;
  @Transient
  private final LocalDateTime fechaHasta;
  @Transient
  private final LocalDateTime fechaFiltro;

  public CriterioPorFechaHecho(String fechaFiltro, String fechaDesde, String fechaHasta) {
    this.fechaDesde = LocalDateTime.parse(fechaDesde);
    this.fechaHasta = LocalDateTime.parse(fechaHasta);
    this.fechaFiltro = LocalDateTime.parse(fechaFiltro);
  }

  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return !(hecho.getFechaHecho().isAfter(fechaHasta)) && !(hecho.getFechaHecho().isBefore(fechaDesde));
  }
}
