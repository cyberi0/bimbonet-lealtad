package com.bimbonet.bimbonet_lealtad.Services;

import com.bimbonet.bimbonet_lealtad.Entities.Usuario;
import com.bimbonet.bimbonet_lealtad.Repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarUsuario() {
        // Preparar
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto("Juan Pérez");
        usuario.setEmail("juan@example.com");
        usuario.setPassword("plainPassword");

        // Simular el comportamiento del encriptador de contraseñas
        when(passwordEncoder.encode(any(String.class))).thenReturn("encryptedPassword");

        // Simular el comportamiento del repositorio
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario saved = invocation.getArgument(0);
            saved.setDateCreated(LocalDateTime.now()); // Simula la persistencia
            return saved;
        });

        // Actuar
        Usuario result = usuarioService.registrarUsuario(usuario);

        // Afirmar
        assertNotNull(result.getDateCreated());
        assertEquals(usuario.getNombreCompleto(), result.getNombreCompleto());
        assertEquals(usuario.getEmail(), result.getEmail());
        assertNotEquals("plainPassword", result.getPassword()); // Verifica que la contraseña ha sido encriptada
        assertEquals("encryptedPassword", result.getPassword());
        verify(usuarioRepository, times(1)).save(usuario);
        verify(passwordEncoder, times(1)).encode("plainPassword");
    }
}
