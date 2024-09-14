package com.bimbonet.bimbonet_lealtad.Controllers;

import com.bimbonet.bimbonet_lealtad.Entities.DTOs.PuntoDTO;
import com.bimbonet.bimbonet_lealtad.Entities.Proyections.PuntoProjection;
import com.bimbonet.bimbonet_lealtad.Entities.Punto;
import com.bimbonet.bimbonet_lealtad.Entities.Recompensa;
import com.bimbonet.bimbonet_lealtad.Entities.Usuario;
import com.bimbonet.bimbonet_lealtad.Projections.PuntoProjectionImpl;
import com.bimbonet.bimbonet_lealtad.Repository.PuntoRepository;
import com.bimbonet.bimbonet_lealtad.Repository.RecompensaRepository;
import com.bimbonet.bimbonet_lealtad.Services.PuntoService;
import com.bimbonet.bimbonet_lealtad.Services.RecompensaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PuntoControllerTest {

    @InjectMocks
    private PuntoController puntoController;

    @Mock
    private PuntoRepository puntoRepository;

    @Mock
    private PuntoService puntoService;

    @Mock
    private RecompensaService recompensaService;

    @Mock
    private RecompensaRepository recompensaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testGetPunto() {
        // Preparar
        Recompensa recompensa = new Recompensa();
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Punto punto = new Punto();
        punto.setCantidad(100);

        // Configurar mock
        when(puntoRepository.findById(1L)).thenReturn(Optional.of(punto));

        // Actuar
        Punto result = puntoController.getPunto(1L);

        // Afirmar
        assertNotNull(result);
        assertEquals(100, result.getCantidad());
    }

    @Test
    void testRegistrarPunto() {
        // Preparar
        Recompensa recompensa = new Recompensa();
        recompensa.setId(1L);
        recompensa.setPuntos(50);

        Punto punto = new Punto();
        punto.setRecompensaId(recompensa);
        punto.setCantidad(0);

        when(recompensaRepository.getById(anyLong())).thenReturn(recompensa);
        when(puntoService.registrarPunto(any(Punto.class))).thenReturn(punto);

        // Actuar
        ResponseEntity<Punto> response = puntoController.registrarPunto(punto);

        // Afirmar
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(50, response.getBody().getCantidad());
    }

    @Test
    void testContarPuntosPorRecompensa() {
        // Preparar
        List<Object[]> puntos = Collections.singletonList(new Object[]{1L, 100L});
        when(puntoService.contarPuntosPorRecompensa(anyLong())).thenReturn(puntos);

        // Actuar
        List<Object[]> result = puntoController.contarPuntos(1L);

        // Afirmar
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(100L, result.get(0)[1]);
    }

    @Test
    void testContarPuntosPorRecompensaId() {
        // Preparar
        List<Object[]> puntos = Collections.singletonList(new Object[]{1L, 50L});
        when(puntoService.contarPuntosPorRecompensaId(anyLong(), anyLong())).thenReturn(puntos);

        // Actuar
        List<Object[]> result = puntoController.contarPuntos(1L, 1L);

        // Afirmar
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(50L, result.get(0)[1]);
    }


}



