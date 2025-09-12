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
@DiscriminatorValue("FechaCarga")
public class CriterioPorFechaCarga extends Criterio {
  @Column
  private LocalDateTime fechaDesde;
  @Column
  private LocalDateTime fechaHasta;

  public CriterioPorFechaCarga(String fechaFiltro, String fechaDesde, String fechaHasta) {
    this.fechaDesde = LocalDateTime.parse(fechaDesde);
    this.fechaHasta = LocalDateTime.parse(fechaHasta);
  }

  public CriterioPorFechaCarga() {};

  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return !(hecho.getFechaCarga().isAfter(fechaHasta)) && !(hecho.getFechaCarga().isBefore(fechaDesde));
  }
}