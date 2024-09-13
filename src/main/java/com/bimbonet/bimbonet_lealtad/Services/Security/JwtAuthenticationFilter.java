package com.bimbonet.bimbonet_lealtad.Services.Security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, CustomUserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            // Verificar si el token JWT es válido
            if (jwt != null && jwtProvider.validateJwtToken(jwt)) {
                String email = jwtProvider.getUserEmailFromJwtToken(jwt);

                // Cargar el usuario asociado al token JWT
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                // Verificar si no existe una autenticación previa en el contexto de seguridad
                if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

                    // Establecer detalles adicionales, como IP y detalles del request
                    ((UsernamePasswordAuthenticationToken) authentication)
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Establecer el contexto de seguridad con el usuario autenticado
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            // Puedes manejar excepciones relacionadas con la autenticación aquí si es necesario
            System.out.println("No se pudo establecer la autenticación del usuario: " + e.getMessage());
        }

        // Continuar con el siguiente filtro en la cadena
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        // Verificar que el token comience con 'Bearer ' y no sea nulo
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remover "Bearer " del token
        }
        return null;
    }
}
