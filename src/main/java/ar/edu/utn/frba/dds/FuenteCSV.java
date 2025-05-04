package ar.edu.utn.frba.dds;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FuenteCSV extends Fuente {

  private String path;

  public FuenteCSV(String path) {
    this.path = path;
  };

  @Override
  public FileReader openFile() {
    try{
      return new FileReader(path);
    }catch(IOException e){
      e.printStackTrace();
      return null;
    }
  }

  @Override
  public List<Hecho> guardarHechos(FileReader file){
    List<Hecho> hechos = new ArrayList<Hecho>();
    try{
      BufferedReader br = new BufferedReader(file);
      String registro;
      while((registro = br.readLine()) != null){

        String[] tokens = registro.split(",");
        String titulo = tokens[0];
        String descripcion = tokens[1];
        Categoria categoria = Categoria.valueOf(tokens[2]);
        double latitud = Double.valueOf(tokens[3]);
        double longitud = Double.valueOf(tokens[4]);
        LocalDate fecha = LocalDate.parse(tokens[5]);

        Hecho hecho = new Hecho(titulo, descripcion, categoria, latitud, longitud, fecha);

        agregarHechoNuevo(hecho,hechos);
      }
    }catch(IOException e){
      e.printStackTrace();
      return null;
    }
    return hechos;
  }

  @Override
  public void closeFile(FileReader file){
    try{
      file.close();
    }catch(IOException e){
      e.printStackTrace();
    }
  }
  private void agregarHechoNuevo(Hecho hecho,List<Hecho> hechos) {
    hechos.removeIf(h-> h.getTitulo().equalsIgnoreCase(hecho.getTitulo()));
    hechos.add(hecho);
  }
}
