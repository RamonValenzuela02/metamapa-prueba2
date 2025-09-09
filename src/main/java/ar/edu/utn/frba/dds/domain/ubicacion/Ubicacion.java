package ar.edu.utn.frba.dds.domain.ubicacion;

import javax.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public class Ubicacion {
  @Getter
  private final String latitud;
  @Getter
  private final String longitud;

  public Ubicacion(String latitud, String longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public String getProvincia(){
    ClaseMoldeProvincia respuesta = ServicioProvincia.getInstancia().provincia(latitud, longitud);
    return respuesta.getAddress().getState();
  }

}
