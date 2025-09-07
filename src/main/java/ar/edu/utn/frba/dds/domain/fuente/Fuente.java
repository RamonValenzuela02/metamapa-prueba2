package ar.edu.utn.frba.dds.domain.fuente;

import ar.edu.utn.frba.dds.domain.Hecho;
import java.util.ArrayList;
import java.util.List;


public abstract class Fuente {
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