package ar.edu.utn.frba.dds.domain.criterio;

import ar.edu.utn.frba.dds.domain.Hecho;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("SiempreCumple")
public class CriterioCumplidorSiempre extends Criterio {
  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return true;
  }
}