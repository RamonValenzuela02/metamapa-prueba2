package ar.edu.utn.frba.dds.domain.coleccion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ar.edu.utn.frba.dds.domain.consenso.AlgoritmoConsenso;
import ar.edu.utn.frba.dds.domain.criterio.Criterio;
import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una colección de hechos basada en un criterio y una fuente.
 */
public class Coleccion {
  @Getter
  private final String handle;
  @Getter
  private final String titulo;
  @Getter
  private final String descripcion;
  @Getter
  private final Fuente fuente;
  @Getter
  private List<Criterio> criterios = new ArrayList<>();
  //@Setter
  //@Getter
  //private ModoNavegacion modoNavegacion;
  @Getter
  @Setter
  private AlgoritmoConsenso algoritmoConsenso;
  @Getter
  @Setter
  private List<Hecho> hechosConsensuados = new ArrayList<>();


  public Coleccion(String handle, String titulo, String descripcion, Fuente fuente, List<Criterio> criterios,
                   ModoNavegacion modoNavegacion, AlgoritmoConsenso algoritmoConsenso) {
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

  private void actualizarHechosConsensuados() {
    fuente.obtenerHechos().forEach(this::actualizarConsensuado);
  }

}