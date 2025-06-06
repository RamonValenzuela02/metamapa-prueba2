package ar.edu.utn.frba.dds;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class FuenteMetaMapa implements Fuente {

  private final Client client;
  private final String baseUrl;
  private final ObjectMapper mapper;
  List<Hecho> hechos = new ArrayList<>();

  public FuenteMetaMapa(String baseUrl, ObjectMapper mapper) {
    this.client = Client.create();
    this.baseUrl = baseUrl;
    this.mapper = mapper;
  }

  @Override
  public List<Hecho> obtenerHechosConCriterio(Criterio criterio) {
    return obtenerRespuesta("hecho", Optional.ofNullable(criterio));
  }

  @Override
  public List<Hecho> obtenerHechosConVariosCriterios(List<Hecho> hechosAfiltrar, List<Criterio> criterios) {
    return Fuente.super.obtenerHechosConVariosCriterios(hechos, criterios);
  }

  public List<Hecho> obtenerHechos() {
    return obtenerRespuesta("hecho", Optional.empty());
  }

  public List<Hecho> obtenerHechosDeColeccion(String idColeccion, Optional<Criterio> criterio) {
    String endpoint = "colecciones/" + idColeccion + "/hechos";
    return obtenerRespuesta(endpoint, criterio);
  }

  public void enviarSolicitudDeEliminacion(SolicitudDeEliminacion solicitud) {

    WebResource url = client.resource(baseUrl).path("solicitudes");

    WebResource.Builder builder = url.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

    // Creamos el JSON manualmente
    String body;
    try {
      body = mapper.writeValueAsString(Map.of(
          "tituloHecho", solicitud.getTituloHecho(),
          "motivo", solicitud.getMotivo()
      ));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error al serializar la solicitud a JSON", e);
    }

    ClientResponse respuesta = builder.post(ClientResponse.class, body);

    if (respuesta.getStatus() != 200 && respuesta.getStatus() != 201) {
      throw new RuntimeException("Error al enviar solicitud de eliminación: " + respuesta.getStatus());
    }
  }



  private List<Hecho> obtenerRespuesta(String endpoint, Optional<Criterio> criterio) { //obtengo hechos del sistema

    WebResource url = client.resource(baseUrl).path(endpoint); //le agrego el endpoint a la url

    if (criterio.isPresent()) {
      url = agregarQueryParams(url, criterio.get());
    }

    WebResource.Builder builder = url.accept(MediaType.APPLICATION_JSON);

    ClientResponse respuesta = builder.get(ClientResponse.class);

    if (respuesta.getStatus() != 200) {
      throw new RuntimeException("Error al obtener hechos de MetaMapa: " + respuesta.getStatus());
    }

    List<Hecho> hechos_aux = new ArrayList<>();

    try {
      String json = respuesta.getEntity(String.class); //extrae el body del json y lo convierte a string

      List<Map<String, Object>> datosHechos = mapper.readValue( // creo una lista de diccionarios, donde cada elemento representa un hecho
          json, new TypeReference<List<Map<String, Object>>>() {
          } // va a leer un json y lo convierte a lista de diccionarios
      );
      for (Map<String, Object> dato : datosHechos) {
        hechos_aux.add(construirHechoDesdeMap(dato));
      }
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return hechos_aux;
  }

  private WebResource agregarQueryParams(WebResource url, Criterio criterio) {

    if (criterio instanceof CriterioPorCategoria c) {
      url = url.queryParam("categoria", c.getCategoria().toString());
    }else if (criterio instanceof CriterioPorFechaHecho c) {
      url = url.queryParam("fechaDesde", c.getFechaDesde().toString()).queryParam("fechaHasta", c.getFechaHasta().toString());
    }else if (criterio instanceof CriterioPorFechaCarga c) {
      url = url.queryParam("fechaDesde", c.getFechaDesde().toString()).queryParam("fechaHasta", c.getFechaHasta().toString());
    }else if (criterio instanceof CriterioPorUbicacion c) {
      url = url.queryParam("latitud", c.getLatitud()).queryParam("longitud", c.getLongitud());
    }

    return url;
  }

  //agarra cada "campo" del diccionario para crear el hecho
  private Hecho construirHechoDesdeMap(Map<String, Object> dato) {

    String titulo = (String) dato.get("titulo");
    String descripcion = (String) dato.get("descripcion");
    Categoria categoria = Categoria.valueOf((String) dato.get("categoria"));
    String latitud = (String) dato.get("latitud");
    String longitud = (String) dato.get("longitud");
    LocalDate fechaHecho = LocalDate.parse((String) dato.get("fechaHecho"));
    LocalDate fechaCarga = LocalDate.parse((String) dato.get("fechaCarga"));

    return new Hecho(titulo, descripcion, categoria, latitud, longitud, fechaHecho, fechaCarga);
  }
}