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

  public class FuenteMetaMapa extends Fuente {

    // Atributo que representa la API para conectarse al servicio externo
    private final Api api;

    // Constructor que recibe una instancia de Api y la guarda
    public FuenteMetaMapa(Api api) {
      this.api = api;
    }

    // Implementación del metodo abstracto de Fuente.
    // Devuelve todos los hechos sin ningún criterio (GET a /hecho)
    @Override
    public List<Hecho> obtenerHechos() {
      return api.getHechos("hecho", Optional.empty());
    }

    // Obtiene hechos pertenecientes a una colección específica, con un criterio opcional
    public List<Hecho> obtenerHechosDeColeccion(String idColeccion, Optional<List<Criterio>> criterios) {
      // Arma el endpoint dinámicamente con el id de la colección
      String endpoint = "colecciones/" + idColeccion + "/hechos";
      // Llama a la API con ese endpoint y el criterio si existe
      return api.getHechos(endpoint, criterios);
    }

    // Envía una solicitud de eliminación de un hecho a la API (POST a /solicitudes)
    public void enviarSolicitudDeEliminacion(SolicitudDeEliminacion solicitud) {
      // Arma un JSON con los datos de la solicitud y lo envía por POST
      api.postJson("solicitudes", Map.of(
              "tituloHecho", solicitud.getTituloHecho(),
              "motivo", solicitud.getMotivo()
      ));
    }
  }
