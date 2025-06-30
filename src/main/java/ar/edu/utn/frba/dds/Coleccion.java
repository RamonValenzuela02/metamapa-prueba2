package ar.edu.utn.frba.dds;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
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
  private ModoNavegacion modoNavegacion;
  @Getter @Setter
  private AlgoritmoConsenso algoritmoConsenso;


  public Coleccion(String handle, String titulo, String descripcion, Fuente fuente, List<Criterio> criterios) {
    validarColeccion(handle, titulo, descripcion, fuente, criterios);
    this.handle = handle; //es un alias que se le da a una coleccion que sirve para identificarla cuando la exponemos por API REST
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    this.criterios = criterios;
  }

  public List<Hecho> obtenerHechos() {
    List<Hecho>hechos = fuente.obtenerHechos();

    return hechos.stream()
            .filter(hecho -> criterios.stream().allMatch(c -> c.cumpleCriterio(hecho)))
            .collect(Collectors.toList());
  }

  private void validarColeccion(String handle, String titulo, String descripcion, Fuente fuente, List<Criterio> criterios) {
    requireNonNull(handle);
    requireNonNull(titulo);
    requireNonNull(descripcion);
    requireNonNull(fuente);
    requireNonNull(criterios);

    if (!handle.matches("^[a-zA-Z0-9_-]+$")) {
      throw new IllegalArgumentException("El handle debe ser alfanumérico, sin espacios.");
    } //Aca nos fijamos si cumple con las condiciones que debe tener el handler
  }

  //TODO - Implementar FuenteAgregadora y logica para traer fuentes
  private boolean estaConsensuado(Hecho hecho) {
    if(algoritmoConsenso == null) return true;
    List<Fuente> fuentesDelNodo;

    if(fuente instanceof FuenteAgregadora){
      FuenteAgregadora agregadora = ......;
      fuentesDelNodo = agregadora.getFuentes();
    }else{
      fuentesDelNodo.add(fuente);
    }
    return algoritmoConsenso.estaConsensuado(hecho,fuentesDelNodo);
  }
}