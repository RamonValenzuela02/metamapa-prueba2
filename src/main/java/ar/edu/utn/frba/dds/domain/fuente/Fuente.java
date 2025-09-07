package ar.edu.utn.frba.dds.domain.fuente;

import ar.edu.utn.frba.dds.domain.Hecho;
import java.util.ArrayList;
import java.util.List;


public abstract class Fuente {

  public abstract List<Hecho> obtenerHechos();

}