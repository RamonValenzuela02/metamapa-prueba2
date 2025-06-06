package ar.edu.utn.frba.dds;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class GestorDeSpam {
    private DetectorDeSpam detectorDeSpam;
    @Getter
    private final List<SolicitudDeEliminacion> solicitudes = new ArrayList<>();

    public void GestorDeSolicitudes(DetectorDeSpam detectorDeSpam) {
        this.detectorDeSpam = detectorDeSpam;
    }

    public GestorDeSpam(DetectorDeSpam detectorDeSpam) {
        this.detectorDeSpam = detectorDeSpam;
    }

    public void registrarSolicituDeEliminacion(SolicitudDeEliminacion solicitud) {
        if (detectorDeSpam.esSpam(solicitud.getMotivo())) {
            solicitud.rechazar();
        }
        solicitudes.add(solicitud);
    }

}
