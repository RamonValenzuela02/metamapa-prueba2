package ar.edu.utn.frba.dds.domain.consenso;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import ar.edu.utn.frba.dds.repo.RepoFuenteDemo;
import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;
import java.util.List;

public class ConsensoMayoriaSimple implements AlgoritmoConsenso {

    @Override
    public boolean estaConsensuado(Hecho hecho){
        List<Fuente> fuentes =RepoFuentesDelSistema.getInstance().obtenerFuentes();
        long cantidadMenciones = fuentes.stream()
                .filter(f -> f.obtenerHechos().contains(hecho)).count();

        return  cantidadMenciones >= (fuentes.size() / 2);
    }
}
