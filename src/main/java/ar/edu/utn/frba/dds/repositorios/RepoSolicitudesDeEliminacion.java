package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.model.solicitud.DetectorDeSpam;
import ar.edu.utn.frba.dds.model.solicitud.Estado;
import ar.edu.utn.frba.dds.model.solicitud.SolicitudDeEliminacion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;


public class RepoSolicitudesDeEliminacion implements WithSimplePersistenceUnit {
  private static RepoSolicitudesDeEliminacion instancia;

  @Getter
  private int cantidadDeSpam = 0;

  public static RepoSolicitudesDeEliminacion getInstance() {
    if (instancia == null) {
      instancia = new RepoSolicitudesDeEliminacion();
    }
    return instancia;
  }

  private RepoSolicitudesDeEliminacion() {}

  public void registrarSolicituDeEliminacion(SolicitudDeEliminacion solicitud) {
    entityManager().persist(solicitud);
  }

  public List<SolicitudDeEliminacion> solicitudesAceptadas() {
      return entityManager()
        .createQuery("SELECT s FROM SolicitudDeEliminacion s WHERE s.estado = :estado", SolicitudDeEliminacion.class)
        .setParameter("estado", Estado.ACEPTADA)
        .getResultList();
  }

  public List<SolicitudDeEliminacion> getSolicitudesPendientes() {
    return entityManager()
            .createQuery("SELECT s FROM SolicitudDeEliminacion s WHERE s.estado = :estado", SolicitudDeEliminacion.class)
            .setParameter("estado", Estado.PENDIENTE)
            .getResultList();
  }

  public SolicitudDeEliminacion getSolicitudPorId(Long id) {
    return entityManager()
      .createQuery("SELECT s FROM SolicitudDeEliminacion s WHERE s.id = :id", SolicitudDeEliminacion.class)
      .setParameter("id", id)
      .getSingleResult();
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