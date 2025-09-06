package ar.edu.utn.frba.dds.domain.consenso;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import java.util.List;

public interface AlgoritmoConsenso {
    boolean estaConsensuado(Hecho hecho, List<Fuente> fuentesDelNodo);
}
