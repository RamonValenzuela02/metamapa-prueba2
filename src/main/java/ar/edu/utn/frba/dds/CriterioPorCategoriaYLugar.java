package ar.edu.utn.frba.dds;

/**
 * Representa un criterio que filtra hechos por categoría y ubicación (latitud y longitud).
 */
public class CriterioPorCategoriaYLugar implements Criterio {

  private Categoria categoria;
  private String latitud;
  private String longitud;

  /**
   * Crea un criterio que filtra por categoría y ubicación específica.
   */
  public CriterioPorCategoriaYLugar(Categoria categoria, String latitud, String longitud) {
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
  }

  /**
   * Verifica si el hecho cumple con la categoría y la ubicación especificadas.
   */
  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return hecho.getCategoria() == categoria
        && hecho.getLatitud().equals(latitud)
        && hecho.getLongitud().equals(longitud);
  }
}
