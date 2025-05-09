package ar.edu.utn.frba.dds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * clase que representa la carga de los hechos estaticos de un determinado path.
 */
public class FuenteCsv extends Fuente {

  private final String path;

  /**
   * Contructor de objetos, que va a necesitar el path en donde va a buscar la informacion.
   */
  public FuenteCsv(String path) {
    this.path = path;
  }

  /**
   * va a generar un FileReader, para poder hacer la lectura del archivo.
   */
  @Override
  public FileReader openFile() {
    try {
      return new FileReader(path, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * va a ir creando y guardando los hechos de la fuente. Y tambien los retorna.
   */
  @Override
  public List<Hecho> guardarHechos(FileReader file) {
    List<Hecho> hechos = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(file);
      String registro;
      while ((registro = br.readLine()) != null) {

        String[] tokens = registro.split(",");
        String titulo = tokens[0];
        String descripcion = tokens[1];
        Categoria categoria = Categoria.valueOf(tokens[2]);
        String latitud = tokens[3];
        String longitud = tokens[4];
        LocalDate fecha = LocalDate.parse(tokens[5]);

        Hecho hecho = new Hecho(titulo, descripcion, categoria, latitud, longitud, fecha);
        agregarHechoNuevo(hecho, hechos);
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return hechos;
  }

  /**
   * Cierro el FileReader que anteriormente lo creamos para leer un path.
   */
  @Override
  public void closeFile(FileReader file) {
    try {
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * verifica que no exista un hecho que tenga su mismo titulo,
   * si es asi lo pisa y si no lo carga normalmente.
   */
  private void agregarHechoNuevo(Hecho hecho, List<Hecho> hechos) {
    hechos.removeIf(h -> h.getTitulo().equalsIgnoreCase(hecho.getTitulo()));
    hechos.add(hecho);
  }
}
