package ar.edu.utn.frba.dds;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Hecho {
  private final String titulo;
  private final String descripcion;
  private final Categoria categoria;
  private final String latitud;
  private final String longitud;
  private final LocalDate fechaHecho;
  private final LocalDate fechaCarga;


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