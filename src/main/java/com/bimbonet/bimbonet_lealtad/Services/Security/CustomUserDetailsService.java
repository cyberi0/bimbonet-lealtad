package com.bimbonet.bimbonet_lealtad.Services.Security;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.stream.Collectors;

import com.bimbonet.bimbonet_lealtad.Entities.Rol;
import com.bimbonet.bimbonet_lealtad.Entities.Usuario;
import com.bimbonet.bimbonet_lealtad.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                mapRolesToAuthorities(usuario.getRoles()) // Asegúrate de que esto devuelva Collection<GrantedAuthority>
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Rol> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
