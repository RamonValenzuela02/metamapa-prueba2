package ar.edu.utn.frba.dds.domain.fuente;

import ar.edu.utn.frba.dds.domain.Hecho;
import java.util.ArrayList;
import java.util.List;


public abstract class Fuente {
  //esto se iria  y pasaria al repositorio pero todavia no hice los refactors en fuenteAgregacion
  List<Hecho> hechos = new ArrayList<>();

  public List<Fuente> fuentesDelNodo() {
    return List.of(this);
  }
    
  public abstract List<Hecho> obtenerHechos();
  /*
  Coleccion col = new ColeccionBuilder()
          .conFuente(new FuenteAgregadora())
          .conAlgoritmoConsenso(new ConsensoAbsoluta())
          .crear();
   */
}