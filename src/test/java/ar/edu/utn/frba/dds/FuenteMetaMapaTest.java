package ar.edu.utn.frba.dds;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.*;

public class FuenteMetaMapaTest {

    private WireMockServer wireMockServer;
    private FuenteMetaMapa fuente;

    @BeforeEach
    void iniciarMockServer() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
        configureFor("localhost", 8089);

        ObjectMapper mapper = new ObjectMapper();
        fuente = new FuenteMetaMapa("http://localhost:8089", mapper);
    }

    @AfterEach
    void detenerMockServer() {
        wireMockServer.stop();
    }

    @Test
    void puedeObtenerHechosDeUnaColeccionEnTiempoReal() {
        stubFor(get(urlPathEqualTo("/colecciones/123/hechos"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                  [
                    {
                      "titulo": "Incendio en fábrica",
                      "descripcion": "Un incendio afectó a una fábrica.",
                      "categoria": "INCENDIO_FORESTAL",
                      "latitud": "-34.6037",
                      "longitud": "-58.3816",
                      "fechaHecho": "2024-10-01",
                      "fechaCarga": "2024-10-02"
                    }
                  ]
                """)));

        List<Hecho> hechos = fuente.obtenerHechosDeColeccion("123", Optional.empty());

        assertEquals(1, hechos.size());
        Hecho hecho = hechos.get(0);
        assertEquals("Incendio en fábrica", hecho.getTitulo());
        assertEquals(Categoria.INCENDIO_FORESTAL, hecho.getCategoria());
        assertEquals(LocalDate.of(2024, 10, 1), hecho.getFechaHecho());
    }
}