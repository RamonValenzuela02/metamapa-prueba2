package ar.edu.utn.frba.dds;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

import ar.edu.utn.frba.dds.domain.solicitud.SolicitudDinamica;
import ar.edu.utn.frba.dds.repo.RepoHechosDinamicos;
import java.time.LocalDate;
import ar.edu.utn.frba.dds.domain.criterio.Categoria;
import ar.edu.utn.frba.dds.domain.fuente.FuenteDinamica;
import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.repo.RepoSolicitudesDinamicas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FuenteDinamicaTest {
  RepoSolicitudesDinamicas repoSolicitudes = RepoSolicitudesDinamicas.getInstance();

  @BeforeEach
  void limpiarRepos() {
    RepoSolicitudesDinamicas.getInstance().getSolicitudes().clear();
    RepoHechosDinamicos.getInstance().getHechos().clear();
  }
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

    assertFalse(fuente.obtenerHechos().contains(hecho));
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

    SolicitudDinamica solicitud  = RepoSolicitudesDinamicas.getInstance().getSolicitudes().get(0);
    solicitud.aceptar();

    assertTrue(fuente.obtenerHechos().contains(hecho));
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

    SolicitudDinamica solicitud  = RepoSolicitudesDinamicas.getInstance().getSolicitudes().get(0);

    solicitud.rechazar();
    assertFalse(fuente.obtenerHechos().contains(hecho));
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

    SolicitudDinamica solicitud  = RepoSolicitudesDinamicas.getInstance().getSolicitudes().get(0);

    solicitud.aceptarConSugerencia("latitud invalida");
    
    assertTrue(fuente.obtenerHechos().contains(hecho));
  }
}
