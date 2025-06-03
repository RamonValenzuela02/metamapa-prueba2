package ar.edu.utn.frba.dds;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;

public class FuenteDemo implements Fuente {
  List<Hecho> hechos = new ArrayList<Hecho>();
  Conexion conexion;
  URL urlBase;
  DateTime ultimaConexion = new DateTime();

  public FuenteDemo(URL urlBase, Conexion conexion) {
    this.conexion = conexion;
    this.urlBase = urlBase;
  }

  public void obtenerSiguienteHecho() {
    Map<String, Object> mapConHecho= conexion.siguienteHecho(urlBase, ultimaConexion);
    if (mapConHecho != null) {
      Hecho hecho = getHechoDeMap(mapConHecho);
      hechos.add(hecho);
    }
    ultimaConexion = DateTime.now();
  }

  private Hecho getHechoDeMap(Map<String, Object> datos) {

    return new Hecho((String) datos.get("titulo"),
        (String) datos.get("descripcion"),
        Categoria.valueOf((String) datos.get("categoria")),
        (String) datos.get("latitud"),
        (String) datos.get("longitud"),
        (LocalDate) datos.get("fechaHecho"));
  }

  @Override
  public List<Hecho> obtenerHechos() {
    return hechos;
  }
}
