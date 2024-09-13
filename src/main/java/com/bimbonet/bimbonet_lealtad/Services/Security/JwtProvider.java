package com.bimbonet.bimbonet_lealtad.Services.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtProvider {

    private SecretKey jwtSecret;
    private int jwtExpirationMs = 86400000; // 24 horas

    private final UserDetailsService userDetailsService;

    public JwtProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    public void init() {
        // Genera una clave segura solo al iniciar la aplicación
        jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(jwtSecret, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
            return true;
        } catch (io.jsonwebtoken.security.SignatureException e) {
            // Firma no coincide
            System.out.println("Invalid JWT signature");
        } catch (Exception e) {
            // Otros errores
            System.out.println("Invalid JWT token");
        }
        return false;
    }

    public String getUserEmailFromJwtToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Authentication getAuthentication(String email) {
        UserDetails userDetails = loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private UserDetails loadUserByUsername(String username) {
        // Implementar la lógica para cargar el usuario desde la base de datos
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return userDetails;
    }
}
