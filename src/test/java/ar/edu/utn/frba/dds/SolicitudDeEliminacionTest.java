package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.domain.solicitud.DetectorDeSpam;
import ar.edu.utn.frba.dds.domain.solicitud.Estado;
import ar.edu.utn.frba.dds.domain.fuente.FuenteDinamica;
import ar.edu.utn.frba.dds.domain.solicitud.SolicitudDeEliminacion;
import ar.edu.utn.frba.dds.repo.RepoSolicitudesDeEliminacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SolicitudDeEliminacionTest {
  DetectorDeSpam detector;

  @BeforeEach
  public void mockeoDetectorDeSpam() {
    detector = mock(DetectorDeSpam.class);
  }

  @Test
  public void crearSolicitudDeEliminacion() {
    when(detector.esSpam("no es compatible titulo con su descripcion")).thenReturn(false);
    RepoSolicitudesDeEliminacion gestor = new RepoSolicitudesDeEliminacion(detector);
    FuenteDinamica fuente = new FuenteDinamica();

    //creo la solicitud
    SolicitudDeEliminacion solicitud = new SolicitudDeEliminacion("no compatibilidad",
        "no es compatible titulo con su descripcion",
        fuente);
    //se agrega a la lista de solicitudes y se verifica si es spam
    gestor.registrarSolicituDeEliminacion(solicitud);

    assertTrue(gestor.getSolicitudes().contains(solicitud));
    assertEquals(Estado.PENDIENTE, solicitud.getEstado());

  }

  @Test
  public void rechazarPorSpamSolicitudDeEliminacion() {
    when(detector.esSpam("no es compatible titulo con su descripcion")).thenReturn(true);
    RepoSolicitudesDeEliminacion gestor = new RepoSolicitudesDeEliminacion(detector);
    FuenteDinamica fuente = new FuenteDinamica();

    //creo la solicitud
    SolicitudDeEliminacion solicitud = new SolicitudDeEliminacion("no compatibilidad",
        "no es compatible titulo con su descripcion",
        fuente);
    gestor.registrarSolicituDeEliminacion(solicitud);

    assertTrue(gestor.getSolicitudes().contains(solicitud));
    assertEquals(Estado.RECHAZADA, solicitud.getEstado());
  }
}