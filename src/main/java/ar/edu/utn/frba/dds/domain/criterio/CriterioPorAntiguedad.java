package ar.edu.utn.frba.dds.domain.criterio;


import ar.edu.utn.frba.dds.domain.Hecho;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class CriterioPorAntiguedad extends Criterio {
  @Transient
  private LocalDateTime  fechaReferencia;
  @Transient
  private int antiguedad;

  public CriterioPorAntiguedad(LocalDateTime  fechaRefencia, int antiguedad ) {
    this.fechaReferencia = fechaRefencia;
    this.antiguedad = antiguedad;
  }

  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    Duration duracion = Duration.between(hecho.getFechaCarga(), fechaReferencia);
    return duracion.toHours() <= antiguedad;
  }
}