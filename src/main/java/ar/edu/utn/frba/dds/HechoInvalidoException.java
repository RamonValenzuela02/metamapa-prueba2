package ar.edu.utn.frba.dds;

/**
 * EXECPCION para cuando los valores del contructor sean invalidos (por ej= falta de valores).
 */
public class HechoInvalidoException extends RuntimeException {
  /**
   * muestra la causa por la cual se produjo esa exepcion .
   */
  public HechoInvalidoException(String causa) {
    super("No selecciono " + causa + " del hecho.");
  }
}