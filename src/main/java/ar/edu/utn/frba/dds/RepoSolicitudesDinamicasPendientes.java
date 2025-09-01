package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

public class RepoSolicitudesDinamicasPendientes {
  private List<Hecho> hechosPendientes;
  private static final RepoSolicitudesDinamicasPendientes INSTANCE = new RepoSolicitudesDinamicasPendientes();

  private RepoSolicitudesDinamicasPendientes() {
    hechosPendientes = new ArrayList<>();
  }

  public static RepoSolicitudesDinamicasPendientes getInstance() {
    return INSTANCE;
  }

  public void agregarSolicitud(Hecho solicitud) {
    hechosPendientes.add(solicitud);
  }

  public void aceptarSolicitud(Hecho solicitud) {
    hechosPendientes.remove(solicitud);
    RepoHechosDinamicos.getInstance().agregarHecho(solicitud);
  }

  public void aceptarConSugerenciaSolicitud(Hecho solicitud, String sugerencia) {
    SolicitudDinamica solicitudDinamica = new SolicitudDinamica(solicitud);
    solicitudDinamica.setSugerencia(sugerencia);
    hechosPendientes.remove(solicitud);
    RepoSolicitudesConSugerencia.getInstance().agregarSolicitudConSugerencia(solicitudDinamica);
    RepoHechosDinamicos.getInstance().agregarHecho(solicitud);
  }

  public void rechazarSolicitud(Hecho solicitud) {
    //aca pordria tener un repo de solicitudes rechazadas
    hechosPendientes.remove(solicitud);
  }
}
