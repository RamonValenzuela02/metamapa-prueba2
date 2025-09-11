package ar.edu.utn.frba.dds.repo;

import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.criterio.Categoria;
import ar.edu.utn.frba.dds.domain.fuente.Fuente;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RepoFuentesDelSistema implements WithSimplePersistenceUnit {
  private static final RepoFuentesDelSistema INSTANCE = new RepoFuentesDelSistema();

  private RepoFuentesDelSistema() {}

  public static RepoFuentesDelSistema getInstance() {
    return INSTANCE;
  }

  public void agregarFuente(Fuente fuente) {
    entityManager().persist(fuente);
  }

  public void agregarFuentes(List<Fuente> fuentesNuevas ) {
    fuentesNuevas.forEach(this::agregarFuente);
  }

  public List<Fuente> obtenerFuentes(){
    return entityManager()
      .createQuery("SELECT f FROM Fuente f", Fuente.class)
      .getResultList();
  }
  //full text search
  public List<Hecho> obtenerHechosSimilares(String texto) {
    return this.obtenerFuentes().stream()
      .flatMap(fuente -> fuente.obtenerHechos().stream())
      .filter(hecho -> hecho.cumpleConBusqueda(texto))
      .collect(Collectors.toList());
  }

  //¿Cuál es la categoría con mayor cantidad de hechos reportados?
  public Categoria categoriaConMayorCantidadDeHechos() {
    return this.obtenerFuentes().stream()
      .flatMap(fuente -> fuente.obtenerHechos().stream())
      .collect(Collectors.groupingBy(Hecho::getCategoria,Collectors.counting()))
      .entrySet().stream()
      .max(Map.Entry.comparingByValue())
      .map(Map.Entry::getKey)
      .orElse(null);
  }

  //¿En qué provincia se presenta la mayor cantidad de hechos de una cierta categoría?
  public String provinciaConMayorCantidadDeHechosDe(Categoria categoria) {
    return this.obtenerFuentes().stream()
      .flatMap(fuente -> fuente.obtenerHechos().stream())
      .filter(hecho -> hecho.getCategoria()==categoria)
      .collect(Collectors.groupingBy(Hecho::getProvincia,Collectors.counting()))
      .entrySet().stream()
      .max(Map.Entry.comparingByValue())
      .map(Map.Entry::getKey)
      .orElse(null);
  }
  //¿A qué hora del día ocurren la mayor cantidad de hechos de una cierta categoría?
  public Integer horaDelDiaQueOcurrenMasHechosDe(Categoria categoria) {
    return this.obtenerFuentes().stream()
      .flatMap(fuente -> fuente.obtenerHechos().stream())
      .filter(hecho -> hecho.getCategoria()==categoria)
      .collect(Collectors.groupingBy(Hecho::getHoraDelHecho,Collectors.counting()))
      .entrySet().stream()
      .max(Map.Entry.comparingByValue())
      .map(Map.Entry::getKey)
      .orElse(null);

  }
}
