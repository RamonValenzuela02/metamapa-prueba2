package ar.edu.utn.frba.dds.repo;

import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import ar.edu.utn.frba.dds.domain.Hecho;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;


public class RepoHechosDinamicos implements WithSimplePersistenceUnit {
  private static final RepoHechosDinamicos INSTANCE = new RepoHechosDinamicos();

  private RepoHechosDinamicos() {
  }

  public static RepoHechosDinamicos getInstance() {
    return INSTANCE;
  }

  public void agregarHecho(Hecho hecho) {
    entityManager().persist(hecho);
  }
  public List<Hecho> obtenerHechos() {
    return entityManager()
      .createQuery("from Hecho")
      .getResultList();
  }
}
