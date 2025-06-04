package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

public class GestorDeSolicitudes {
  private List<SolicitudDeEliminacion> solicitudesDeEliminacion = new ArrayList<>();
  private final DetectorDeSpam detector;

  public GestorDeSolicitudes(DetectorDeSpam detector) {
    this.detector = detector;
  }

  public void registrarSolicituDeEliminacion(SolicitudDeEliminacion solicitud) {
    if (detector.esSpam(solicitud.getMotivo())){
      solicitud.rechazar();
    }
    solicitudesDeEliminacion.add(solicitud);
  }

  public List<SolicitudDeEliminacion> getSolicitudes() {
    return solicitudesDeEliminacion;
  }
}
