package ar.edu.utn.frba.dds.domain.fuente;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;
import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class FuenteAgregadora extends Fuente {
  @Transient
  private List<Hecho> hechos = new ArrayList<>();
  @Transient //aca hay un many to many
  private List<Fuente> fuentesAgregadas;

  public FuenteAgregadora() {
    this.fuentesAgregadas = new ArrayList<>();
    //RepoFuentesDelSistema.getInstance().agregarFuente(this);
  }

  public void agregarFuente(Fuente fuente) {
    if (!this.fuentesAgregadas.contains(fuente)) {
      this.fuentesAgregadas.add(fuente);
    }
  }

  public void removerFuente(Fuente fuente) {
    this.fuentesAgregadas.remove(fuente);
  }

  /*
  @Override
  public List<Hecho> obtenerHechos() {
    return fuentesAgregadoras.flatmap(fuente -> fuente.obtenerHechos());
  }
  */
  //aca no tendria que retornar los hechos de cada fuente??? para mi es como hice arriba
  @Override
  public List<Hecho> obtenerHechos() {
    return hechos;
  }

  public void actualizarCache() {
    this.hechos = this.fuentesAgregadas.stream()
        .flatMap(fuente -> fuente.obtenerHechos().stream())
        .collect(Collectors.toList());
  }

}
