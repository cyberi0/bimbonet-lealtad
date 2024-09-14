package com.bimbonet.bimbonet_lealtad.Services;

import com.bimbonet.bimbonet_lealtad.Entities.Accion;
import com.bimbonet.bimbonet_lealtad.Repository.AccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccionRecompensaService {

    @Autowired
    private AccionRepository recompensaRepository;

    public Accion registrarAccion(Accion recompensa) {
        recompensa.setDateCreated(LocalDateTime.now());
        return recompensaRepository.save(recompensa);
    }

    public Accion canjearAccion(Accion recompensa) {
        recompensa.setDateCreated(LocalDateTime.now());
        return recompensaRepository.save(recompensa);
    }


}