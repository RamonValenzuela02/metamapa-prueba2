package ar.edu.utn.frba.dds;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class FuenteAgregadora extends Fuente {
    private List<Fuente> fuentesAgregadas;
    private LocalDateTime ultimaActualizacion = LocalDateTime.MIN;

    public FuenteAgregadora() {
      this.fuentesAgregadas = new ArrayList<>();
    }

    @Override
    public List<Fuente> fuentesDelNodo() {
        return fuentesAgregadas;
    }

    public void agregarFuente(Fuente fuente) {
      if (!this.fuentesAgregadas.contains(fuente)) {
        this.fuentesAgregadas.add(fuente);
      }
    }

    public void removerFuente(Fuente fuente) {
      this.fuentesAgregadas.remove(fuente);
    }

    @Override
    public List<Hecho> obtenerHechos() {
      return hechos;
    }

    public void actualizarCache() {
    this.hechos = this.fuentesAgregadas.stream()
        .flatMap(fuente -> fuente.obtenerHechos().stream())
        .collect(Collectors.toList());
    this.ultimaActualizacion = LocalDateTime.now();
  }

}
