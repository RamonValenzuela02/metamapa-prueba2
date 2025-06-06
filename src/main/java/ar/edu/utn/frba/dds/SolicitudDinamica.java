package ar.edu.utn.frba.dds;

public class SolicitudDinamica {
  private Hecho hecho;
  private EstadoSolicitudDinamica estado;
  private String sugerencia;
  private Runnable cuandoEsAceptada;
  private Runnable cuandoEsTratada;

  public SolicitudDinamica(Hecho hecho, Runnable cuandoEsAceptada) {
    this.hecho = hecho;
    this.estado = EstadoSolicitudDinamica.PENDIENTE;
    this.cuandoEsAceptada = cuandoEsAceptada;
  }

  public void aceptar() {
    this.estado = EstadoSolicitudDinamica.ACCEPTADA;
    cuandoEsAceptada.run();
    cuandoEsTratada.run();
  }

  public void aceptarConSugerencia(String sugerencia) {
    this.sugerencia = sugerencia;
    this.estado = EstadoSolicitudDinamica.ACCEPTADA_CON_SUGERENCIA;
    cuandoEsAceptada.run();
    cuandoEsTratada.run();
  }

  public void rechazar() {
    this.estado = EstadoSolicitudDinamica.RECHAZADA;
    cuandoEsTratada.run();
  }

  public void setCallbackCuandoEsTratada(Runnable callback) {
    this.cuandoEsTratada= callback;
  }
}