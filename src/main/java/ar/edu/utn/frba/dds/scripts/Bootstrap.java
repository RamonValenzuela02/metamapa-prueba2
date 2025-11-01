package ar.edu.utn.frba.dds.scripts;


import ar.edu.utn.frba.dds.model.Hecho.Hecho;
import ar.edu.utn.frba.dds.model.Multimedia.ArchivoMultimedia;
import ar.edu.utn.frba.dds.model.coleccion.Coleccion;
import ar.edu.utn.frba.dds.model.coleccion.ColeccionBuilder;
import ar.edu.utn.frba.dds.model.consenso.AlgoritmoConsenso;
import ar.edu.utn.frba.dds.model.criterio.Categoria;
import ar.edu.utn.frba.dds.model.criterio.Criterio;
import ar.edu.utn.frba.dds.model.criterio.CriterioPorCategoria;
import ar.edu.utn.frba.dds.model.fuente.Fuente;
import ar.edu.utn.frba.dds.model.fuente.FuenteDemo;
import ar.edu.utn.frba.dds.model.fuente.FuenteDinamica;
import ar.edu.utn.frba.dds.model.fuente.FuenteEstatica;
import ar.edu.utn.frba.dds.model.solicitud.DetectorDeSpamBasico;
import ar.edu.utn.frba.dds.model.solicitud.ServicioDeSolicitudesEliminacion;
import ar.edu.utn.frba.dds.model.solicitud.SolicitudDeEliminacion;
import ar.edu.utn.frba.dds.repositorios.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.time.LocalDateTime;
import ar.edu.utn.frba.dds.model.Usuario.TipoUsuario;
import ar.edu.utn.frba.dds.model.Usuario.Usuario;

import java.util.Arrays;
import java.util.List;


public class Bootstrap implements WithSimplePersistenceUnit {
  public void init() {
    withTransaction(() -> {
      Hecho h1 = new Hecho("incendio", "incendio en Cordoba", Categoria.INCENDIO_FORESTAL, "-31.427502308472217", "-64.15248731961331", LocalDateTime.now(), LocalDateTime.now());
      Hecho h2 = new Hecho("accidente", "accidente en buenos aires", Categoria.ACCIDENTE_VIAL, "-34.63590479382364", "-58.44512378698377", LocalDateTime.now(), LocalDateTime.now());
      Hecho h3 = new Hecho("homicidio", "homicidio en buenos aires", Categoria.HOMICIDOS_DOLOSOS, "-34.62158055780353", "-58.44372922151555", LocalDateTime.now(), LocalDateTime.now());
      Hecho h4 = new Hecho("incendio", "incendio en la patagonia", Categoria.INCENDIO_FORESTAL, "-41.572616430443965", "-70.67058748910377", LocalDateTime.now(), LocalDateTime.now());
      Hecho h5 = new Hecho("incendio", "incendio en Cordoba", Categoria.INCENDIO_FORESTAL, "-31.92098099373357", "-64.46001571183002", LocalDateTime.now(), LocalDateTime.now());

      Fuente fuenteDinamica = new FuenteDinamica();
      RepoHechosDinamicos.getInstance().agregarHecho(h1);
      RepoHechosDinamicos.getInstance().agregarHecho(h2);
      RepoHechosDinamicos.getInstance().agregarHecho(h3);
      RepoHechosDinamicos.getInstance().agregarHecho(h4);
      RepoHechosDinamicos.getInstance().agregarHecho(h5);

      RepoFuentesDelSistema.getInstance().agregarFuente(fuenteDinamica);

      Coleccion colIncendiosForestales = new ColeccionBuilder()
              .conTitulo("Incendios Forestales")
              .conHandle("incendios")
              .conDescripcion("Incendios Forestales en el pais")
              .conCriterios(List.of (new CriterioPorCategoria(Categoria.INCENDIO_FORESTAL)))
              .conAlgoritmoConsenso(AlgoritmoConsenso.CONSENSO_ABSOLUTO)
              .conFuente(fuenteDinamica)
              .crear();

      Coleccion colAccidentes = new ColeccionBuilder()
              .conTitulo("Accidentes")
              .conHandle("accidentes")
              .conDescripcion("Accidentes en el pais")
              .conCriterios(List.of (new CriterioPorCategoria(Categoria.ACCIDENTE_VIAL)))
              .conFuente(fuenteDinamica)
              .crear();

      Coleccion colHomicidiosDolosos = new ColeccionBuilder()
              .conTitulo("Homicidios Dolosos")
              .conHandle("homicidios_dolosos")
              .conDescripcion("Homicidios en el pais")
              .conCriterios(List.of (new CriterioPorCategoria(Categoria.HOMICIDOS_DOLOSOS)))
              .conFuente(fuenteDinamica)
              .crear();

      RepoDeColecciones.getInstance().agregarColeccion(colIncendiosForestales);
      RepoDeColecciones.getInstance().agregarColeccion(colAccidentes);
      RepoDeColecciones.getInstance().agregarColeccion(colHomicidiosDolosos);

      //SOLICITUDES DE ELIMINACION BASE
      SolicitudDeEliminacion s1 = new SolicitudDeEliminacion(h1,"mala redaccion de contenido", fuenteDinamica);
      SolicitudDeEliminacion s2 = new SolicitudDeEliminacion(h2,"ubicacion erronea", fuenteDinamica);

      RepoSolicitudesDeEliminacion.getInstance().registrarSolicituDeEliminacion(s1);
      RepoSolicitudesDeEliminacion.getInstance().registrarSolicituDeEliminacion(s2);

    });

    // usuarios
    withTransaction(() -> {
      RepoUsuarios repo = RepoUsuarios.getInstance();
      repo.agregar(new Usuario(TipoUsuario.ADMINISTRADOR, "Facundo", "Facundo"));
      repo.agregar(new Usuario(TipoUsuario.CONTRIBUYENTE, "Juan", "Juan"));
      repo.agregar(new Usuario(TipoUsuario.ADMINISTRADOR, "Mati", "Mati"));
    });

  }

}
