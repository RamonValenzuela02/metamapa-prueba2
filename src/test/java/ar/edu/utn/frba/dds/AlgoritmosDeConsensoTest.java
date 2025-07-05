package ar.edu.utn.frba.dds;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlgoritmosDeConsensoTest {

    @Test
    public void siDosFuentesTienenElMismoHechoEsteEsConsensuado() {
        Hecho hecho = new Hecho(
                "Incendio Forestal",
                "Incendio Forestal en Bariloche",
                Categoria.INCENDIO_FORESTAL,
                "10.0",
                "20.0",
                LocalDate.of(2025, 6, 30),
                LocalDate.of(2025, 6, 30));

        Fuente fuente1 = mock(Fuente.class);
        Fuente fuente2 = mock(Fuente.class);

        when(fuente1.obtenerHechos()).thenReturn(List.of(hecho));
        when(fuente2.obtenerHechos()).thenReturn(List.of(hecho));

        AlgoritmoConsenso algoritmo = new ConsensoMultiplesMenciones();
        boolean hechoConsensuado = algoritmo.estaConsensuado(hecho, List.of(fuente1, fuente2));

        assertTrue(hechoConsensuado);
    }

    @Test
    public void dosFuentesConMismoTituloPeroConAtributosDistintosNoEsConsensuado() {
        Hecho hecho1 = new Hecho(
                "Incendio Forestal",
                "Incendio en Bariloche",
                Categoria.INCENDIO_FORESTAL,
                "10.0",
                "20.0",
                LocalDate.of(2025, 6, 30),
                LocalDate.of(2025, 6, 30));

        Hecho hecho2 = new Hecho(
                "Incendio Forestal",
                "Incendio Misiones",
                Categoria.INCENDIO_FORESTAL,
                "10.0",
                "20.0",
                LocalDate.of(2025, 6, 30),
                LocalDate.of(2025, 6, 30));

        Fuente fuente1 = mock(Fuente.class);
        Fuente fuente2 = mock(Fuente.class);

        when(fuente1.obtenerHechos()).thenReturn(List.of(hecho1));
        when(fuente2.obtenerHechos()).thenReturn(List.of(hecho1));

        AlgoritmoConsenso algoritmo = new ConsensoMultiplesMenciones();
        boolean hechoConsensuado = algoritmo.estaConsensuado(hecho1, List.of(fuente1, fuente2));

        assertFalse(hechoConsensuado);
    }

    @Test
    public void siDosFuentesDeTresContienenElHechoEsteEsConsensuado() {
        Hecho hecho = new Hecho(
                "Incendio Forestal",
                "Incendio en Bariloche",
                Categoria.INCENDIO_FORESTAL,
                "10.0",
                "20.0",
                LocalDate.of(2025, 6, 30),
                LocalDate.of(2025, 6, 30));

        Fuente fuente1 = mock(Fuente.class);
        Fuente fuente2 = mock(Fuente.class);
        Fuente fuente3 = mock(Fuente.class);

        when(fuente1.obtenerHechos()).thenReturn(List.of(hecho));
        when(fuente2.obtenerHechos()).thenReturn(List.of(hecho));
        when(fuente3.obtenerHechos()).thenReturn(List.of());  // No le agrego el hecho a la fuente

        AlgoritmoConsenso algoritmo = new ConsensoMayoriaSimple();
        boolean consensuado = algoritmo.estaConsensuado(hecho, List.of(fuente1, fuente2, fuente3));

        assertTrue(consensuado);
    }

    @Test
    public void siUnaFuenteDeTresContieneElHechoEsteNoEsConsensuado() {
        Hecho hecho = new Hecho(
                "Incendio Forestal",
                "Incendio en Bariloche",
                Categoria.INCENDIO_FORESTAL,
                "10.0",
                "20.0",
                LocalDate.of(2025, 6, 30),
                LocalDate.of(2025, 6, 30));

        Fuente fuente1 = mock(Fuente.class);
        Fuente fuente2 = mock(Fuente.class);
        Fuente fuente3 = mock(Fuente.class);

        when(fuente1.obtenerHechos()).thenReturn(List.of(hecho));
        when(fuente2.obtenerHechos()).thenReturn(List.of());
        when(fuente3.obtenerHechos()).thenReturn(List.of());

        AlgoritmoConsenso algoritmo = new ConsensoMayoriaSimple();
        boolean consensuado = algoritmo.estaConsensuado(hecho, List.of(fuente1, fuente2, fuente3));

        assertFalse(consensuado);
    }

    @Test
    public void siTodasLasFuentesContienenAlHechoEsteEsConsensuado() {
        Hecho hecho = new Hecho(
                "Incendio Forestal",
                "Incendio en Bariloche",
                Categoria.INCENDIO_FORESTAL,
                "10.0",
                "20.0",
                LocalDate.of(2025, 6, 30),
                LocalDate.of(2025, 6, 30));

        Fuente fuente1 = mock(Fuente.class);
        Fuente fuente2 = mock(Fuente.class);
        Fuente fuente3 = mock(Fuente.class);

        when(fuente1.obtenerHechos()).thenReturn(List.of(hecho));
        when(fuente2.obtenerHechos()).thenReturn(List.of(hecho));
        when(fuente3.obtenerHechos()).thenReturn(List.of(hecho));

        AlgoritmoConsenso algoritmo = new ConsensoAbsoluta();
        boolean consensuado = algoritmo.estaConsensuado(hecho, List.of(fuente1, fuente2, fuente3));

        assertTrue(consensuado);
    }

    @Test
    public void siAlMenosUnaFuenteNoContieneAlHechoEsteNoEsConsensuado() {
        Hecho hecho = new Hecho(
                "Incendio Forestal",
                "Incendio en Bariloche",
                Categoria.INCENDIO_FORESTAL,
                "10.0",
                "20.0",
                LocalDate.of(2025, 6, 30),
                LocalDate.of(2025, 6, 30));

        Fuente fuente1 = mock(Fuente.class);
        Fuente fuente2 = mock(Fuente.class);
        Fuente fuente3 = mock(Fuente.class);

        when(fuente1.obtenerHechos()).thenReturn(List.of());
        when(fuente2.obtenerHechos()).thenReturn(List.of(hecho));
        when(fuente3.obtenerHechos()).thenReturn(List.of(hecho));

        AlgoritmoConsenso algoritmo = new ConsensoAbsoluta();
        boolean consensuado = algoritmo.estaConsensuado(hecho, List.of(fuente1, fuente2, fuente3));

        assertFalse(consensuado);
    }
}