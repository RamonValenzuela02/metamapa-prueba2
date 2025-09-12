package ar.edu.utn.frba.dds.domain.criterio;


import ar.edu.utn.frba.dds.domain.Hecho;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import lombok.Getter;

@Entity
@DiscriminatorValue("Categoria")
public class CriterioPorCategoria extends Criterio {
  @Enumerated(EnumType.STRING)
  @Getter
  private Categoria categoria;

  public CriterioPorCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public CriterioPorCategoria() {};

  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return hecho.getCategoria() == categoria;
  }
}