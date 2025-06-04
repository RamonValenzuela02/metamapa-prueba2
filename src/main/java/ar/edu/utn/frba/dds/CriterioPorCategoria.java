package ar.edu.utn.frba.dds;


public class CriterioPorCategoria implements Criterio {
  private final Categoria categoria;


  public CriterioPorCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return hecho.getCategoria() == categoria;
  }
}
