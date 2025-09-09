package ar.edu.utn.frba.dds.domain.fuente;

import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;
import java.util.ArrayList;
import java.util.List;
import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.solicitud.SolicitudDinamica;
import ar.edu.utn.frba.dds.repo.RepoHechosDinamicos;
import ar.edu.utn.frba.dds.repo.RepoSolicitudesDinamicas;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import lombok.Getter;

@Entity
public class FuenteDinamica extends Fuente{
  public FuenteDinamica() {
    RepoFuentesDelSistema.getInstance().agregarFuente(this);
  }

  public void agregarHecho(Hecho hecho) {
    SolicitudDinamica solicitud = new SolicitudDinamica(hecho);
    RepoSolicitudesDinamicas.getInstance().agregarSolicitud(solicitud);
  }

  public List<Hecho> obtenerHechos() {
    return RepoHechosDinamicos.getInstance().obtenerHechos();
  }
}