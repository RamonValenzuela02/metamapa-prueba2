package ar.edu.utn.frba.dds.repo;

import ar.edu.utn.frba.dds.domain.solicitud.SolicitudDinamica;
import java.util.ArrayList;
import java.util.List;

public class RepoSolicitudesConSugerencia {
  private List<SolicitudDinamica> solicitudesConSugerencia;
  private static final RepoSolicitudesConSugerencia INSTANCE = new RepoSolicitudesConSugerencia();

  private RepoSolicitudesConSugerencia() {
    solicitudesConSugerencia = new ArrayList<>();
  }

  public static RepoSolicitudesConSugerencia getInstance() {
    return INSTANCE;
  }

  public void agregarSolicitudConSugerencia(SolicitudDinamica solicitud) {
    solicitudesConSugerencia.add(solicitud);
  }
}
