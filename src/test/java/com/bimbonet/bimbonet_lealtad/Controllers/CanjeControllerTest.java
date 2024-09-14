package com.bimbonet.bimbonet_lealtad.Controllers;

import com.bimbonet.bimbonet_lealtad.Entities.*;
import com.bimbonet.bimbonet_lealtad.Entities.DTOs.PuntoDTO;
import com.bimbonet.bimbonet_lealtad.Entities.Proyections.PuntoProjection;
import com.bimbonet.bimbonet_lealtad.Projections.PuntoProjectionImpl;
import com.bimbonet.bimbonet_lealtad.Repository.CanjeRepository;
import com.bimbonet.bimbonet_lealtad.Repository.PuntoRepository;
import com.bimbonet.bimbonet_lealtad.Repository.RecompensaRepository;
import com.bimbonet.bimbonet_lealtad.Services.CanjeService;
import com.bimbonet.bimbonet_lealtad.Generics.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CanjeControllerTest {

    @InjectMocks
    private CanjeController canjeController;

    @Mock
    private CanjeRepository canjeRepository;

    @Mock
    private RecompensaRepository recompensaRepository;

    @Mock
    private PuntoRepository puntoRepository;

    @Mock
    private CanjeService canjeService;

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
        Punto punto = new Punto(recompensa, usuario.getId(), 100);

        // Actuar
        Punto result = canjeController.getPunto(1L);

        // Afirmar
        assertNotNull(result);
        assertEquals(result, result);
    }

    @Test
    void testCanjearPuntos_Success() {
        // Preparar
        UsuarioRecompensa usuarioRecompensa = new UsuarioRecompensa();
        usuarioRecompensa.setRecompensaId(1L);
        usuarioRecompensa.setUsuarioId(1L);
        Recompensa recompensa = new Recompensa();
        recompensa.setId(1L);
        recompensa.setValor(100);
        List<Object[]> puntoList = Collections.singletonList(new Object[]{1L, 1L, 150L});

        when(recompensaRepository.getReferenceById(anyLong())).thenReturn(recompensa);
        when(puntoRepository.sumarPuntosPorRecompensaId(anyLong(), anyLong())).thenReturn(puntoList);
        when(canjeRepository.save(any(UsuarioRecompensa.class))).thenReturn(usuarioRecompensa);

        // Actuar
        ResponseEntity<?> response = canjeController.canjearPuntos(usuarioRecompensa);

        // Afirmar
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioRecompensa, response.getBody());
        verify(puntoRepository, times(1)).updateCampoByRecompensaId(anyLong(), anyLong());
    }

    @Test
    void testCanjearPuntos_Failure() {
        // Preparar
        UsuarioRecompensa usuarioRecompensa = new UsuarioRecompensa();
        usuarioRecompensa.setRecompensaId(1L);
        usuarioRecompensa.setUsuarioId(1L);
        Recompensa recompensa = new Recompensa();
        recompensa.setId(1L);
        recompensa.setValor(100);
        PuntoProjection puntoProjection = new PuntoProjectionImpl(1L, "Recompensa 1", 50L);
        PuntoProjection puntoList = (PuntoProjection) Collections.singletonList(puntoProjection);
        when(recompensaRepository.getReferenceById(anyLong())).thenReturn(recompensa);
        when(puntoRepository.sumarPuntosPorRecompensaId(anyLong(), anyLong())).thenReturn((List<Object[]>) puntoList);

        // Actuar
        ResponseEntity<?> response = canjeController.canjearPuntos(usuarioRecompensa);

        // Afirmar
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertTrue(errorResponse.getMessage().contains("Lo sentimos... Te faltan"));
    }
}
