package ar.edu.utn.frba.dds;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
/*
class FuenteDinamicaTest {

  @DisplayName("Como persona contribuyente, deseo poder crear un hecho a partir de una fuente dinámica. ") // req 1
  @Test
  void agregarHechoNoAgregaDirectamenteAListaDeHechos() {
    Hecho hecho = new Hecho("habia una vez",
        "holaholhola",
        Categoria.INCENDIO_FORESTAL,
        "1234",
        "5678",
        LocalDate.now(),
            LocalDate.now());
    FuenteDinamica fuente = new FuenteDinamica();
    fuente.agregarHecho(hecho);

    assertFalse(fuente.getHechos().contains(hecho));
  }

  @Test
  void agregarHechoYAceptarLaSolicitud() {
    Hecho hecho = new Hecho("habia una vez",
        "holaholhola",
        Categoria.INCENDIO_FORESTAL,
        "1234",
        "5678",
        LocalDate.now(),
            LocalDate.now());
    FuenteDinamica fuente = new FuenteDinamica();
    fuente.agregarHecho(hecho);

    SolicitudDinamica solicitud = fuente.getPendientes().get(0);

    solicitud.aceptar();
    assertTrue(fuente.getHechos().contains(hecho));
  }

  @Test
  void agregarHechoYRechazarSolicitud() {
    Hecho hecho = new Hecho("habia una vez",
        "holaholhola",
        Categoria.INCENDIO_FORESTAL,
        "1234",
        "5678",
        LocalDate.now(),
            LocalDate.now());
    FuenteDinamica fuente = new FuenteDinamica();
    fuente.agregarHecho(hecho);

    SolicitudDinamica solicitud = fuente.getPendientes().get(0);

    solicitud.rechazar();
    assertFalse(fuente.getHechos().contains(hecho));
  }

  @Test
  void agregarHechoYAceptarLaSolicitudConSugerencia() {
    Hecho hecho = new Hecho("habia una vez",
        "holaholhola",
        Categoria.INCENDIO_FORESTAL,
        "1234",
        "5678",
        LocalDate.now(),
            LocalDate.now());
    FuenteDinamica fuente = new FuenteDinamica();
    fuente.agregarHecho(hecho);

    SolicitudDinamica solicitud = fuente.getPendientes().get(0);

    solicitud.aceptarConSugerencia("latitud invalida");
    
    assertTrue(fuente.getHechos().contains(hecho));
  }
}
 */