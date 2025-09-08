package ar.edu.utn.frba.dds.domain.fuente;

import ar.edu.utn.frba.dds.domain.criterio.Categoria;
import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class FuenteDemo extends  Fuente {
  @Embedded
  private final Conexion conexion;
  @Transient
  private final URL url;
  @Column
  private LocalDateTime fechaUltimaConsulta;
  @OneToMany
  private List<Hecho> hechosDemo;

  public FuenteDemo(Conexion conexion, URL url) {
    this.conexion = conexion;
    this.url = url;
    this.fechaUltimaConsulta = LocalDateTime.now();
    this.hechosDemo = new ArrayList<>();
    RepoFuentesDelSistema.getInstance().agregarFuente(this);
  }

  public void actualizarHechos() {
    Map<String, Object> datosHecho = conexion.siguienteHecho(url, fechaUltimaConsulta);
    while (datosHecho != null) {
      Hecho hecho = mapearHecho(datosHecho);
      hechosDemo.add(hecho);
      fechaUltimaConsulta = LocalDateTime.now();
      datosHecho = conexion.siguienteHecho(url, fechaUltimaConsulta);
    }
  }

  private Hecho mapearHecho(Map<String, Object> datos) {
    String titulo = (String) datos.get("titulo");
    String descripcion = (String) datos.get("descripcion");
    Categoria categoria = Categoria.fromString((String) datos.get("categoria"));
    String latitud = (String) datos.get("latitud");
    String longitud = (String) datos.get("longitud");
    LocalDate fechaHecho = (LocalDate) datos.get("fechaHecho");
    LocalDate fechaCarga = (LocalDate) datos.get("fechaCarga");

    return new Hecho(titulo, descripcion, categoria, latitud, longitud, fechaHecho, fechaCarga);
  }

  public List<Hecho> obtenerHechos() {
    return hechosDemo;
  }
}