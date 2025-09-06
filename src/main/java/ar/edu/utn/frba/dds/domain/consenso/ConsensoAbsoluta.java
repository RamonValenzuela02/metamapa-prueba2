package ar.edu.utn.frba.dds.domain.consenso;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import java.util.List;

public class ConsensoAbsoluta  implements AlgoritmoConsenso {

    @Override
    public boolean estaConsensuado(Hecho hecho, List<Fuente> fuentesDelNodo) {
        return fuentesDelNodo.stream().allMatch(f -> f.obtenerHechos().contains(hecho));
    }
}
