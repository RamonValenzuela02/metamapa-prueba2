package ar.edu.utn.frba.dds;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import org.joda.time.DateTime;

public interface Conexion {
      Map<String, Object> siguienteHecho(URL url, LocalDateTime fechaUltimaConsulta);
}