package ar.edu.utn.frba.dds;

import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.opencsv.CSVReader;
import java.util.stream.Collectors;

/**
 * clase que representa la carga de los hechos estaticos de un determinado path.
 */
public class FuenteCsv implements Fuente {
  private final String path;

  public FuenteCsv(String path) {
    this.path = path;
  }

  public List<Hecho> obtenerHechos() {
    List<Hecho> hechos = new ArrayList<>();
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

  @Override
  public List<Hecho> obtenerHechosConCriterio(Criterio criterio) {
    return obtenerHechos().stream().filter(criterio::cumpleCriterio).collect(Collectors.toList());
  }

}
