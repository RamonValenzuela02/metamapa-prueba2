package ar.edu.utn.frba.dds.domain.criterio;

import ar.edu.utn.frba.dds.domain.Hecho;

/**
 * Representa un criterio para filtrar hechos.
 */
public interface Criterio {
  Boolean cumpleCriterio(Hecho hecho);
}