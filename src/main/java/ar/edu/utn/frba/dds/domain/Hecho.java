package ar.edu.utn.frba.dds.domain;

import static java.util.Objects.requireNonNull;

import ar.edu.utn.frba.dds.domain.ubicacion.Ubicacion;
import java.time.LocalDate;
import ar.edu.utn.frba.dds.domain.criterio.Categoria;
import java.time.LocalDateTime;
import java.util.Arrays;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.apache.commons.text.similarity.LevenshteinDistance;

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
  @Embedded
  private final Ubicacion ubicacion;
  @Column
  private final LocalDateTime fechaHecho;
  @Column
  private final LocalDateTime fechaCarga;
  @Column
  private boolean eliminado = false;

  public Hecho(String titulo, String descripcion, Categoria categoria,
               String latitud, String longitud, LocalDateTime fechaHecho, LocalDateTime fechaCarga) {
    validarNotNull(titulo, descripcion, categoria, latitud, longitud, fechaHecho, fechaCarga);
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.ubicacion = new Ubicacion(latitud,longitud);
    this.fechaHecho = fechaHecho;
    this.fechaCarga = fechaCarga;
  }

  private void validarNotNull(String titulo, String descripcion, Categoria categoria,
                              String latitud, String longitud, LocalDateTime fechaHecho, LocalDateTime fechaCarga) {
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

  public boolean cumpleConBusqueda(String texto) {
    LevenshteinDistance distancia = new LevenshteinDistance();
    int tolerancia = 2; //tolera hasta dos errores por palabra

    String[] palabrasBusqueda =  texto.toLowerCase().split("\\s+");
    String textoBusqueda = titulo.toLowerCase() + " " + descripcion.toLowerCase();
    String[] tokens = textoBusqueda.split("\\s+");

    return Arrays.stream(palabrasBusqueda)
      .allMatch(palabra -> Arrays.stream(tokens)
        .anyMatch(token -> distancia.apply(token, palabra) <= tolerancia));
  }

  public String getProvincia() {
    return ubicacion.getProvincia();
  }

  public Integer getHoraDelHecho() {
    return fechaHecho.getHour();
  }
}