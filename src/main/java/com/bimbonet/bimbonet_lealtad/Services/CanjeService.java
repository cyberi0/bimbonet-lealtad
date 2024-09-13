package com.bimbonet.bimbonet_lealtad.Services;

import com.bimbonet.bimbonet_lealtad.Entities.Canje;
import com.bimbonet.bimbonet_lealtad.Entities.UsuarioRecompensa;
import com.bimbonet.bimbonet_lealtad.Repository.CanjeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CanjeService {

    @Autowired
    private CanjeRepository canjeRepository;

    public UsuarioRecompensa canjearPuntos(UsuarioRecompensa usuarioRecompensa) {
        usuarioRecompensa.setDateCreated(LocalDateTime.now());
        return canjeRepository.save(usuarioRecompensa);
    }

}