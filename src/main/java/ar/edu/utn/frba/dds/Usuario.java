package ar.edu.utn.frba.dds;

import java.util.List;

public class Usuario {
  private String nombre;
  private String apellido;
  private Integer edad;
  private TipoUsuario tipo;

  public Usuario(String nombre, String apellido, Integer edad, TipoUsuario tipo) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
    this.tipo = tipo;
  }


  public List<Hecho> visualizar(Coleccion coleccion) {
     return coleccion.hechos;
  }

//  public void subirHechoA( ,Coleccion coleccion) {
 //   coleccion.hechos.add()
 // }


}
