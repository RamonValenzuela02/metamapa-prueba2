package ar.edu.utn.frba.dds;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ar.edu.utn.frba.dds.*;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
public class FuenteMetaMapaTest{

    private FuenteMetaMapa fuenteMock;
    private Coleccion coleccion;

    @BeforeEach
    public void setup() {
        // Creamos el mock de la fuente
        fuenteMock = mock(FuenteMetaMapa.class);

        // Creamos dos hechos
        Hecho hecho1 = new Hecho(
            "Hecho 1", "Descripcion 1", Categoria.INCENDIO_FORESTAL,
            "-34.61", "-58.38",
            LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 2)
        );

        Hecho hecho2 = new Hecho(
            "Hecho 2", "Descripcion 2", Categoria.INCENDIO_FORESTAL,
            "-34.60", "-58.37",
            LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 20)
        );

        // Definimos qué devuelve el mock cuando se llama a obtenerHechosConCriterio
        when(fuenteMock.obtenerHechosConCriterio(any(Criterio.class)))
            .thenReturn(List.of(hecho1, hecho2));

        // Creamos un criterio dummy para usar en la coleccion
        Criterio criterioDummy = mock(Criterio.class);

        coleccion = new Coleccion("handle_test","Titulo Test", "Descripcion Test", fuenteMock, criterioDummy);
    }

    @Test
    public void testObtenerHechosDevuelveListaDesdeFuente() {
        List<Hecho> hechos = coleccion.obtenerHechos();

        // Verificamos que la lista tenga los hechos mockeados
        assertEquals(2, hechos.size());
        assertEquals("Hecho 1", hechos.get(0).getTitulo());
        assertEquals("Hecho 2", hechos.get(1).getTitulo());
    }
}
*/