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
    app.get("/", context -> context.redirect("/home"));
    app.get("/home", ctx -> ctx.render("home.hbs", controller.index(ctx)));
  }
}
