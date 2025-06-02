package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

public class FuenteDinamica implements Fuente{
  private List<Hecho> hechos;
  private List<SolicitudDinamica> pendientes;

  public FuenteDinamica() {
    hechos = new ArrayList<Hecho>();
    pendientes = new ArrayList<SolicitudDinamica>();
  }

  @Override
  public List<Hecho> obtenerHechos() {
    return hechos;
  }

  public void agregarHecho(Hecho hecho) {
    //aca usaria una continuacion (patron de comu asincrionica),
    //en donde una vez que se acepte  ese hecho se agrege a la lista de hechos.
    SolicitudDinamica solicitud = new SolicitudDinamica(hecho,
        () -> hechos.add(hecho),
        null
    );
    solicitud.setCallbackCuandoEsTratada(() -> pendientes.remove(solicitud)); //ver desp esto me hace ruido
    pendientes.add(solicitud);
  }
}
