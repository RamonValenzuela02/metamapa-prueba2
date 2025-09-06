package ar.edu.utn.frba.dds.domain.consenso;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import java.util.List;

public class ConsensoMayoriaSimple implements AlgoritmoConsenso {

    @Override
    public boolean estaConsensuado(Hecho hecho, List<Fuente> fuentesDelNodo){
        long cantidadMenciones = fuentesDelNodo.stream()
                .filter(f -> f.obtenerHechos().contains(hecho)).count();

        return  cantidadMenciones >= (fuentesDelNodo.size() / 2);
    }
}
