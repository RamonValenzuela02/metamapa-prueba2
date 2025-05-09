package ar.edu.utn.frba.dds;

/**
 * representa los criterios que van a filtrar por categoria.
 */
public class CriterioPorCategoria implements Criterio {

  private final Categoria categoria;

  /**
   * constructor de objeto  .
   */
  public CriterioPorCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  /**
   * verifica si un hecho cumple o no con un determinado criterio.
   */
  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return hecho.getCategoria() == categoria;
  }
}
