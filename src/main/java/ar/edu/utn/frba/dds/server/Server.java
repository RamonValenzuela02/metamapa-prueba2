package ar.edu.utn.frba.dds.server;

import ar.edu.utn.frba.dds.scripts.Bootstrap;
import ar.edu.utn.frba.dds.server.templates.JavalinHandlebars;
import ar.edu.utn.frba.dds.server.templates.JavalinRenderer;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.staticfiles.Location;

public class Server {
  public void start() {
    var app = Javalin.create(config -> {
      initializeStaticFiles(config);
      initializeTemplating(config);
    });

    new Router().configure(app);

    int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "9001"));
    app.start(port);
  }

  private void initializeTemplating(JavalinConfig config) {
    config.fileRenderer(
      new JavalinRenderer().register("hbs", new JavalinHandlebars())
    );
  }

  private static void initializeStaticFiles(JavalinConfig config) {
    config.staticFiles.add(staticFileConfig -> {
      staticFileConfig.hostedPath = "/assets";
      staticFileConfig.directory = "/assets";
    });

    config.staticFiles.add(staticFileConfig -> {
      staticFileConfig.hostedPath = "/images";
      staticFileConfig.directory = "/images";
    });

    config.staticFiles.add(staticFiles -> {
      staticFiles.hostedPath = "/uploads";
      staticFiles.directory = "uploads";
      staticFiles.location = Location.EXTERNAL;
    });
  }
}

