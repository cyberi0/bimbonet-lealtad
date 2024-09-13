package com.bimbonet.bimbonet_lealtad.Controllers;

import com.bimbonet.bimbonet_lealtad.Entities.Usuario;
import com.bimbonet.bimbonet_lealtad.Repository.UsuarioRepository;
import com.bimbonet.bimbonet_lealtad.Services.UsuarioService;
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

class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsuario() {
        // Preparar
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombreCompleto("Usuario Test");

        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));

        // Actuar
        Usuario result = usuarioController.getUsuario(1L);

        // Afirmar
        assertNotNull(result);
        assertEquals("Usuario Test", result.getNombreCompleto());
    }

    @Test
    void testGetUsuario_NotFound() {
        // Preparar
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Actuar
        Usuario result = usuarioController.getUsuario(1L);

        // Afirmar
        assertNull(result);
    }

    @Test
    void testGetAllUsuarios() {
        // Preparar
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombreCompleto("Usuario Test");
        List<Usuario> usuarios = Collections.singletonList(usuario);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        // Actuar
        List<Usuario> result = usuarioController.getAllUsuarios();

        // Afirmar
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Usuario Test", result.get(0).getNombreCompleto());
    }

    @Test
    void testRegistrarUsuario() {
        // Preparar
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto("Nuevo Usuario");

        when(usuarioService.registrarUsuario(any(Usuario.class))).thenReturn(usuario);

        // Actuar
        ResponseEntity<Usuario> response = usuarioController.registrarUsuario(usuario);

        // Afirmar
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Nuevo Usuario", response.getBody().getNombreCompleto());
    }
}
