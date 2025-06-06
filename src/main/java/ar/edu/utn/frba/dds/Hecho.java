package ar.edu.utn.frba.dds;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

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

  Boolean anonimo;
  @Getter
  Optional<String> nombreContribuyente;
  @Getter
  Optional<String> apellidoContribuyente;
  @Getter
  Optional<Integer> edadContribuyente;

  public Hecho(String titulo, String descripcion, Categoria categoria,
                String latitud, String longitud, LocalDate fechaHecho, LocalDate fechaCarga,
                  Boolean anonimo, Optional<String> nombreContribuyente, Optional<String> apellidoContribuyente, Optional<Integer> edadContribuyente ) {
    validar(titulo, descripcion, categoria, latitud, longitud, fechaHecho, fechaCarga, anonimo, nombreContribuyente);
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.latitud = latitud;
    this.longitud = longitud;
    this.fechaHecho = fechaHecho;
    this.fechaCarga = fechaCarga;
    this.anonimo = anonimo;
    this.nombreContribuyente = nombreContribuyente;
    this.apellidoContribuyente = apellidoContribuyente;
    this.edadContribuyente = edadContribuyente;
  }

  private void validar(String titulo, String descripcion, Categoria categoria,
                       String latitud, String longitud, LocalDate fechaHecho, LocalDate fechaCarga,
                       Boolean anonimo, Optional<String> nombreContribuyente) {
    requireNonNull(titulo);
    requireNonNull(descripcion);
    requireNonNull(categoria);
    requireNonNull(latitud);
    requireNonNull(longitud);
    requireNonNull(fechaHecho);
    requireNonNull(fechaCarga);
    if (!anonimo && (!nombreContribuyente.isPresent() || nombreContribuyente.get().isBlank())) {
      throw new IllegalArgumentException("El nombre es obligatorio si la carga no se realiza de manera anónima");
    }
  }

}
