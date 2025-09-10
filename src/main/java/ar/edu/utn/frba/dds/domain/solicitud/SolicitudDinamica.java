package ar.edu.utn.frba.dds.domain.solicitud;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.criterio.Categoria;
import ar.edu.utn.frba.dds.domain.ubicacion.Ubicacion;
import ar.edu.utn.frba.dds.repo.RepoHechosDinamicos;
import ar.edu.utn.frba.dds.repo.RepoSolicitudesDinamicas;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
public class SolicitudDinamica {
  @Id
  @GeneratedValue
  private Long id;
  @Column
  private final String titulo;
  @Column
  private final String descripcion;
  @Enumerated(EnumType.STRING)
  private final Categoria categoria;
  @Column
  private final String latitud;
  @Column
  private final String longitud;
  @Column
  private final LocalDate fechaHecho;
  @Column
  private final LocalDate fechaCarga;
  @Enumerated(EnumType.STRING)
  private EstadoSolicitudDinamica estado;
  @Column
  @Setter
  private String sugerencia;

  public SolicitudDinamica(String titulo, String descripcion, Categoria categoria, String latitud, String longitud, LocalDate fechaHecho, LocalDate fechaCarga) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaHecho = fechaHecho;
    this.fechaCarga = fechaCarga;
    this.estado = EstadoSolicitudDinamica.PENDIENTE;
  }

  public void aceptar() {
    this.estado = EstadoSolicitudDinamica.ACEPTADA;
    Hecho hecho = new Hecho(titulo,descripcion,categoria,latitud,longitud,fechaHecho,fechaCarga);
    RepoHechosDinamicos.getInstance().agregarHecho(hecho);
  }

  public void aceptarConSugerencia(String sugerencia) {
    this.sugerencia = sugerencia;
    this.estado = EstadoSolicitudDinamica.ACEPTADA_CON_SUGERENCIA;
    Hecho hecho = new Hecho(titulo,descripcion,categoria,latitud,longitud,fechaHecho,fechaCarga);
    RepoHechosDinamicos.getInstance().agregarHecho(hecho);
  }

  public void rechazar() {
    this.estado = EstadoSolicitudDinamica.RECHAZADA;
  }

}