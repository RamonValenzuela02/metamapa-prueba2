package ar.edu.utn.frba.dds;

//lo hice para los test y que el criterio no me interfiera
public class CriterioCumplidorSiempre implements Criterio {
  @Override
  public Boolean cumpleCriterio(Hecho hecho) {
    return true;
  }
}