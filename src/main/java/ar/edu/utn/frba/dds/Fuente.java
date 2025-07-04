package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Fuente {

  List<Hecho> hechos = new ArrayList<>();

  public List<Fuente> fuentesDelNodo() {
    return List.of(this);
  }
    
  public abstract List<Hecho> obtenerHechos();
  Coleccion col = new ColeccionBuilder()
          .conFuente(new FuenteAgregadora())
          .conAlgoritmoConsenso(new ConsensoAbsoluta())
          .crear();
}