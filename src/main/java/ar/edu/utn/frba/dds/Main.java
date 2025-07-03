package ar.edu.utn.frba.dds;

public class Main {
  public static void main(String[] args) {

    FuenteAgregadora agregadora = new FuenteAgregadora();

    agregadora.actualizarCache();
    System.out.println("Cache actualizado exitosamente.");
  }
}
