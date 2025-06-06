package ar.edu.utn.frba.dds;

import java.util.Optional;

public class SolicitudEdicionHecho {

  Hecho hecho;
  String nombreContribuyente;
  Optional<String> apellidoContribuyente;
  Optional<Integer> edadContribuyente;

  public SolicitudEdicionHecho(Hecho hecho, String nombreContribuyente, Optional<String> apellidoContribuyente, Optional<Integer> edadContribuyente) {
    this.hecho = hecho;
    this.nombreContribuyente = nombreContribuyente;
    this.apellidoContribuyente = apellidoContribuyente;
    this.edadContribuyente = edadContribuyente;
  }

  public Validacion() {
    // Validamos que el nombreContribuyente recibido no sea null ni vacío
    if (nombreContribuyente == null || nombreContribuyente.isBlank()) {
      throw new IllegalArgumentException("El nombre del contribuyente es obligatorio");
    }else if (!hecho.getNombreContribuyente().isPresent() || !hecho.getNombreContribuyente().get().equals(nombreContribuyente)) { // Validamos que el nombreContribuyente coincida con el del hecho
      throw new IllegalArgumentException("El nombre del contribuyente no coincide");
    }else if (apellidoContribuyente.isPresent()) {// Validamos que el apellido coincida si está presente
      if (!hecho.getApellidoContribuyente().isPresent() || !hecho.getApellidoContribuyente().get().equals(apellidoContribuyente.get())) {
        throw new IllegalArgumentException("El apellido no coincide");
      }
    }else if (apellidoContribuyente.isPresent()) {// Validamos que la edad coincida si está presente
      if (!hecho.getEdadContribuyente().isPresent() || !hecho.getEdadContribuyente().get().equals(edadContribuyente.get())) {
        throw new IllegalArgumentException("La edad no coincide");
      }
    }

  }

}
