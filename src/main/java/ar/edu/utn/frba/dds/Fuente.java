package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Fuente {
  List<Hecho> hechos = new ArrayList<>();

  public abstract List<Hecho> obtenerHechos();

}