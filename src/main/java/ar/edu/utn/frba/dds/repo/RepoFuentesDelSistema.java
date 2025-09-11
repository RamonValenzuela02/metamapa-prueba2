package ar.edu.utn.frba.dds.repo;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.stream.Collectors;


public class RepoFuentesDelSistema implements WithSimplePersistenceUnit {
  private static final RepoFuentesDelSistema INSTANCE = new RepoFuentesDelSistema();


  private RepoFuentesDelSistema() {}

  public static RepoFuentesDelSistema getInstance() {
    return INSTANCE;
  }

  public void agregarFuente(Fuente fuente) {
    entityManager().persist(fuente);
  }

  public void agregarFuentes(List<Fuente> fuentesNuevas ) {
    fuentesNuevas.forEach(this::agregarFuente);
  }

  public List<Fuente> obtenerFuentes(){
    return entityManager()
      .createQuery("SELECT f FROM Fuente f", Fuente.class)
      .getResultList();
  }

  public List<Hecho> obtenerHechosSimilares(String texto) {
    return this.obtenerFuentes().stream()
      .flatMap(fuente -> fuente.obtenerHechos().stream())
      .filter(hecho -> hecho.cumpleConBusqueda(texto))
      .collect(Collectors.toList());
  }
}
