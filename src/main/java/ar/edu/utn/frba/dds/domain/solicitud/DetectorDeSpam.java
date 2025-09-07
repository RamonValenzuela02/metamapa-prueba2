package ar.edu.utn.frba.dds.domain.solicitud;

import javax.persistence.Embeddable;

@Embeddable
public interface DetectorDeSpam {
  boolean esSpam(String texto);
}