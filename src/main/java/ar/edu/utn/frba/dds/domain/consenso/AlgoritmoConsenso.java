package ar.edu.utn.frba.dds.domain.consenso;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;
import java.util.List;


public enum AlgoritmoConsenso {
    CONSENSO_ABSOLUTO {
        @Override
        public boolean estaConsensuado(Hecho hecho) {
            List<Fuente> fuentes = RepoFuentesDelSistema.getInstance().obtenerFuentes();
            return fuentes.stream().allMatch(f -> f.obtenerHechos().contains(hecho));
        }
    },
    CONSENSO_MAYORIA_SIEMPLE{
        @Override
        public boolean estaConsensuado(Hecho hecho){
            List<Fuente> fuentes =RepoFuentesDelSistema.getInstance().obtenerFuentes();
            long cantidadMenciones = fuentes.stream()
              .filter(f -> f.obtenerHechos().contains(hecho)).count();

            return  cantidadMenciones >= (fuentes.size() / 2);
        }
    },
    CONSENSO_MULTIPLES_MENSIONES{
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
    };
    public abstract boolean estaConsensuado(Hecho hecho);
}
