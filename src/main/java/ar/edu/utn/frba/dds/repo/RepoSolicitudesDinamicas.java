package ar.edu.utn.frba.dds.repo;

import ar.edu.utn.frba.dds.domain.solicitud.SolicitudDinamica;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import lombok.Getter;

@Entity
public class RepoSolicitudesDinamicas {
  @Id
  @GeneratedValue
  private Long id;
  @OneToMany
  @Getter
  private List<SolicitudDinamica> solicitudes;
  @Transient
  private static final RepoSolicitudesDinamicas INSTANCE = new RepoSolicitudesDinamicas();

  private RepoSolicitudesDinamicas() {
    solicitudes = new ArrayList<>();
  }

  public static RepoSolicitudesDinamicas getInstance() {
    return INSTANCE;
  }

  public void agregarSolicitud(SolicitudDinamica solicitud) {
    solicitudes.add(solicitud);
  }

}
