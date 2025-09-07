package ar.edu.utn.frba.dds.domain.consenso;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;
import java.util.List;

public class ConsensoMultiplesMenciones implements AlgoritmoConsenso {

    @Override
    public boolean estaConsensuado(Hecho hecho){
        List<Fuente> fuentes = RepoFuentesDelSistema.getInstance().obtenerFuentes();
        long cantidadMenciones = fuentes.stream()
                .filter(f -> f.obtenerHechos().contains(hecho)).count();

        boolean existenVersionesDistintasDeUnHecho = fuentes.stream()
                .flatMap(f -> f.obtenerHechos().stream())
                .filter(h -> h.getTitulo().equals(hecho.getTitulo()))
                .allMatch(h -> h.equals(hecho));

        return cantidadMenciones >= 2 && !existenVersionesDistintasDeUnHecho;
    }
}

