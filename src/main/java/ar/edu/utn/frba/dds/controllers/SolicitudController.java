package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.model.Hecho.Hecho;
import ar.edu.utn.frba.dds.model.solicitud.DetectorDeSpamBasico;
import ar.edu.utn.frba.dds.model.solicitud.ServicioDeSolicitudesEliminacion;
import ar.edu.utn.frba.dds.model.solicitud.SolicitudDeEliminacion;
import ar.edu.utn.frba.dds.repositorios.RepoHechosDinamicos;
import ar.edu.utn.frba.dds.repositorios.RepoSolicitudesDeEliminacion;
import io.javalin.http.Context;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SolicitudController {
    public Map<String, Object> listarSolicitudes(@NotNull Context ctx) {
        RepoSolicitudesDeEliminacion repo = RepoSolicitudesDeEliminacion.getInstance();

        Map<String, Object> model = new HashMap<>();
        model.put("solicitudes",repo.getSolicitudes());
        model.put("cantidadSpam",repo.getCantidadDeSpam());
        return model;
    }


    public static void aprobarSolicitud(@NotNull Context context) {

        long id = Long.valueOf(context.pathParam("id"));
        RepoSolicitudesDeEliminacion repo = RepoSolicitudesDeEliminacion.getInstance();

        repo.withTransaction(() -> {
            SolicitudDeEliminacion solicitud = repo.getSolicitudPorId(id);
            if (solicitud != null) {
                solicitud.aceptar();
                solicitud.getHecho().setEliminado(true);
                repo.entityManager().merge(solicitud);
            }
        });

        boolean esHtmx = "true".equalsIgnoreCase(context.header("HX-Request"));

        if (esHtmx) {
            HomeController homeController = new HomeController();
            Map<String, Object> modelo = homeController.modeloHomeAdmin(context);
            context.render("partials/solicitudesPendientes.hbs",modelo);
        }else{
            context.redirect("/admin");
        }
    }

    public static void rechazarSolicitud(@NotNull Context context) {

        long id = Long.valueOf(context.pathParam("id"));
        RepoSolicitudesDeEliminacion repo = RepoSolicitudesDeEliminacion.getInstance();

        repo.withTransaction(() -> {
            SolicitudDeEliminacion solicitud = repo.getSolicitudPorId(id);
            if (solicitud != null) {
                solicitud.rechazar();
                repo.entityManager().merge(solicitud);
            }
        });

        boolean esHtmx = "true".equalsIgnoreCase(context.header("HX-Request"));

        if (esHtmx) {
            HomeController homeController = new HomeController();
            Map<String, Object> modelo = homeController.modeloHomeAdmin(context);
            context.render("partials/solicitudesPendientes.hbs",modelo);
            return;
        }
        context.redirect("/admin");
    }


    public Map<String,Object> solicitarEliminacionForm(@NotNull Context ctx) {
        Long hechoId = Long.parseLong(Objects.requireNonNull(ctx.queryParam("hechoId")));

        Hecho hecho = RepoHechosDinamicos.getInstance().obtenerHechoPorId(hechoId);

        Map<String,Object> model = new HashMap<>();
        model.put("hecho",hecho);
        return model;
    }

    public void solicitarEliminacion(@NotNull Context context) {

        long hechoId = Long.parseLong(Objects.requireNonNull(context.formParam("hechoId")));
        String motivo = context.formParam("motivo");

        Hecho hecho = RepoHechosDinamicos.getInstance().obtenerHechoPorId(hechoId);

        if (motivo.length() < 500) {
            Map<String, Object> model = new HashMap<>();
            model.put("hecho", hecho);
            model.put("motivo", motivo);
            model.put("error", "El motivo debe tener al menos 500 caracteres.");

            context.status(400)
                    .render("solEliminacion/solEliminacion.nueva.hbs", model);
            return;
        }

        SolicitudDeEliminacion solicitudDeEliminacion = new SolicitudDeEliminacion(hecho,motivo);
        ServicioDeSolicitudesEliminacion servicio = new ServicioDeSolicitudesEliminacion(new DetectorDeSpamBasico());

        RepoSolicitudesDeEliminacion.getInstance().withTransaction(() -> {
            servicio.registrarSolicituDeEliminacion(solicitudDeEliminacion);
        });

        context.redirect("/hecho/" + hechoId + "?ok=1");
    }
}
