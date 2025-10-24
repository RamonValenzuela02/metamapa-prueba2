package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.model.Usuario.TipoUsuario;
import io.github.flbulgarelli.jpa.extras.TransactionalOps;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;
import ar.edu.utn.frba.dds.repositorios.RepoUsuarios;

public class SessionController implements WithSimplePersistenceUnit, TransactionalOps {


  public void show(Context ctx) {
    if (ctx.sessionAttribute("user_id") != null) {
      ctx.redirect("/");
      return;
    }

    Map<String, Object> modelo = new HashMap<>();
    if ("true".equals(ctx.queryParam("error"))) {
      modelo.put("error", "Usuario o contraseña inválidas");
    }

    ctx.render("login.hbs", modelo);
  }

  public void create(Context ctx) {
    RepoUsuarios repo = RepoUsuarios.getInstance();
    String nombre = ctx.formParam("nombre");
    String password = ctx.formParam("password");

    var usuario = repo.buscarUsuario(nombre, password);

    if (usuario != null) {
      // Usuario encontrado -> guardamos la session
      ctx.sessionAttribute("user_id", usuario.getId());
      ctx.redirect("/");
    } else {
      ctx.redirect("/login?error=true");
    }
  }

  public void logout(Context ctx) {
    ctx.sessionAttribute("user_id", null);
    ctx.redirect("/login");
  }
}
