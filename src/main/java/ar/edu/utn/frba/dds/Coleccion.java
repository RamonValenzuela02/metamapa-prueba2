package ar.edu.utn.frba.dds;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Nodes.collect;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
  private final Fuente fuente;
  private List<Criterio> criterios = new ArrayList<>();
  @Setter
  private ModoNavegacion modoNavegacion;
  @Getter
  @Setter
  private AlgoritmoConsenso algoritmoConsenso;


  public Coleccion(String handle, String titulo, String descripcion, Fuente fuente, List<Criterio> criterios,
                   ModoNavegacion modoNavegacion, AlgoritmoConsenso algoritmoConsenso) {
    this.handle = handle; //es un alias que se le da a una coleccion que sirve para identificarla cuando la exponemos por API REST
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    this.criterios = criterios;
  }

  public List<Hecho> getHechos() {
    List<Hecho> hechos = fuente.obtenerHechos();
    if (modoNavegacion == ModoNavegacion.IRRESTRICTA) {
      return hechos;
    } else {
      return hechos.stream()
              .filter(this::estaConsensuado)
              .collect(Collectors.toList());
    }
  }

  private boolean estaConsensuado(Hecho hecho) {
    if (algoritmoConsenso == null) return true;
    return algoritmoConsenso.estaConsensuado(hecho, fuente.fuentesDelNodo());
  }
}