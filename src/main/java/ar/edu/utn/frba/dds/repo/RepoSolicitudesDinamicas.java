package ar.edu.utn.frba.dds.repo;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.solicitud.SolicitudDinamica;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class RepoSolicitudesDinamicas {
  @Getter
  private List<SolicitudDinamica> solicitudes;
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
