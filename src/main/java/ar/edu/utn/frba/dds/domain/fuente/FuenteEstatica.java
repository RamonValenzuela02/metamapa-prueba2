package ar.edu.utn.frba.dds.domain.fuente;

import ar.edu.utn.frba.dds.domain.criterio.Categoria;
import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;

/**
 * clase que representa la carga de los hechos estaticos de un determinado path.
 */
public class FuenteEstatica extends Fuente {
  List<Hecho> hechos = new ArrayList<>();
  private final String path;

  public FuenteEstatica(String path) {
    this.path = path;
    RepoFuentesDelSistema.getInstance().agregarFuente(this);
  }

  public List<Hecho> obtenerHechos() {
    try (CSVReader reader = new CSVReader(new FileReader(path))) {
      String[] fila;
      while ((fila = reader.readNext()) != null) {
        String titulo = fila[0];
        String descripcion = fila[1];
        Categoria categoria = Categoria.valueOf(fila[2]);
        String latitud = fila[3];
        String longitud = fila[4];
        LocalDate fecha = LocalDate.parse(fila[5]);

        Hecho hecho = new Hecho(titulo, descripcion, categoria, latitud, longitud, fecha, fecha);
        agregarHechoNuevo(hecho, hechos);
      }
    } catch (IOException | CsvValidationException e) {
      e.printStackTrace();
    }
    return hechos;
  }

  private void agregarHechoNuevo(Hecho hecho, List<Hecho> hechos) {
    hechos.removeIf(h -> h.getTitulo().equalsIgnoreCase(hecho.getTitulo()));
    hechos.add(hecho);
  }
}