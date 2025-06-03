package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class FuenteDinamica implements Fuente{
  private List<Hecho> hechos;
  @Getter
  private List<SolicitudDinamica> pendientes;

  public FuenteDinamica() {
    hechos = new ArrayList<>();
    pendientes = new ArrayList<>();
  }

  @Override
  public List<Hecho> obtenerHechos() {
    return hechos;
  }

  public void agregarHecho(Hecho hecho) {
    //aca usaria una continuacion (patron de comu asincrionica),
    //en donde una vez que se acepte  ese hecho se agrege a la lista de hechos.
    SolicitudDinamica solicitud = new SolicitudDinamica(hecho, () -> hechos.add(hecho));

    solicitud.setCallbackCuandoEsTratada(()-> pendientes.remove(solicitud));
    pendientes.add(solicitud);
  }
}
