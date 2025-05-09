package ar.edu.utn.frba.dds;

/**
 * EXECPCION para cuando los valores del contructor sean invalidos (por ej= falta de valores).
 */
public class ColeccionInvalidaException extends RuntimeException {
  /**
   * muestra la causa por la cual se produjo esa exepcion .
   */
  public ColeccionInvalidaException(String causa) {
    super("No selecciono " + causa + " de la coleccion.");
  }
}