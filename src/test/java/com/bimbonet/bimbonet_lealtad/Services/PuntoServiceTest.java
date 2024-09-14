package com.bimbonet.bimbonet_lealtad.Services;

import com.bimbonet.bimbonet_lealtad.Entities.DTOs.PuntoDTO;
import com.bimbonet.bimbonet_lealtad.Entities.Proyections.PuntoProjection;
import com.bimbonet.bimbonet_lealtad.Entities.Punto;
import com.bimbonet.bimbonet_lealtad.Repository.PuntoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PuntoServiceTest {

    @InjectMocks
    private PuntoService puntoService;

    @Mock
    private PuntoRepository puntoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarPunto() {
        // Preparar
        Punto punto = new Punto();
        punto.setRecompensaId(null);  // Ajustar según la implementación
        punto.setUsuarioId(1L);
        punto.setCantidad(100);

        // Simular el comportamiento del repositorio
        when(puntoRepository.save(any(Punto.class))).thenAnswer(invocation -> {
            Punto saved = invocation.getArgument(0);
            saved.setDateCreated(LocalDateTime.now()); // Simula la persistencia
            return saved;
        });

        // Actuar
        Punto result = puntoService.registrarPunto(punto);

        // Afirmar
        assertNotNull(result.getDateCreated());
        assertEquals(punto.getUsuarioId(), result.getUsuarioId());
        assertEquals(punto.getCantidad(), result.getCantidad());
        verify(puntoRepository, times(1)).save(punto);
    }

    @Test
    void testContarPuntosPorRecompensa() {
        // Preparar
        List<Object[]> puntos = Collections.singletonList(new Object[]{1L, 100});

        // Simular el comportamiento del repositorio
        when(puntoRepository.sumarPuntosPorRecompensa(anyLong())).thenReturn(puntos);

        // Actuar
        List<Object[]> result = puntoService.contarPuntosPorRecompensa(1L);

        // Afirmar
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(100, result.get(0)[1]); // Suponiendo que el valor está en la segunda posición
        verify(puntoRepository, times(1)).sumarPuntosPorRecompensa(1L);
    }

    @Test
    void testContarPuntosPorRecompensaId() {
        // Preparar
        List<Object[]> puntos = Collections.singletonList(new Object[]{1L, 50});

        // Simular el comportamiento del repositorio
        when(puntoRepository.sumarPuntosPorRecompensaId(anyLong(), anyLong())).thenReturn(puntos);

        // Actuar
        List<Object[]> result = puntoService.contarPuntosPorRecompensaId(1L, 2L);

        // Afirmar
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(50, result.get(0)[1]); // Suponiendo que el valor está en la segunda posición
        verify(puntoRepository, times(1)).sumarPuntosPorRecompensaId(1L, 2L);
    }
}

