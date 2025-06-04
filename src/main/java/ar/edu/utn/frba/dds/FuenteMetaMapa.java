package ar.edu.utn.frba.dds;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

  private List<Hecho> obtenerRespuesta(String endpoint) { //obtengo hechos del sistema

    WebResource url = client.resource(baseUrl).path(endpoint); //le agrego el endpoint a la url

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
        Hecho hecho = construirHechoDesdeMap(dato);
        hechos_aux.add(hecho);
      }
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return hechos_aux;
  }

  @Override
  public List<Hecho> obtenerHechosConCriterio(Criterio criterio) {
    List<Hecho> hechos_aux = obtenerRespuesta("hecho");
    List<Hecho> hechos = hechos_aux.stream().filter(criterio::cumpleCriterio).toList();
    return hechos;
  }

  //agarra cada "campo" del diccionario para crear el hecho
  private Hecho construirHechoDesdeMap(Map<String, Object> dato) {
    // Asegurate de adaptar esto a cómo viene el JSON desde MetaMapa
    String titulo = (String) dato.get("titulo");
    String descripcion = (String) dato.get("descripcion");
    Categoria categoria = Categoria.valueOf((String) dato.get("categoria"));
    String latitud = (String) dato.get("latitud");
    String longitud = (String) dato.get("longitud");
    LocalDate fechaHecho = LocalDate.parse((String) dato.get("fechaHecho"));

    return new Hecho(titulo, descripcion, categoria, latitud, longitud, fechaHecho);
  }
}
