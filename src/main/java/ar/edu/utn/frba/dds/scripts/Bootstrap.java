package ar.edu.utn.frba.dds.scripts;


import ar.edu.utn.frba.dds.model.Hecho.Hecho;
import ar.edu.utn.frba.dds.model.criterio.Categoria;
import ar.edu.utn.frba.dds.model.fuente.Fuente;
import ar.edu.utn.frba.dds.model.fuente.FuenteDinamica;
import ar.edu.utn.frba.dds.model.solicitud.DetectorDeSpamBasico;
import ar.edu.utn.frba.dds.model.solicitud.ServicioDeSolicitudesEliminacion;
import ar.edu.utn.frba.dds.model.solicitud.SolicitudDeEliminacion;
import ar.edu.utn.frba.dds.repositorios.RepoFuentesDelSistema;
import ar.edu.utn.frba.dds.repositorios.RepoHechosDinamicos;
import ar.edu.utn.frba.dds.repositorios.RepoSolicitudesDeEliminacion;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDateTime;
import ar.edu.utn.frba.dds.model.Usuario.TipoUsuario;
import ar.edu.utn.frba.dds.model.Usuario.Usuario;
import ar.edu.utn.frba.dds.repositorios.RepoUsuarios;
import java.util.Arrays;


public class Bootstrap implements WithSimplePersistenceUnit {
  public void init() {
    withTransaction(() -> {
      Hecho h1 = new Hecho("incendio", "incendio en buenos aires", Categoria.INCENDIO_FORESTAL, "1000", "10001", LocalDateTime.now(), LocalDateTime.now());
      Hecho h2 = new Hecho("accidente", "accidente en buenos aires", Categoria.ACCIDENTE_VIAL, "1000", "10001", LocalDateTime.now(), LocalDateTime.now());
      Hecho h3 = new Hecho("homicidio", "homicidio en buenos aires", Categoria.HOMICIDOS_DOLOSOS, "1000", "10001", LocalDateTime.now(), LocalDateTime.now());
      Hecho h4 = new Hecho("incendio", "incendio en la patagonia", Categoria.INCENDIO_FORESTAL, "1000", "10001", LocalDateTime.now(), LocalDateTime.now());
      Hecho h5 = new Hecho("incendio", "incendio en Codoba", Categoria.INCENDIO_FORESTAL, "1000", "10001", LocalDateTime.now(), LocalDateTime.now());

      Fuente fuenteDinamica = new FuenteDinamica();
      RepoHechosDinamicos.getInstance().agregarHecho(h1);
      RepoHechosDinamicos.getInstance().agregarHecho(h2);
      RepoHechosDinamicos.getInstance().agregarHecho(h3);
      RepoHechosDinamicos.getInstance().agregarHecho(h4);
      RepoHechosDinamicos.getInstance().agregarHecho(h5);

      RepoFuentesDelSistema.getInstance().agregarFuente(fuenteDinamica);

      //SOLICITUDES DE ELIMINACION BASE
      SolicitudDeEliminacion s1 = new SolicitudDeEliminacion(h1,"mala redaccion de contenido", fuenteDinamica);
      SolicitudDeEliminacion s2 = new SolicitudDeEliminacion(h2,"ubicacion erronea", fuenteDinamica);

      RepoSolicitudesDeEliminacion.getInstance().registrarSolicituDeEliminacion(s1);
      RepoSolicitudesDeEliminacion.getInstance().registrarSolicituDeEliminacion(s2);

      RepoUsuarios repo = RepoUsuarios.getInstance();
        var usuarios = Arrays.asList(
          new Usuario(TipoUsuario.ADMINISTRADOR, "Facundo", "Facundo"),
          new Usuario(TipoUsuario.CONTRIBUYENTE, "Juan", "Juan"),
          new Usuario(TipoUsuario.ADMINISTRADOR, "Mati", "Mati"));
        usuarios.forEach(repo::agregar);
      });

  }

}
