package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.controllers.*;
import io.github.flbulgarelli.jpa.extras.test.SimplePersistenceTest;
import io.javalin.Javalin;

public class Router implements SimplePersistenceTest {
  public void configure(Javalin app) {
    HomeController homeController = new HomeController();
    HechoController hechoController = new HechoController();
    SolicitudController solicitudController = new SolicitudController();
    ColeccionController coleccionController = new ColeccionController();
    SessionController session = new SessionController();

    app.before(ctx -> {
      entityManager().clear();
      ctx.contentType("text/html; charset=UTF-8");
    });

    app.get("/", context -> context.redirect("/home"));
    app.get("/home", homeController::showHome);
    app.get("/admin", homeController::showAdminHome);

    app.get("/hecho/nuevo", hechoController::formHechoNuevo);
    app.post("/hechos", hechoController::crearHecho);
    app.get("/hecho/{id}", hechoController::ver);

    app.get("/solicitud/nueva", solicitudController::solicitarEliminacionForm);
    app.post("/solicitud/nueva", solicitudController::solicitarEliminacion);

    app.get("/login", session::show);
    app.post("/login", session::create);
    app.get("/logout", session::logout);

    app.get("/admin/colecciones", coleccionController::listarColecciones);
    app.get("/admin/coleccion/nueva", coleccionController::formNuevaColeccion);
    app.post("/admin/colecciones", coleccionController::crearColeccion);

    app.get("/admin/solicitudes", solicitudController::listarSolicitudes);
    app.post("/admin/solicitudes/{id}/aprobar", solicitudController::aprobarSolicitud);
    app.post("/admin/solicitudes/{id}/rechazar", solicitudController::rechazarSolicitud);
  }
}
