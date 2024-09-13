package com.bimbonet.bimbonet_lealtad.Services;

import com.bimbonet.bimbonet_lealtad.Entities.Recompensa;
import com.bimbonet.bimbonet_lealtad.Repository.RecompensaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RecompensaServiceTest {

    @InjectMocks
    private RecompensaService recompensaService;

    @Mock
    private RecompensaRepository recompensaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarRecompensa() {
        // Preparar
        Recompensa recompensa = new Recompensa();
        recompensa.setNombre("Recompensa 1");
        recompensa.setValor(100);

        // Simular el comportamiento del repositorio
        when(recompensaRepository.save(any(Recompensa.class))).thenAnswer(invocation -> {
            Recompensa saved = invocation.getArgument(0);
            saved.setDateCreated(LocalDateTime.now()); // Simula la persistencia
            return saved;
        });

        // Actuar
        Recompensa result = recompensaService.registrarRecompensa(recompensa);

        // Afirmar
        assertNotNull(result.getDateCreated());
        assertEquals(recompensa.getNombre(), result.getNombre());
        assertEquals(recompensa.getValor(), result.getValor());
        verify(recompensaRepository, times(1)).save(recompensa);
    }

    @Test
    void testCanjearRecompensa() {
        // Preparar
        Recompensa recompensa = new Recompensa();
        recompensa.setNombre("Recompensa 1");
        recompensa.setValor(100);

        // Simular el comportamiento del repositorio
        when(recompensaRepository.save(any(Recompensa.class))).thenAnswer(invocation -> {
            Recompensa saved = invocation.getArgument(0);
            saved.setDateCreated(LocalDateTime.now()); // Simula la persistencia
            return saved;
        });

        // Actuar
        Recompensa result = recompensaService.canjearRecompensa(recompensa);

        // Afirmar
        assertNotNull(result.getDateCreated());
        assertEquals(recompensa.getNombre(), result.getNombre());
        assertEquals(recompensa.getValor(), result.getValor());
        verify(recompensaRepository, times(1)).save(recompensa);
    }
}
