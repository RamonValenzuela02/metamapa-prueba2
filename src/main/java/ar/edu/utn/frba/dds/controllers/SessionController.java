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

  public void show(Context ctx){
    if (ctx.sessionAttribute("user_id") != null) {
      ctx.redirect("/");
    }
    Map<String, Object> modelo = new HashMap<>();
    if ("true".equals(ctx.queryParam("error"))){
      modelo.put("error", "usuario o contraseña invalidas");
    }
    ctx.render("login.hbs", modelo);
  }

  public void create(Context ctx) {
    try {
      RepoUsuarios repo = RepoUsuarios.getInstance();
      String nombre = ctx.formParam("nombre");
      String password = ctx.formParam("password");
      //var usuario = repo.buscarUsuario(nombre,password);

      //ctx.sessionAttribute("user_id", usuario.getId());
      ctx.render("home.administrador.hbs", new HashMap() {{}});
    } catch (Exception e) {
      Map<String, Object> modelo = new HashMap<>();
      ctx.redirect("/login?error=true");
    }
  }
}
