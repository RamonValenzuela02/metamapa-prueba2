package ar.edu.utn.frba.dds.domain.criterio;

import ar.edu.utn.frba.dds.domain.Hecho;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Representa un criterio para filtrar hechos.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//me falta poner los discriminatorValues
public abstract class Criterio {
  @Id
  @GeneratedValue
  private long id;
  public abstract Boolean cumpleCriterio(Hecho hecho);
}