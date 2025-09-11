package ar.edu.utn.frba.dds.domain.criterio;

import ar.edu.utn.frba.dds.domain.Hecho;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import lombok.Getter;

import java.time.LocalDate;
@Entity
@Getter
@DiscriminatorValue("FechaCarga")
public class CriterioPorFechaCarga extends Criterio {
  @Column
  private final LocalDate fechaDesde;
  @Column
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