package ar.edu.utn.frba.dds.domain.criterio;


import ar.edu.utn.frba.dds.domain.Hecho;
import javax.persistence.DiscriminatorValue;
import lombok.Getter;

@Getter
@DiscriminatorValue("Ubicacion")
public class CriterioPorUbicacion extends Criterio {
  private final String latitud;
  private final String longitud;

  public CriterioPorUbicacion(String latitud, String longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }
  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return hecho.getUbicacion().getLatitud().equals(latitud) &&
      hecho.getUbicacion().getLongitud().equals(longitud);
  }
}