package ar.edu.utn.frba.dds.model.Hecho;

import static java.util.Objects.requireNonNull;

import ar.edu.utn.frba.dds.model.Multimedia.ArchivoMultimedia;
import ar.edu.utn.frba.dds.model.ubicacion.Ubicacion;
import ar.edu.utn.frba.dds.model.criterio.Categoria;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Setter;
import org.apache.commons.text.similarity.LevenshteinDistance;


@Getter
@Entity
public class Hecho {
  @Id
  @GeneratedValue
  @Getter
  private Long id;
  @Column
  private String titulo;
  @Column
  private String descripcion;
  @Enumerated(EnumType.STRING)
  private Categoria categoria;
  @Embedded
  private Ubicacion ubicacion;
  @Column
  private LocalDateTime fechaHecho;
  @Column
  private LocalDateTime fechaCarga;
  @Column
  @Setter
  private boolean eliminado = false;
  @OneToMany(mappedBy = "hecho", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<ArchivoMultimedia> archivosMultimedia = new ArrayList<>();

 public Hecho(){};

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

  public void agregarArchivo(ArchivoMultimedia nuevoArchivo) {
  }
}