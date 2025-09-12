package ar.edu.utn.frba.dds.domain;

import ar.edu.utn.frba.dds.domain.criterio.Categoria;
import ar.edu.utn.frba.dds.domain.ubicacion.ClaseMoldeProvincia;
import ar.edu.utn.frba.dds.repo.RepoFuentesDelSistema;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ExportarCSV {

    private static final RepoFuentesDelSistema repo = RepoFuentesDelSistema.getInstance();

    public void exportarCategoriaConMayorCantidadDeHechos(){
        Categoria categoria = repo.categoriaConMayorCantidadDeHechos();

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
}
