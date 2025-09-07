package ar.edu.utn.frba.dds.repo;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import java.util.ArrayList;
import java.util.List;

public class RepoFuentesDelSistema {
  private List<Fuente> fuentes;
  private static final RepoFuentesDelSistema INSTANCE = new RepoFuentesDelSistema();

  private RepoFuentesDelSistema() {
    fuentes = new ArrayList<>();
  }

  public static RepoFuentesDelSistema getInstance() {
    return INSTANCE;
  }

  public void agregarFuente(Fuente fuente) {
    fuentes.add(fuente);
  }
  public void agregarFuentes(List<Fuente> fuentesNuevas ) {
    fuentes.addAll(fuentesNuevas);
  }
  public List<Fuente> obtenerFuentes(){
    return fuentes;
  }
}
