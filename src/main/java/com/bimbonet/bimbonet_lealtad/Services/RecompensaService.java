package com.bimbonet.bimbonet_lealtad.Services;

import com.bimbonet.bimbonet_lealtad.Entities.Punto;
import com.bimbonet.bimbonet_lealtad.Entities.Recompensa;
import com.bimbonet.bimbonet_lealtad.Repository.PuntoRepository;
import com.bimbonet.bimbonet_lealtad.Repository.RecompensaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecompensaService {

    @Autowired
    private RecompensaRepository recompensaRepository;

    public Recompensa registrarRecompensa(Recompensa recompensa) {
        recompensa.setDateCreated(LocalDateTime.now());
        return recompensaRepository.save(recompensa);
    }

    public Recompensa canjearRecompensa(Recompensa recompensa) {
        recompensa.setDateCreated(LocalDateTime.now());
        return recompensaRepository.save(recompensa);
    }


}