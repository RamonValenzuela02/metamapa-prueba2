package ar.edu.utn.frba.dds;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class FuenteAgregadora extends Fuente {
    private List<Fuente> fuentesAgregadas;
    //private Map<String, Hecho> cacheLocal; // Para optimización
   // private LocalDateTime ultimaActualizacionCache;
   // private int tiempoExpiracionCache = 30; // minutos

    public FuenteAgregadora() {
      this.fuentesAgregadas = new ArrayList<>();
    }

    public void agregarFuente(Fuente fuente) {
      if (!this.fuentesAgregadas.contains(fuente)) {
        this.fuentesAgregadas.add(fuente);
       // invalidarCache(); // Cache obsoleto al agregar fuentes
      }
    }

    public void removerFuente(Fuente fuente) {
      this.fuentesAgregadas.remove(fuente);
     // invalidarCache();
    }

    public List<Fuente> getFuentes() {
      return new ArrayList<>(fuentesAgregadas);
    }

    @Override
    public List<Hecho> obtenerHechos() {
      return fuentesAgregadas.stream().flatMap(fuente -> fuente.obtenerHechos().stream()).collect(Collectors.toList());
    }
    /*

    private boolean cacheVigente() {
      return ultimaActualizacionCache != null &&
          ultimaActualizacionCache.plusMinutes(tiempoExpiracionCache).isAfter(LocalDateTime.now()) &&
          !cacheLocal.isEmpty();
    }

    private void invalidarCache() {
      cacheLocal.clear();
      ultimaActualizacionCache = null;
    }

    private List<Hecho> obtenerHechosYActualizarCache() {
      Map<String, List<Hecho>> hechosPorTitulo = new HashMap<>();
      List<Hecho> todosLosHechos = new ArrayList<>();

      // 1. Recopilar hechos de todas las fuentes
      for (Fuente fuente : fuentesAgregadas) {
        try {
          List<Hecho> hechosDeEstaFuente = fuente.obtenerHechos();
          todosLosHechos.addAll(hechosDeEstaFuente);

          // Agrupar por título para análisis de consenso
          for (Hecho hecho : hechosDeEstaFuente) {
            hechosPorTitulo.computeIfAbsent(hecho.getTitulo(), k -> new ArrayList<>()).add(hecho);
          }
        } catch (Exception e) {
          System.err.println("Error al obtener hechos de fuente: " + e.getMessage());
          // Continuar con las otras fuentes
        }
      }

      // 2. Eliminar duplicados y aplicar lógica de consenso si existe
      List<Hecho> hechosFinales = procesarHechos(hechosPorTitulo);

      // 3. Actualizar cache
      actualizarCache(hechosFinales);

      return hechosFinales;
    }

    private List<Hecho> procesarHechos(Map<String, List<Hecho>> hechosPorTitulo) {
      Set<Hecho> hechosUnicos = new LinkedHashSet<>();

      for (Map.Entry<String, List<Hecho>> entry : hechosPorTitulo.entrySet()) {
        List<Hecho> hechosConMismoTitulo = entry.getValue();

        if (hechosConMismoTitulo.size() == 1) {
          // Solo una fuente tiene este hecho
          hechosUnicos.add(hechosConMismoTitulo.get(0));
        } else {
          // Múltiples fuentes - procesar duplicados/variaciones
          hechosUnicos.addAll(procesarHechosConMismoTitulo(hechosConMismoTitulo));
        }
      }

      return new ArrayList<>(hechosUnicos);
    }

    private List<Hecho> procesarHechosConMismoTitulo(List<Hecho> hechos) {
      Map<String, Hecho> hechosUnicos = new HashMap<>();

      for (Hecho hecho : hechos) {
        String claveUnica = generarClaveUnica(hecho);
        if (!hechosUnicos.containsKey(claveUnica)) {
          hechosUnicos.put(claveUnica, hecho);
        }
      }

      return new ArrayList<>(hechosUnicos.values());
    }

    private String generarClaveUnica(Hecho hecho) {
      return hecho.getTitulo() + "|" +
          hecho.getDescripcion() + "|" +
          hecho.getCategoria() + "|" +
          hecho.getLatitud() + "|" +
          hecho.getLongitud();
    }

    private void actualizarCache(List<Hecho> hechos) {
      cacheLocal.clear();
      for (Hecho hecho : hechos) {
        cacheLocal.put(generarClaveUnica(hecho), hecho);
      }
      ultimaActualizacionCache = LocalDateTime.now();
    }




// ========== CLASE PARA TAREAS ASÍNCRONAS ==========
package ar.edu.utn.frba.dds;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

  public class GestorTareasAgreagador {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public void programarActualizacionCache(FuenteAgregadora agregador, int intervalMinutos) {
      scheduler.scheduleAtFixedRate(
          () -> {
            try {
              System.out.println("Actualizando cache del agregador...");
              agregador.forzarActualizacionCache();
              System.out.println("Cache actualizado. Hechos: " + agregador.getTamanioCache());
            } catch (Exception e) {
              System.err.println("Error actualizando cache: " + e.getMessage());
            }
          },
          intervalMinutos, // delay inicial
          intervalMinutos, // período
          TimeUnit.MINUTES
      );
    }

  }

     */
}
