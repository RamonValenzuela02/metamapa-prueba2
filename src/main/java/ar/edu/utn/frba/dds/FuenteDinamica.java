package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

public class FuenteDinamica extends Fuente{
  @Getter
  private List<SolicitudDinamica> pendientes;

  public FuenteDinamica() {
    hechos = new ArrayList<>();
    pendientes = new ArrayList<>();
  }

  public void agregarHecho(Hecho hecho) {
    //en donde una vez que se acepte ese hecho se agregue a la lista de hechos.
    SolicitudDinamica solicitud = new SolicitudDinamica(hecho, () -> hechos.add(hecho));

    solicitud.setCallbackCuandoEsTratada(()-> pendientes.remove(solicitud));
    pendientes.add(solicitud);
  }

  public List<Hecho> obtenerHechos() {
    return hechos;
  }
}