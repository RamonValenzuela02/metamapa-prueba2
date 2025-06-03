package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Test;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class SolicitudTest {

  @Test
  public void solicitud() throws Exception { // req 5
    List<SolicitudDeEliminacion> solicitudes = new ArrayList<>();
    URL resource = getClass().getClassLoader().getResource("prueba.csv");
    if (resource == null) {
      throw new RuntimeException("No se encontró el archivo prueba.csv");
    }
    File csvFile = Paths.get(resource.toURI()).toFile();
    FuenteCsv fuenteCSV = new FuenteCsv(csvFile.getAbsolutePath());
    String titulo = fuenteCSV.obtenerHechos().get(0).getTitulo();
    String motivo = "Ubicacion erronea";

    SolicitudDeEliminacion solicitud = new SolicitudDeEliminacion(titulo, motivo, solicitudes, fuenteCSV);

    assertEquals(1, solicitudes.size());
  }

  @Test
  public void aceptarSolicitud() throws Exception { // req 6
    List<SolicitudDeEliminacion> solicitudes = new ArrayList<>();
    URL resource = getClass().getClassLoader().getResource("prueba.csv");
    if (resource == null) {
      throw new RuntimeException("No se encontró el archivo prueba.csv");
    }
    File csvFile = Paths.get(resource.toURI()).toFile();
    FuenteCsv fuenteCSV = new FuenteCsv(csvFile.getAbsolutePath());
    String titulo = fuenteCSV.obtenerHechos().get(0).getTitulo();
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
  public void rechazarSolicitud() throws Exception {  // req 6
    List<SolicitudDeEliminacion> solicitudes = new ArrayList<>();
    URL resource = getClass().getClassLoader().getResource("prueba.csv");
    if (resource == null) {
      throw new RuntimeException("No se encontró el archivo prueba.csv");
    }
    File csvFile = Paths.get(resource.toURI()).toFile();
    FuenteCsv fuenteCSV = new FuenteCsv(csvFile.getAbsolutePath());
    String titulo = fuenteCSV.obtenerHechos().get(0).getTitulo();
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