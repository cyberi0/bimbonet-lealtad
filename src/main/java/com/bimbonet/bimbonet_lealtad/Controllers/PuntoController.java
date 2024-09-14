package com.bimbonet.bimbonet_lealtad.Controllers;

import com.bimbonet.bimbonet_lealtad.Entities.DTOs.PuntoDTO;
import com.bimbonet.bimbonet_lealtad.Entities.Proyections.PuntoProjection;
import com.bimbonet.bimbonet_lealtad.Entities.Punto;
import com.bimbonet.bimbonet_lealtad.Entities.Recompensa;
import com.bimbonet.bimbonet_lealtad.Entities.Usuario;
import com.bimbonet.bimbonet_lealtad.Repository.PuntoRepository;
import com.bimbonet.bimbonet_lealtad.Repository.RecompensaRepository;
import com.bimbonet.bimbonet_lealtad.Services.PuntoService;
import com.bimbonet.bimbonet_lealtad.Services.RecompensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puntos")
public class PuntoController {
    @Autowired
    private PuntoRepository usuarioRepository;

    @Autowired
    private PuntoService puntoService;

    @Autowired
    private RecompensaRepository recompensaRepository;

    @Autowired
    private PuntoRepository puntoRepository;

    @GetMapping("/{id}")
    public Punto getPunto(@PathVariable Long id) {
        Punto punto = puntoRepository.findById(id).orElse(null);
        return punto;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Punto> registrarPunto(@RequestBody Punto punto) {
        Recompensa recompensa = recompensaRepository.getById(punto.getRecompensaId().getId());
        punto.setCantidad(recompensa.getPuntos());
        Punto usuarioRegistrado = puntoService.registrarPunto(punto);
        return ResponseEntity.ok(usuarioRegistrado);
    }

    @GetMapping("/saldo/{usuarioId}")
    public List<Object[]> contarPuntos(@PathVariable Long usuarioId) {
        return puntoService.contarPuntosPorRecompensa(usuarioId);
    }

    @GetMapping("/saldo-recompensa/{usuarioId}/{recompensaId}")
    public List<Object[]> contarPuntos(@PathVariable Long usuarioId, @PathVariable Long recompensaId) {
        return puntoService.contarPuntosPorRecompensaId(usuarioId, recompensaId);
    }
}