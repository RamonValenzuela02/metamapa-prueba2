package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

public interface Fuente {
  public  List<Hecho> obtenerHechosConCriterio(Criterio criterio);

  public default List<Hecho> obtenerHechosConCriterios(List<Criterio> criterios) {
    List<Hecho> hechosCumplidores = new ArrayList<>();
    for(Criterio criterio : criterios) {
      hechosCumplidores.addAll(obtenerHechosConCriterio(criterio));
    }
    return hechosCumplidores;
  }
}
