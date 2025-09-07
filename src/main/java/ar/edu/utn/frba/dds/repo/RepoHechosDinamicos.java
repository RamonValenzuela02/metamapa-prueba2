package ar.edu.utn.frba.dds.repo;

import java.util.ArrayList;
import java.util.List;
import ar.edu.utn.frba.dds.domain.Hecho;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Entity
public class RepoHechosDinamicos {
  @Id
  @GeneratedValue
  private Long id;
  @Transient
  private List<Hecho> hechos;
  @Transient
  private static final RepoHechosDinamicos INSTANCE = new RepoHechosDinamicos();

  private RepoHechosDinamicos() {
    hechos = new ArrayList<>();
  }

  public static RepoHechosDinamicos getInstance() {
    return INSTANCE;
  }

  public void agregarHecho(Hecho hecho) {
    hechos.add(hecho);
  }
}
