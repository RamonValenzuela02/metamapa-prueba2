package ar.edu.utn.frba.dds.domain.solicitud;

public interface DetectorDeSpam {
  boolean esSpam(String texto);
}