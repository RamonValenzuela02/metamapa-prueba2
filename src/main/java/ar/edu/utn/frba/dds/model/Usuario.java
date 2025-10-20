package ar.edu.utn.frba.dds.model;

import lombok.Getter;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Usuario {
  @Id
  @GeneratedValue
  @Getter
  private Long id;
  @Column
  @Getter
  private String nombre;
  @Column
  @Getter
  private String password;
  @Column
  @Getter
  private String tipo;

  public Usuario(String tipo, String nombre, String password) {
    this.tipo = tipo;
    this.nombre = nombre;
    this.password = password;
  }
}
