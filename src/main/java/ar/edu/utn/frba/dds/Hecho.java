package ar.edu.utn.frba.dds;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;


public class Hecho {
  @Getter
  private final String titulo;
  @Getter
  private final String descripcion;
  @Getter
  private final Categoria categoria;
  @Getter
  private final String latitud;
  @Getter
  private final String longitud;
  @Getter
  private final LocalDate fechaHecho;
  @Getter
  private LocalDate fechaCarga;


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
                              String latitud, String longitud,LocalDate fechaHecho, LocalDate fechaCarga) {
    requireNonNull(titulo);
    requireNonNull(descripcion);
    requireNonNull(categoria);
    requireNonNull(latitud);
    requireNonNull(longitud);
    requireNonNull(fechaHecho);
    requireNonNull(fechaCarga);
  }


}
