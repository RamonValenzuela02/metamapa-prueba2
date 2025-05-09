package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


public class SolicitudTest {
  List<SolicitudDeEliminacion> solicitudes = new ArrayList<>();
  FuenteCSV fuenteCSV = new FuenteCSV("C:\\Users\\santi\\OneDrive\\Documentos\\UTN\\Diseño\\Java\\tpa-2025-26\\src\\test\\java\\ar\\edu\\utn\\frba\\dds\\prueba.csv");
  List<Hecho> hechos = fuenteCSV.getHechos();
  String titulo = hechos.get(0).getTitulo();
  String motivo = "Ubicacion erronea";
  SolicitudDeEliminacion solicitud = new SolicitudDeEliminacion(titulo, motivo, solicitudes, fuenteCSV); // req 5
  @Test
  public void solicitud(){
    assertEquals(1, solicitudes.size());
  }
  @Test
  public void aceptarSolicitud(){ // req 6
    List<SolicitudDeEliminacion> pendientes = solicitudes.stream().
            filter(soli -> soli.getEstado().
            equals(Estado.PENDIENTE)).collect(Collectors.toList());
    SolicitudDeEliminacion soliAanalizar = pendientes.get(0);
    soliAanalizar.aceptar();
    pendientes.remove(0);
    assertEquals(0, pendientes.size());
    assertEquals(Estado.ACEPTADA, soliAanalizar.getEstado());
  }
  @Test
  public void rechazarSolicitud(){ // req 6
    List<SolicitudDeEliminacion> pendientes = solicitudes.stream().
        filter(soli -> soli.getEstado().
            equals(Estado.PENDIENTE)).collect(Collectors.toList());
    SolicitudDeEliminacion soliAanalizar = pendientes.get(0);
    soliAanalizar.rechazar();
    pendientes.remove(0);
    assertEquals(0, pendientes.size());
    assertEquals(Estado.RECHAZADA, soliAanalizar.getEstado());
  }
}
