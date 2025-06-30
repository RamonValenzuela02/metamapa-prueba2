package ar.edu.utn.frba.dds;

import java.util.List;

public class ConsensoAbsoluta  implements AlgoritmoConsenso {

    @Override
    public boolean estaConsensuado(Hecho hecho, List<Fuente> fuentesDelNodo) {
        return fuentesDelNodo.stream().allMatch(f -> f.obtenerHechos().contains(hecho));
    }
}
