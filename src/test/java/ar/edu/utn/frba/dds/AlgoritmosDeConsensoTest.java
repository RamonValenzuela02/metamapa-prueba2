package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlgoritmosDeConsensoTest {

    @Test
    public void dosFuentesConElMismoHechoEsConsensuado(){
        Hecho hecho = new Hecho("Incendio Forestal", "Incendio Forestal en Bariloche",
                Categoria.INCENDIO_FORESTAL,"10.0", "20.0",
                LocalDate.of(2025,06,39),LocalDate.of(2025,06,30));

        Fuente fuente1 = mock(Fuente.class);
        Fuente fuente2 = mock(Fuente.class);

        when(fuente1.obtenerHechos()).thenReturn(List.of(hecho));
        when(fuente2.obtenerHechos()).thenReturn(List.of(hecho));

        AlgoritmoConsenso algoritmo = new ConsensoMultiplesMenciones();
        boolean hechoConsensuado = algoritmo.estaConsensuado(hecho, List.of(fuente1, fuente2));

        assertTrue(hechoConsensuado);
    };
};