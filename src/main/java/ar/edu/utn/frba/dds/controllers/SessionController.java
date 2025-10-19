package ar.edu.utn.frba.dds.controllers;

import io.javalin.http.Context;
import java.util.HashMap;
import java.util.Map;

public class SessionController {

  public void show(Context ctx){
    Map<String, Object> modelo = new HashMap<>();
    ctx.render("login.hbs", modelo);
  }
}
