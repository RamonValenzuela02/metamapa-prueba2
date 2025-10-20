package ar.edu.utn.frba.dds.controllers;

import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Map;
import ar.edu.utn.frba.dds.repositorios.RepoUsuarios;

public class SessionController {

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
      var usuario = repo.buscarUsuario(ctx.formParam("nombre"),ctx.formParam("password"));
      ctx.sessionAttribute("user_id", usuario.getId());
      ctx.redirect("/");
    } catch (Exception e) {
      Map<String, Object> modelo = new HashMap<>();
      ctx.redirect("/login?error=true"); //Siempre dirige al get a menos que lo cambies
    }
  }
}
