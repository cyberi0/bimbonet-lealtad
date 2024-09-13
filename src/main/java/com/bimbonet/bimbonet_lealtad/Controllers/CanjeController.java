package com.bimbonet.bimbonet_lealtad.Controllers;

import com.bimbonet.bimbonet_lealtad.Entities.*;
import com.bimbonet.bimbonet_lealtad.Repository.CanjeRepository;
import com.bimbonet.bimbonet_lealtad.Repository.PuntoRepository;
import com.bimbonet.bimbonet_lealtad.Repository.RecompensaRepository;
import com.bimbonet.bimbonet_lealtad.Services.CanjeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.bimbonet.bimbonet_lealtad.Generics.ErrorResponse;
import java.util.List;

@RestController
@RequestMapping("/api/canje")
public class CanjeController {
    @Autowired
    private CanjeRepository canjeRepository;

    @Autowired
    private CanjeService canjeService;

    @Autowired
    private RecompensaRepository recompensaRepository;

    @Autowired
    private PuntoRepository puntoRepository;

    @GetMapping("/{id}")
    public Punto getPunto(@PathVariable Long id) {
        Recompensa recompensa = new Recompensa();
        Usuario usuario = new Usuario();
        return new Punto(recompensa, usuario.getId(), 100);
    }

    @PostMapping("/puntos")
    public  ResponseEntity<?> canjearPuntos(@RequestBody UsuarioRecompensa usuarioRecompensa) {
        Recompensa recompensa = recompensaRepository.getReferenceById(usuarioRecompensa.getRecompensaId());
        Integer valorRecompensa = recompensa.getValor();

        List<Object[]> puntoList = puntoRepository.sumarPuntosPorRecompensaId(usuarioRecompensa.getUsuarioId(), recompensa.getId());
        if (!puntoList.isEmpty()) {
            Object[] fila = puntoList.get(0);
            Long valorAcumuladoRecompensa = (Long) fila[2];
            if (valorRecompensa > valorAcumuladoRecompensa) {
                long puntosFaltantes = valorRecompensa - valorAcumuladoRecompensa;
                String mensaje = "Lo sentimos... Te faltan " + puntosFaltantes + " puntos para canjear tu recompensa '"+recompensa.getNombre()+"'.";
                ErrorResponse errorResponse = new ErrorResponse(mensaje);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            } else {
                puntoRepository.deleteByRecompensaId(usuarioRecompensa.getRecompensaId());
                usuarioRecompensa.setValor(valorRecompensa);
                return ResponseEntity.ok(canjeRepository.save(usuarioRecompensa));
            }
        }
        return ResponseEntity.badRequest().build();
    }

}