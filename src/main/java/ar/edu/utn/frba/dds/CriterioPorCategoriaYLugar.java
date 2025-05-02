package ar.edu.utn.frba.dds;

public class CriterioPorCategoriaYLugar implements Criterio {

  Categoria categoria;
  Ubicacion lugarHecho;

  public CriterioPorCategoriaYLugar(Categoria categoria, Ubicacion lugarHecho){
    this.categoria = categoria;
    this.lugarHecho = lugarHecho;
  }

  @Override
  public Boolean cumpleCriterio(Hecho hecho){
    return hecho.categoria == categoria && hecho.lugarHecho == lugarHecho;
  }
}
