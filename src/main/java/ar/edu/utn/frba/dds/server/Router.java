package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.HomeController;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import io.javalin.Javalin;
import java.util.HashMap;

public class Router implements SimplePersistenceTest {
  public void configure(Javalin app) {
    HomeController controller = new HomeController();

    /*
    app.before(ctx -> {
      entityManager().clear();
    });
     */


    //REQUERIMIENTO 1 (me falta lo del mapa)
    app.get("/", context -> context.redirect("/home"));
    app.get("/home", ctx -> ctx.render("home.hbs", new HashMap<>()));

    //REQUERIMIENTO 2
    //crear hecho
    app.get("/hecho/nuevo", ctx -> ctx.render("hecho.nuevo.hbs", new HashMap<>()));
    app.post("/hechos", controller::crearHecho);
    //crear solicitud de eliminacion
    //app.get("/hechos/:id/eliminar", ctx -> ctx.render("hechos.eliminar.hbs", controller.solicitarEliminacionForm(ctx)));
    //app.post("/hechos/:id/eliminar", controller::solicitarEliminacion);

    //REQUERIMIENTO 3
    //login
    app.get("/login", ctx -> ctx.result("login"));

    //REQUERIMIENTO 4
    //REQUERIMIENTO 5
    //REQUERIMIENTO 6
    //REQUERIMIENTO 7
    //REQUERIMIENTO 8

  }
}
