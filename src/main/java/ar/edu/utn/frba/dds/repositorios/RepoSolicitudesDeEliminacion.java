package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.model.solicitud.DetectorDeSpam;
import ar.edu.utn.frba.dds.model.solicitud.Estado;
import ar.edu.utn.frba.dds.model.solicitud.SolicitudDeEliminacion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import lombok.Getter;


public class RepoSolicitudesDeEliminacion implements WithSimplePersistenceUnit {
  private static RepoSolicitudesDeEliminacion instancia;

  private final DetectorDeSpam detector;
  @Getter
  private int cantidadDeSpam = 0;

  public static RepoSolicitudesDeEliminacion getInstance(DetectorDeSpam detector) {
    if (instancia == null) {
      instancia = new RepoSolicitudesDeEliminacion(detector);
    }
    return instancia;
  }

  public RepoSolicitudesDeEliminacion(DetectorDeSpam detector) {
    this.detector = detector;
  }

  public void registrarSolicituDeEliminacion(SolicitudDeEliminacion solicitud) {
    if (detector.esSpam(solicitud.getMotivo())){
      solicitud.rechazar();
      this.cantidadDeSpam++;
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

  public int cantidadDeSolicitudesRechazadasPorSpam() {
    return cantidadDeSpam;
  }
}