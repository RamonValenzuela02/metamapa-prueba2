package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class RepoFuenteDemo {
  private List<Hecho> hechos;
  private static final RepoFuenteDemo INSTANCE = new RepoFuenteDemo();

  private RepoFuenteDemo() {
    hechos = new ArrayList<>();
  }

  public static RepoFuenteDemo getInstance() {
    return INSTANCE;
  }

  public void agregarHecho(Hecho hecho) {
    hechos.add(hecho);
  }

}
