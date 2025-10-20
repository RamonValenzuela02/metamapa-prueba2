package ar.edu.utn.frba.dds.scripts;

import ar.edu.utn.frba.dds.model.Usuario;
import ar.edu.utn.frba.dds.repositorios.RepoUsuarios;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.Arrays;

public class Bootstrap implements WithSimplePersistenceUnit {
  public void init() {/*
    RepoUsuarios repo = RepoUsuarios.getInstance();
    withTransaction(() -> {
      var usuarios = Arrays.asList(
          new Usuario("Admin", "Facundo", "Facundo"),
          new Usuario("Contribuyente", "Juan", "Juan"),
          new Usuario("Admin", "Mati", "Mati"));
      usuarios.forEach(repo::agregar);
    });*/
  }
}
