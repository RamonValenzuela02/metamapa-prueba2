package ar.edu.utn.frba.dds.domain.coleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ar.edu.utn.frba.dds.domain.consenso.AlgoritmoConsenso;
import ar.edu.utn.frba.dds.domain.criterio.Criterio;
import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una colección de hechos basada en un criterio y una fuente.
 */
@Entity
public class Coleccion {
  @Id
  @GeneratedValue
  private Long id;
  @Column
  @Getter
  private String handle;
  @Column
  @Getter
  private String titulo;
  @Column
  @Getter
  private String descripcion;
  @ManyToOne
  //JoinColumn me parece que iria
  @Getter
  private Fuente fuente;
  @ManyToMany
  @Getter
  private List<Criterio> criterios = new ArrayList<>();
  @Enumerated(EnumType.STRING)
  @Getter
  @Setter
  private AlgoritmoConsenso algoritmoConsenso;
  @Transient
  @Getter
  @Setter
  private List<Hecho> hechosConsensuados = new ArrayList<>();

  protected Coleccion(){};

  public Coleccion(String handle, String titulo, String descripcion, Fuente fuente, List<Criterio> criterios, AlgoritmoConsenso algoritmoConsenso) {
    this.handle = handle; //es un alias que se le da a una coleccion que sirve para identificarla cuando la exponemos por API REST
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    this.criterios = criterios;
    this.algoritmoConsenso = algoritmoConsenso;
  }
  /*
  public List<Hecho> getHechos() {
    List<Hecho> hechos = fuente.obtenerHechos();
    if (modoNavegacion == ModoNavegacion.IRRESTRICTA) {
      return hechos.stream()
          .filter(hecho -> !hecho.estaEliminado())
          .filter(this::cumpleTodosLosCriterios)
          .collect(Collectors.toList());
    } else {
      return hechos.stream()
          .filter(hecho -> !hecho.estaEliminado())
          .filter(this::estaConsensuado)
          .filter(this::cumpleTodosLosCriterios)
          .collect(Collectors.toList());
    }
  }
  */
  public List<Hecho> getHechos() {
    return fuente.obtenerHechos().stream()
      .filter(hecho -> !hecho.estaEliminado())
      .filter(this::cumpleTodosLosCriterios)
      .collect(Collectors.toList());
  }

  private boolean cumpleTodosLosCriterios(Hecho hecho) {
    return criterios.stream().allMatch(criterio -> criterio.cumpleCriterio(hecho));
  }

  private void actualizarConsensuado(Hecho hecho) {
    if(algoritmoConsenso.estaConsensuado(hecho)){
      hechosConsensuados.add(hecho);
    }
  }

  private List<Hecho> getHechosConNavegacion(ModoNavegacion modoNavegacion) {
    if(modoNavegacion==ModoNavegacion.IRRESTRICTA){
      return getHechos();
    }else{
      return hechosConsensuados;
    }
  }

  public void actualizarHechosConsensuados() {
    fuente.obtenerHechos().forEach(this::actualizarConsensuado);
  }

  //De una colección, ¿en qué provincia se agrupan la mayor cantidad de hechos reportados?
  public String provinciaConMayorCantidadDeHechos() {
    return "falta terminar";
  }
}