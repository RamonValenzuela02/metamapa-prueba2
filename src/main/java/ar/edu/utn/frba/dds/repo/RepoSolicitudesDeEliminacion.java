package ar.edu.utn.frba.dds.repo;

import ar.edu.utn.frba.dds.domain.solicitud.DetectorDeSpam;
import ar.edu.utn.frba.dds.domain.solicitud.Estado;
import ar.edu.utn.frba.dds.domain.solicitud.SolicitudDeEliminacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class RepoSolicitudesDeEliminacion {
  @Id
  @GeneratedValue
  private Long id;
  @OneToMany
  private List<SolicitudDeEliminacion> solicitudesDeEliminacion = new ArrayList<>();
  @Embedded
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
//tendria q devolver las solicitudes
  public void solicitudesAceptadas() {
    for(SolicitudDeEliminacion solicitud : solicitudesDeEliminacion) {
      if(solicitud.getEstado() == Estado.ACEPTADA) {

      }
    }
  }

  public List<SolicitudDeEliminacion> getSolicitudes() {
    return solicitudesDeEliminacion;
  }
}