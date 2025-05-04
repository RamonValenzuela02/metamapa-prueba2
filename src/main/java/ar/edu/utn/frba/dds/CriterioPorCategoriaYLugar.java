package ar.edu.utn.frba.dds;

public class CriterioPorCategoriaYLugar implements Criterio {

  Categoria categoria;
  double latitud;
  double longitud;

  public CriterioPorCategoriaYLugar(Categoria categoria, double latitud, double longitud) {
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
  }

  @Override
  public Boolean cumpleCriterio(Hecho hecho){
    return hecho.getCategoria() == categoria && hecho.getLatitud() == latitud && hecho.getLongitud() == longitud;
  }
}
