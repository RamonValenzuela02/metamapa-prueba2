package ar.edu.utn.frba.dds.domain.fuente;

import ar.edu.utn.frba.dds.domain.criterio.Categoria;
import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;
import java.time.LocalDate;
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
    //RepoFuentesDelSistema.getInstance().agregarFuente(this);
  }

  public void agregarHecho(String titulo,
                           String descripcion,
                           Categoria categoria,
                           String latitud,
                           String longitud,
                           LocalDate fechaHecho,
                           LocalDate fechaCarga) {
    SolicitudDinamica solicitud = new SolicitudDinamica(titulo,descripcion,categoria,latitud,longitud,fechaHecho,fechaCarga);
    RepoSolicitudesDinamicas.getInstance().agregarSolicitud(solicitud);
  }

  public List<Hecho> obtenerHechos() {
    return RepoHechosDinamicos.getInstance().obtenerHechos();
  }
}