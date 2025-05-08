package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


public class SolicitudTest {
  List<SolicitudDeEliminacion> solicitudes = new ArrayList<>();
  FuenteCSV fuenteCSV = new FuenteCSV("D:\\UTN pc\\3er año\\Diseño de Sistemas de Informacion\\tpa-2025-26\\src\\test\\java\\ar\\edu\\utn\\frba\\dds\\prueba.csv");
  List<Hecho> hechos = fuenteCSV.getHechos();
  String titulo = hechos.get(0).getTitulo();
  String motivo = "Ubicacion erronea";
  SolicitudDeEliminacion solicitud = new SolicitudDeEliminacion(titulo, motivo, solicitudes);
  @Test
  public void solicitud(){
    assertEquals(1, solicitudes.size());
  }
  @Test
  public void aceptarSolicitud(){
    List<SolicitudDeEliminacion> pendientes = solicitudes.stream().
            filter(soli -> soli.getEstado().
            equals(Estado.PENDIENTE)).toList();
    SolicitudDeEliminacion soliAanalizar = pendientes.get(0);
    soliAanalizar.aceptar();
    for (SolicitudDeEliminacion soliCitud : solicitudes) {
      System.out.println("ESTADO: " + soliCitud.estado);
    }
    pendientes = solicitudes.stream().
        filter(soli -> soli.getEstado().
            equals(Estado.PENDIENTE)).toList();
    assertEquals(0, pendientes.size());
  }
}
