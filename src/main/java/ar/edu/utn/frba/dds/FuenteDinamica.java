package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class FuenteDinamica implements Fuente{
  @Getter
  private List<Hecho> hechos;
  @Getter
  private List<SolicitudDinamica> pendientes;

  public FuenteDinamica() {
    hechos = new ArrayList<>();
    pendientes = new ArrayList<>();
  }

  public void agregarHecho(Hecho hecho) {
    //aca usaria una continuacion (patron de comu asincrionica),
    //en donde una vez que se acepte  ese hecho se agrege a la lista de hechos.
    SolicitudDinamica solicitud = new SolicitudDinamica(hecho, () -> hechos.add(hecho));

    solicitud.setCallbackCuandoEsTratada(()-> pendientes.remove(solicitud));
    pendientes.add(solicitud);
  }

  @Override
  public List<Hecho> obtenerHechosConCriterio(Criterio criterio) {
    return hechos.stream().filter(criterio::cumpleCriterio).collect(Collectors.toList());
  }

}
