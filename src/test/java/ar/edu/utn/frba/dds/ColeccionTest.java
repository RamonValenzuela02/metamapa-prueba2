package ar.edu.utn.frba.dds;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class ColeccionTest {

  Coleccion coleccion1;
  Hecho unHecho;
  Fuente unaFuente;

  @Test
  @DisplayName("Creo Coleccion")

  void crearColeccion() {
    unHecho = new Hecho();
    String path = "agregar path para probar";
    CSV_importador importador = new CSV_importador();
    Set<Hecho> hechos_CSV = importador.importarHechos(path);
    unaFuente = new Fuente(hechos_CSV);
    coleccion1 = new Coleccion("Titulo","Test", unaFuente);
  }

}
