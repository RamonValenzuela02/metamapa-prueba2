package ar.edu.utn.frba.dds;

import java.util.ArrayList;

public class CriterioPorCategoria implements Criterio {
  Categoria categoria;


  @Override
  public Boolean cumpleCriterio(Hecho hecho){
    return hecho.getCategoria() == categoria;
  }

  public CriterioPorCategoria(Categoria categoria){
    this.categoria = categoria;
  }

}
