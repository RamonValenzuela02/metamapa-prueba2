package ar.edu.utn.frba.dds.domain.exportarCSV;
import ar.edu.utn.frba.dds.domain.Hecho;
import ar.edu.utn.frba.dds.domain.criterio.Categoria;
import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;
import ar.edu.utn.frba.dds.domain.coleccion.Coleccion;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class ExportarCSV {

    private static final RepoFuentesDelSistema repoFuentes = RepoFuentesDelSistema.getInstance();

    public ExportarCSV(){};

    public void exportarProvinciaPorColeccion(Coleccion coleccion) {

        Map<String, Long> provinciaXCantidad = coleccion.getHechosConsensuados().stream()
                                        .collect(Collectors.groupingBy(Hecho::getProvincia, Collectors.counting()));

        Map.Entry<String, Long> provinciaConMayorCantidad = provinciaXCantidad.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        try (FileWriter fileWriter = new FileWriter("provincia_con_mayor_cantidad_por_coleccion.csv");
             CSVWriter writer = new CSVWriter(fileWriter)) {

            writer.writeNext(new String[] { "Coleccion", "Provincia", "Cantidad" });

            writer.writeNext(new String[] {
                    coleccion.getTitulo(),
                    provinciaConMayorCantidad.getKey(),
                    provinciaConMayorCantidad.getValue().toString()
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportarCategoriaConMayorCantidadDeHechos(){
        Categoria categoria = repoFuentes.categoriaConMayorCantidadDeHechos();

        try (
                FileWriter fileWriter = new FileWriter("categora_con_mayor_cantidad_de_hechos.csv");
                CSVWriter writer = new CSVWriter(fileWriter)
        ){
            writer.writeNext(new String[]{"Categoria"});
            writer.writeNext(new String[]{categoria.name()});
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportarProvinciaConMasHechosDe(Categoria categoria) {
        String provincia = repoFuentes.provinciaConMayorCantidadDeHechosDe(categoria);

        try (FileWriter fileWriter = new FileWriter("provincia_con_mayor_cantidad_de_hechos_de_categoria.csv");
             CSVWriter writer = new CSVWriter(fileWriter)) {

            writer.writeNext(new String[] { "Categoria", "Provincia" });
            writer.writeNext(new String[] {
                    categoria.name(),
                    provincia
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportarHoraPicoPorCategoria(Categoria categoria) {
        Integer hora = repoFuentes.horaDelDiaQueOcurrenMasHechosDe(categoria);

        try (FileWriter fileWriter = new FileWriter("hora_pico_por_categoria.csv");
             CSVWriter writer = new CSVWriter(fileWriter)) {

            writer.writeNext(new String[] { "Categoria", "Hora_Pico" });

            writer.writeNext(new String[] {
                    categoria.name(),
                    hora.toString()
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
