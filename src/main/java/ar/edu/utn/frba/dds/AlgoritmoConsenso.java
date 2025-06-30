package ar.edu.utn.frba.dds;

import java.util.List;

public interface AlgoritmoConsenso {
    boolean estaConsensuado(Hecho hecho, List<Fuente> fuentesDelNodo);
}
