package ar.edu.utn.frba.dds.model.ubicacion;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import lombok.Getter;

@Embeddable
public class Ubicacion {
  @Getter
  private String latitud;
  @Getter
  private String longitud;

  public Ubicacion(String latitud, String longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  protected Ubicacion(){};

  public String getProvincia(){
    ClaseMoldeProvincia respuesta = ServicioProvincia.getInstancia().provincia(latitud, longitud);
    return respuesta.getAddress().getState();
  }

}
