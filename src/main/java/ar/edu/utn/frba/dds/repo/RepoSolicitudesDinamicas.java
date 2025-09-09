package ar.edu.utn.frba.dds.repo;

import ar.edu.utn.frba.dds.domain.solicitud.SolicitudDinamica;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import lombok.Getter;

public class RepoSolicitudesDinamicas implements WithSimplePersistenceUnit {
  private static final RepoSolicitudesDinamicas INSTANCE = new RepoSolicitudesDinamicas();

  private RepoSolicitudesDinamicas() {
  }

  public static RepoSolicitudesDinamicas getInstance() {
    return INSTANCE;
  }

  public void agregarSolicitud(SolicitudDinamica solicitud) {
    entityManager().persist(solicitud);
  }

  public List<SolicitudDinamica> getSolicitudes() {
    return entityManager()
      .createQuery("SELECT s FROM SolicitudDinamica ")
      .getResultList();
  }

}
