package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.HomeController;
import ar.edu.utn.frba.dds.controllers.SessionController;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import io.javalin.Javalin;
import java.util.HashMap;

public class Router implements SimplePersistenceTest {
  public void configure(Javalin app) {
    HomeController controller = new HomeController();
    SessionController session = new SessionController();

    app.before(ctx -> {
      entityManager().clear();
      ctx.contentType("text/html; charset=UTF-8");
    });



    //REQUERIMIENTO 1
    //muestra los hechos
    app.get("/", context -> context.redirect("/home"));
    app.get("/home", ctx -> ctx.render("home.hbs", controller.index(ctx)));

    //REQUERIMIENTO 2
    //crear hecho y crear solicitud de eliminacion
    app.get("/hecho/nuevo", ctx -> ctx.render("hecho.nuevo.hbs", new HashMap<>()));
    app.post("/hechos", controller::crearHecho);

    app.get("/hechos/{hechoId}/solEliminacion", ctx -> ctx.render("hechos.eliminar.hbs", controller.solicitarEliminacionForm(ctx)));
    app.post("/hechos/{hechoId}/solEliminacion", controller::solicitarEliminacion);

    //REQUERIMIENTO 3
    app.get("/login", session::show);
    app.post("/login", session::create);

    //REQUERIMIENTO 4
    //REQUERIMIENTO 5
    app.get("/coleccion/nuevo", ctx -> ctx.render("coleccion.nuevo.hbs", controller.formularioNuevaColeccion()));
    app.post("/colecciones", controller::crearColeccion);
    //REQUERIMIENTO 6

    //REQUERIMIENTO 7
    app.get("/solicitudesEliminacion", ctx -> ctx.render("solicitudesEliminacion.hbs", controller.listarSolicitudes(ctx)));
    app.post("/solicitudesEliminacion/{id}/aprobar", HomeController::aprobarSolicitud);
    app.post("/solicitudesEliminacion/{id}/rechazar", HomeController::rechazarSolicitud);
    //REQUERIMIENTO 8

  }
}
