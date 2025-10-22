package ar.edu.utn.frba.dds.scripts;


import ar.edu.utn.frba.dds.model.Hecho.Hecho;
import ar.edu.utn.frba.dds.model.criterio.Categoria;
import ar.edu.utn.frba.dds.model.fuente.Fuente;
import ar.edu.utn.frba.dds.model.fuente.FuenteDinamica;
import ar.edu.utn.frba.dds.repositorios.RepoFuentesDelSistema;
import ar.edu.utn.frba.dds.repositorios.RepoHechosDinamicos;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDateTime;
import ar.edu.utn.frba.dds.model.Usuario.TipoUsuario;
import ar.edu.utn.frba.dds.model.Usuario.Usuario;
import ar.edu.utn.frba.dds.repositorios.RepoUsuarios;
import java.util.Arrays;


public class Bootstrap implements WithSimplePersistenceUnit {
  public void init() {
    withTransaction(() -> {
      Hecho h1 = new Hecho("incendio", "incendio en buenos aires", Categoria.INCENDIO_FORESTAL, "1000","10001", LocalDateTime.now(),LocalDateTime.now());
      Hecho h2 = new Hecho("accidente", "accidente en buenos aires", Categoria.ACCIDENTE_VIAL, "1000","10001", LocalDateTime.now(),LocalDateTime.now());
      Hecho h3 = new Hecho("homicidio", "homicidio en buenos aires", Categoria.HOMICIDOS_DOLOSOS, "1000","10001", LocalDateTime.now(),LocalDateTime.now());
      Hecho h4 = new Hecho("incendio", "incendio en la patagonia", Categoria.INCENDIO_FORESTAL, "1000","10001", LocalDateTime.now(),LocalDateTime.now());
      Hecho h5 = new Hecho("incendio", "incendio en Codoba", Categoria.INCENDIO_FORESTAL, "1000","10001", LocalDateTime.now(),LocalDateTime.now());

      Fuente fuenteDinamica = new FuenteDinamica();
      RepoHechosDinamicos.getInstance().agregarHecho(h1);
      RepoHechosDinamicos.getInstance().agregarHecho(h2);
      RepoHechosDinamicos.getInstance().agregarHecho(h3);
      RepoHechosDinamicos.getInstance().agregarHecho(h4);
      RepoHechosDinamicos.getInstance().agregarHecho(h5);

      entityManager().persist(h1);
      entityManager().persist(h2);
      entityManager().persist(h3);
      entityManager().persist(h4);
      entityManager().persist(h5);
      entityManager().persist(fuenteDinamica);

      RepoFuentesDelSistema.getInstance().agregarFuente(fuenteDinamica);
    });

    RepoUsuarios repo = RepoUsuarios.getInstance();
    withTransaction(() -> {
      var usuarios = Arrays.asList(
          new Usuario(TipoUsuario.ADMINISTRADOR, "Facundo", "Facundo"),
          new Usuario(TipoUsuario.CONTRIBUYENTE, "Juan", "Juan"),
          new Usuario(TipoUsuario.ADMINISTRADOR, "Mati", "Mati"));
      usuarios.forEach(repo::agregar);
    });
  
}
