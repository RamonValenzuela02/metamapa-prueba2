package ar.edu.utn.frba.dds.domain;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import ar.edu.utn.frba.dds.domain.criterio.Categoria;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Hecho {
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
  @Column
  private boolean eliminado = false;

  public Hecho(String titulo, String descripcion, Categoria categoria,
               String latitud, String longitud, LocalDate fechaHecho, LocalDate fechaCarga) {
    validarNotNull(titulo, descripcion, categoria, latitud, longitud, fechaHecho, fechaCarga);
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaHecho = fechaHecho;
    this.fechaCarga = fechaCarga;
  }

  private void validarNotNull(String titulo, String descripcion, Categoria categoria,
                              String latitud, String longitud, LocalDate fechaHecho, LocalDate fechaCarga) {
    requireNonNull(titulo);
    requireNonNull(descripcion);
    requireNonNull(categoria);
    requireNonNull(latitud);
    requireNonNull(longitud);
    requireNonNull(fechaHecho);
    requireNonNull(fechaCarga);
  }

  public void marcarComoEliminado() {
    this.eliminado = true;
  }

  public boolean estaEliminado() {
    return eliminado;
  }
}