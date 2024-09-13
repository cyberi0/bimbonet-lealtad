package com.bimbonet.bimbonet_lealtad.Services;

import com.bimbonet.bimbonet_lealtad.Entities.UsuarioRecompensa;
import com.bimbonet.bimbonet_lealtad.Repository.CanjeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CanjeServiceTest {

    @InjectMocks
    private CanjeService canjeService;

    @Mock
    private CanjeRepository canjeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCanjearPuntos() {
        // Preparar
        UsuarioRecompensa usuarioRecompensa = new UsuarioRecompensa();
        usuarioRecompensa.setUsuarioId(1L);
        usuarioRecompensa.setRecompensaId(1L);
        usuarioRecompensa.setValor(100);

        // Simular el comportamiento del repositorio
        when(canjeRepository.save(any(UsuarioRecompensa.class))).thenAnswer(invocation -> {
            UsuarioRecompensa saved = invocation.getArgument(0);
            saved.setDateCreated(LocalDateTime.now()); // Simula la persistencia
            return saved;
        });

        // Actuar
        UsuarioRecompensa result = canjeService.canjearPuntos(usuarioRecompensa);

        // Afirmar
        assertNotNull(result.getDateCreated());
        assertEquals(usuarioRecompensa.getUsuarioId(), result.getUsuarioId());
        assertEquals(usuarioRecompensa.getRecompensaId(), result.getRecompensaId());
        assertEquals(usuarioRecompensa.getValor(), result.getValor());
        verify(canjeRepository, times(1)).save(usuarioRecompensa);
    }
}
