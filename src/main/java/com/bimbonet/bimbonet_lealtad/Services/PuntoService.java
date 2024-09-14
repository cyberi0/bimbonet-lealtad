package com.bimbonet.bimbonet_lealtad.Services;

import com.bimbonet.bimbonet_lealtad.Entities.DTOs.PuntoDTO;
import com.bimbonet.bimbonet_lealtad.Entities.Proyections.PuntoProjection;
import com.bimbonet.bimbonet_lealtad.Entities.Punto;
import com.bimbonet.bimbonet_lealtad.Repository.PuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PuntoService {

    @Autowired
    private PuntoRepository puntoRepository;

    public Punto registrarPunto(Punto punto) {
        punto.setDateCreated(LocalDateTime.now());
        return puntoRepository.save(punto);
    }

    public List<Object[]> contarPuntosPorRecompensa(Long usuarioId) {
        List<Object[]> totalPuntos = puntoRepository.sumarPuntosPorRecompensa(usuarioId);
        return totalPuntos;
    }

    public List<Object[]> contarPuntosPorRecompensaId(Long usuarioId, Long recompensaId) {
        List<Object[]> totalPuntos = puntoRepository.sumarPuntosPorRecompensaId(usuarioId, recompensaId);
        return totalPuntos;
    }
}