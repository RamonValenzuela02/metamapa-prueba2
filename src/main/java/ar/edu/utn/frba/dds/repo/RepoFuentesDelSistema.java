package ar.edu.utn.frba.dds.repo;

import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


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
      .createQuery("from Fuente", Fuente.class)
      .getResultList();
  }
}
