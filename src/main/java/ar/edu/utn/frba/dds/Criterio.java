package ar.edu.utn.frba.dds;

/**
 * Representa un criterio para filtrar hechos.
 */
public interface Criterio {
  /**
   * verifica si un hecho cumple o no con un determinado criterio.
   */
  Boolean cumpleCriterio(Hecho hecho);
}
