package ar.edu.utn.frba.dds;

import java.io.FileReader;
import java.util.List;

/**
 * clase abstracta fuente que por ahora solo esta la CSV.
 */
public abstract class Fuente {

  /**
   * retorna los hechos que  pertenecen a la fuente.
   */
  public List<Hecho> getHechos() {
    FileReader file = openFile();
    List<Hecho> hechos = guardarHechos(file);
    closeFile(file);
    return hechos;
  }

  /**
   * abre el archivo en donde se encuentran los hechos.
   */
  protected abstract FileReader openFile();

  /**
   * guarda y retorna los hechos del archivo.
   */
  protected abstract List<Hecho> guardarHechos(FileReader file);

  /**
   * cierra archivo.
   */
  protected abstract void closeFile(FileReader file);

}
