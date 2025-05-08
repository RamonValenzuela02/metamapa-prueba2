package ar.edu.utn.frba.dds;

public class CriterioPorCategoriaYLugar implements Criterio {

  Categoria categoria;
  String latitud;
  String longitud;

  public CriterioPorCategoriaYLugar(Categoria categoria, String latitud, String longitud) {
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
  }

  @Override
  public Boolean cumpleCriterio(Hecho hecho){
    return hecho.getCategoria() == categoria && hecho.getLatitud().equals(latitud)  && hecho.getLongitud().equals(longitud);
  }
}
