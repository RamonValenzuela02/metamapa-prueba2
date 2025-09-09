package ar.edu.utn.frba.dds.domain.consenso;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import java.util.List;

//podria ser un enum ya que son stateless
public interface AlgoritmoConsenso {
    boolean estaConsensuado(Hecho hecho);
}
