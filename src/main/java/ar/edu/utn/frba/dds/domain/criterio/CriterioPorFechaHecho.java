
package ar.edu.utn.frba.dds.domain.criterio;

import ar.edu.utn.frba.dds.domain.Hecho;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
@DiscriminatorValue("FechaHecho")
public class CriterioPorFechaHecho extends Criterio {
  @Column
  private LocalDateTime fechaDesde;
  @Column
  private LocalDateTime fechaHasta;
  @Column
  private LocalDateTime fechaFiltro;

  public CriterioPorFechaHecho(String fechaFiltro, String fechaDesde, String fechaHasta) {
    this.fechaDesde = LocalDateTime.parse(fechaDesde);
    this.fechaHasta = LocalDateTime.parse(fechaHasta);
    this.fechaFiltro = LocalDateTime.parse(fechaFiltro);
  }

  public CriterioPorFechaHecho() {};

  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return !(hecho.getFechaHecho().isAfter(fechaHasta)) && !(hecho.getFechaHecho().isBefore(fechaDesde));
  }
}
