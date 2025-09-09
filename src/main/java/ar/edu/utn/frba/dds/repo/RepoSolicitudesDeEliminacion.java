package ar.edu.utn.frba.dds.repo;

import ar.edu.utn.frba.dds.domain.solicitud.DetectorDeSpam;
import ar.edu.utn.frba.dds.domain.solicitud.Estado;
import ar.edu.utn.frba.dds.domain.solicitud.SolicitudDeEliminacion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

public class RepoSolicitudesDeEliminacion implements WithSimplePersistenceUnit {
  private final DetectorDeSpam detector;

  public RepoSolicitudesDeEliminacion(DetectorDeSpam detector) {
    this.detector = detector;
  }

  public void registrarSolicituDeEliminacion(SolicitudDeEliminacion solicitud) {
    if (detector.esSpam(solicitud.getMotivo())){
      solicitud.rechazar();
    }
    entityManager().persist(solicitud);
  }

  public List<SolicitudDeEliminacion> solicitudesAceptadas() {
      return entityManager()
        .createQuery("SELECT s FROM SolicitudDeEliminacion s WHERE s.estado = :estado", SolicitudDeEliminacion.class)
        .setParameter("estado", Estado.ACEPTADA)
        .getResultList();
  }

  public List<SolicitudDeEliminacion> getSolicitudes() {
    return entityManager()
      .createQuery("SELECT s FROM SolicitudDeEliminacion s", SolicitudDeEliminacion.class)
      .getResultList();
  }
}