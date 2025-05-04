package ar.edu.utn.frba.dds;

public class CriterioPorCategoria implements Criterio {
  Categoria categoria;

  @Override
  public Boolean cumpleCriterio(Hecho hecho){
    return hecho.getCategoria() == categoria;
  }
}
