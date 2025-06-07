package ar.edu.utn.frba.dds;

import java.util.List;
import java.util.stream.Collectors;

public interface Fuente {
  public List<Hecho> obtenerHechosConCriterio(Criterio criterio);

  public default List<Hecho> obtenerHechosConVariosCriterios(List<Hecho> hechosAfiltrar, List<Criterio> criterios) {
    return hechosAfiltrar.stream()
        .filter(hecho -> criterios.stream().allMatch(c -> c.cumpleCriterio(hecho)))
        .collect(Collectors.toList());
  }
}