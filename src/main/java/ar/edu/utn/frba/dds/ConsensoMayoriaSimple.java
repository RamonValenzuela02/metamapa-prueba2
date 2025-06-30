package ar.edu.utn.frba.dds;

import java.util.List;

public class ConsensoMayoriaSimple implements AlgoritmoConsenso {

    @Override
    public boolean estaConsensuado(Hecho hecho, List<Fuente> fuentes){
        long cantidadMenciones = fuentes.stream()
                .filter(f -> f.obtenerHechos().contains(hecho)).count();

        return  cantidadMenciones >= (fuentes.size() / 2);
    }
}
