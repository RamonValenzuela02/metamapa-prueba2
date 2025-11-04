package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.HechoController;
import ar.edu.utn.frba.dds.controllers.HomeController;
import ar.edu.utn.frba.dds.controllers.SessionController;
import ar.edu.utn.frba.dds.controllers.SolicitudController;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import io.javalin.Javalin;

public class Router implements SimplePersistenceTest {
  public void configure(Javalin app) {
    HomeController homeController = new HomeController();
    HechoController hechoController = new HechoController();
    SolicitudController solicitudController = new SolicitudController();
    SessionController session = new SessionController();

    app.before(ctx -> {
      entityManager().clear();
      ctx.contentType("text/html; charset=UTF-8");
    });

    app.get("/admin", homeController::showAdminHome);

    //REQUERIMIENTO 1
    //muestra los hechos
    app.get("/", context -> context.redirect("/home"));
    app.get("/home", homeController::showHome);

    //REQUERIMIENTO 2
    //crear hecho y crear solicitud de eliminacion
    app.get("/hecho/nuevo", hechoController::formHechoNuevo);
    app.post("/hechos", hechoController::crearHecho);
    app.get("/hecho/{id}", hechoController::ver);

    app.get("/solEliminacion/nueva", ctx -> ctx.render("solEliminacion/solEliminacion.nueva.hbs", solicitudController.solicitarEliminacionForm(ctx)));
    app.post("/solEliminacion/nueva", solicitudController::solicitarEliminacion);

    //REQUERIMIENTO 3
    app.get("/login", session::show);
    app.post("/login", session::create);
    app.get("/logout", session::logout);

    //REQUERIMIENTO 4
    //REQUERIMIENTO 5
    app.get("/coleccion/nuevo", ctx -> ctx.render("coleccion/coleccion.hecho.nuevo.hbs", homeController.formularioNuevaColeccion()));
    app.post("/colecciones", homeController::crearColeccion);
    //REQUERIMIENTO 6

    //REQUERIMIENTO 7
    app.get("/solicitudesEliminacion", ctx -> ctx.render("solEliminacion/solicitudesEliminacion.hbs", solicitudController.listarSolicitudes(ctx)));
    app.post("/admin/solicitudes/{id}/aprobar", SolicitudController::aprobarSolicitud);
    app.post("/admin/solicitudes/{id}/rechazar", SolicitudController::rechazarSolicitud);
    //REQUERIMIENTO 8

  }
}
