package ar.edu.utn.frba.dds;

/**
 * Representa un criterio para filtrar hechos.
 */
public interface Criterio {
  Boolean cumpleCriterio(Hecho hecho);
}