package ar.edu.utn.frba.dds.domain.fuente;

import ar.edu.utn.frba.dds.domain.Hecho;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Fuente {
  @Id
  @GeneratedValue
  @Getter
  @Setter
  private Long id;

  public abstract List<Hecho> obtenerHechos();

}