package ar.edu.utn.frba.dds;


import lombok.Getter;

public class CriterioPorCategoria implements Criterio {
  @Getter
  private final Categoria categoria;


  public CriterioPorCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return hecho.getCategoria() == categoria;
  }
}