package ar.edu.utn.frba.dds.domain.fuente;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Map;
import javax.persistence.Embeddable;

@Embeddable
public interface Conexion {
      Map<String, Object> siguienteHecho(URL url, LocalDateTime fechaUltimaConsulta);
}