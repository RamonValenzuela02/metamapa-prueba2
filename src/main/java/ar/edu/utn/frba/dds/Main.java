package ar.edu.utn.frba.dds;

public class Main {
  public static void main(String[] args) {
    // Crear o recuperar la fuente agregadora (esto depende de tu diseño real)
    FuenteAgregadora agregadora = new FuenteAgregadora();

    // fuentes de ejemplo
    agregadora.agregarFuente(new FuenteEstatica());
    agregadora.agregarFuente(new FuenteDinamica());



    agregadora.actualizarCache();

    System.out.println("Cache actualizado exitosamente.");
  }
}
