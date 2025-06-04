package ar.edu.utn.frba.dds;

/**
 * Representa un criterio que filtra hechos por categoría y ubicación (latitud y longitud).
 */
public class CriterioPorCategoriayLugar implements Criterio {
  private final Categoria categoria;
  private final String latitud;
  private final String longitud;

  public CriterioPorCategoriayLugar(Categoria categoria, String latitud, String longitud) {
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
  }

  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return hecho.getCategoria() == categoria
        && hecho.getLatitud().equals(latitud)
        && hecho.getLongitud().equals(longitud);
  }
}
