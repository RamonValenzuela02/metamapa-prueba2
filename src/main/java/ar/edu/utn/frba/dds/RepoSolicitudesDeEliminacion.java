package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

public class RepoSolicitudesDeEliminacion {
  private List<SolicitudDeEliminacion> solicitudesDeEliminacion = new ArrayList<>();
  private final DetectorDeSpam detector;

  public RepoSolicitudesDeEliminacion(DetectorDeSpam detector) {
    this.detector = detector;
  }

  public void registrarSolicituDeEliminacion(SolicitudDeEliminacion solicitud) {
    if (detector.esSpam(solicitud.getMotivo())){
      solicitud.rechazar();
    }
    solicitudesDeEliminacion.add(solicitud);
  }

  public void solicitudesAceptadas() {
    for(SolicitudDeEliminacion solicitud : solicitudesDeEliminacion) {
      if(solicitud.getEstado() == Estado.ACEPTADA) {}
    }
  }

  public List<SolicitudDeEliminacion> getSolicitudes() {
    return solicitudesDeEliminacion;
  }
}