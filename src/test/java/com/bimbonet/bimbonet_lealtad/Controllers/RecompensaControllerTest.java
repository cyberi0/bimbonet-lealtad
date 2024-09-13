package com.bimbonet.bimbonet_lealtad.Controllers;

import com.bimbonet.bimbonet_lealtad.Entities.Recompensa;
import com.bimbonet.bimbonet_lealtad.Repository.RecompensaRepository;
import com.bimbonet.bimbonet_lealtad.Services.RecompensaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RecompensaControllerTest {

    @InjectMocks
    private RecompensaController recompensaController;

    @Mock
    private RecompensaRepository recompensaRepository;

    @Mock
    private RecompensaService recompensaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRecompensa() {
        // Preparar
        Recompensa recompensa = new Recompensa();
        recompensa.setId(1L);
        recompensa.setNombre("Recompensa Test");

        when(recompensaRepository.findById(anyLong())).thenReturn(Optional.of(recompensa));

        // Actuar
        ResponseEntity<Recompensa> response = recompensaController.getRecompensa(1L);

        // Afirmar
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Recompensa Test", response.getBody().getNombre());
    }

    @Test
    void testRegistrarRecompensa() {
        // Preparar
        Recompensa recompensa = new Recompensa();
        recompensa.setNombre("Nueva Recompensa");

        when(recompensaService.registrarRecompensa(any(Recompensa.class))).thenReturn(recompensa);

        // Actuar
        ResponseEntity<Recompensa> response = recompensaController.registrarRecompensa(recompensa);

        // Afirmar
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Nueva Recompensa", response.getBody().getNombre());
    }
}
