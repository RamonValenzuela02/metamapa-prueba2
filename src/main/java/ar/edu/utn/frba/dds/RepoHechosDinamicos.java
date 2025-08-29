package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class RepoHechosDinamicos {
  private List<Hecho> hechos;
  private static final RepoHechosDinamicos INSTANCE = new RepoHechosDinamicos();

  private RepoHechosDinamicos() {
    hechos = new ArrayList<>();
  }

  public static RepoHechosDinamicos getInstance() {
    return INSTANCE;
  }

  public void agregarHecho(Hecho hecho) {
    hechos.add(hecho);
  }
}
