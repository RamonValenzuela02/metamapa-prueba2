package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.model.Hecho;
import ar.edu.utn.frba.dds.model.fuente.Fuente;
import ar.edu.utn.frba.dds.repositorios.RepoFuentesDelSistema;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public class HomeController {
  public Map<String,Object> index(@NotNull Context ctx) {
    List<Fuente> fuentes = RepoFuentesDelSistema.getInstance().obtenerFuentes();
    List<Hecho> hechos = fuentes.stream()
      .flatMap(x-> x.obtenerHechos().stream())
      .toList();

    Map<String,Object> model = new HashMap<>();
    model.put("fuentes",fuentes);

    return model;
  }
}
