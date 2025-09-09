package ar.edu.utn.frba.dds.domain.criterio;


import ar.edu.utn.frba.dds.domain.Hecho;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import lombok.Getter;

@Entity
@DiscriminatorValue("Categoria")
public class CriterioPorCategoria extends Criterio {
  @Transient
  @Getter
  private final Categoria categoria;


  public CriterioPorCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return hecho.getCategoria() == categoria;
  }
}