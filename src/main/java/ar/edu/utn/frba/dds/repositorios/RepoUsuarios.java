package ar.edu.utn.frba.dds.repositorios;

import ar.edu.utn.frba.dds.model.Usuario;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.List;

public class RepoUsuarios implements WithSimplePersistenceUnit {
  private static final RepoUsuarios INSTANCE = new RepoUsuarios();

  private RepoUsuarios() {}

  public static RepoUsuarios getInstance() { return INSTANCE; }

  public void agregar(Usuario usuario) { entityManager().persist(usuario); }
/*
  public Usuario buscarUsuario(int id) {
    return entityManager().find(Usuario.class, id);
  }
*/

  public Usuario buscarUsuario(String nombre, String password) {
      List<Usuario> resultados = entityManager()
          .createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.password = :password", Usuario.class)
          .setParameter("nombre", nombre)
          .setParameter("password", password)
          .getResultList();
      return resultados.isEmpty() ? null : resultados.get(0);
  }

}