package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class SolicitudTest {

  @Test
  public void solicitud() { // req 5
    List<SolicitudDeEliminacion> solicitudes = new ArrayList<>();
    FuenteCSV fuenteCSV = new FuenteCSV("D:\\UTN pc\\3er año\\Diseño de Sistemas de Informacion\\tpa-2025-26\\src\\test\\java\\ar\\edu\\utn\\frba\\dds\\prueba.csv");
    String titulo = fuenteCSV.getHechos().get(0).getTitulo();
    String motivo = "Ubicacion erronea";

    SolicitudDeEliminacion solicitud = new SolicitudDeEliminacion(titulo, motivo, solicitudes, fuenteCSV);

    assertEquals(1, solicitudes.size());
  }

  @Test
  public void aceptarSolicitud() { // req 6
    List<SolicitudDeEliminacion> solicitudes = new ArrayList<>();
    FuenteCSV fuenteCSV = new FuenteCSV("D:\\UTN pc\\3er año\\Diseño de Sistemas de Informacion\\tpa-2025-26\\src\\test\\java\\ar\\edu\\utn\\frba\\dds\\prueba.csv");
    String titulo = fuenteCSV.getHechos().get(0).getTitulo();
    String motivo = "Ubicacion erronea";

    new SolicitudDeEliminacion(titulo, motivo, solicitudes, fuenteCSV);

    List<SolicitudDeEliminacion> pendientes = solicitudes.stream()
        .filter(soli -> soli.getEstado().equals(Estado.PENDIENTE))
        .collect(Collectors.toList());

    SolicitudDeEliminacion soliAanalizar = pendientes.get(0);
    soliAanalizar.aceptar();

    assertEquals(Estado.ACEPTADA, soliAanalizar.getEstado());
  }

  @Test
  public void rechazarSolicitud() {  // req 6
    List<SolicitudDeEliminacion> solicitudes = new ArrayList<>();
    FuenteCSV fuenteCSV = new FuenteCSV("D:\\UTN pc\\3er año\\Diseño de Sistemas de Informacion\\tpa-2025-26\\src\\test\\java\\ar\\edu\\utn\\frba\\dds\\prueba.csv");
    String titulo = fuenteCSV.getHechos().get(0).getTitulo();
    String motivo = "Ubicacion erronea";

    new SolicitudDeEliminacion(titulo, motivo, solicitudes, fuenteCSV);

    List<SolicitudDeEliminacion> pendientes = solicitudes.stream()
        .filter(soli -> soli.getEstado().equals(Estado.PENDIENTE))
        .collect(Collectors.toList());

    SolicitudDeEliminacion soliAanalizar = pendientes.get(0);
    soliAanalizar.rechazar();

    assertEquals(Estado.RECHAZADA, soliAanalizar.getEstado());
  }
}