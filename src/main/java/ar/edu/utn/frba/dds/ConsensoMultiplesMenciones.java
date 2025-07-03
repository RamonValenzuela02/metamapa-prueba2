package ar.edu.utn.frba.dds;

import java.util.List;

public class ConsensoMultiplesMenciones implements  AlgoritmoConsenso {

    @Override
    public boolean estaConsensuado(Hecho hecho, List<Fuente> fuentesDelNodo){

        long cantidadMenciones = fuentesDelNodo.stream()
                .filter(f -> f.obtenerHechos().contains(hecho)).count();

        boolean existenVersionesDistintasDeUnHecho = fuentesDelNodo.stream()
                .flatMap(f -> f.obtenerHechos().stream())
                .filter(h -> h.getTitulo().equals(hecho.getTitulo()))
                .allMatch(h -> h.equals(hecho));

        return cantidadMenciones >= 2 && !existenVersionesDistintasDeUnHecho;
    }
}

