package com.bimbonet.bimbonet_lealtad.Controllers;

import com.bimbonet.bimbonet_lealtad.Entities.*;
import com.bimbonet.bimbonet_lealtad.Entities.DTOs.PuntoDTO;
import com.bimbonet.bimbonet_lealtad.Repository.CanjeRepository;
import com.bimbonet.bimbonet_lealtad.Repository.PuntoRepository;
import com.bimbonet.bimbonet_lealtad.Repository.RecompensaRepository;
import com.bimbonet.bimbonet_lealtad.Services.CanjeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.bimbonet.bimbonet_lealtad.Generics.ErrorResponse;

import java.time.LocalDateTime;
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

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger log = LoggerFactory.getLogger(CanjeController.class);


    @GetMapping("/{id}")
    public Punto getPunto(@PathVariable Long id) {
        Recompensa recompensa = new Recompensa();
        Usuario usuario = new Usuario();
        return new Punto(recompensa, usuario.getId(), 100);
    }

    @PostMapping("/puntos")
    public ResponseEntity<?> canjearPuntos(@RequestBody UsuarioRecompensa usuarioRecompensa) {
        Recompensa recompensa = recompensaRepository.getReferenceById(usuarioRecompensa.getRecompensaId());
        Integer valorRecompensa = recompensa.getValor();
        List<Object[]> puntoList = puntoRepository.sumarPuntosPorRecompensaId(usuarioRecompensa.getUsuarioId(), recompensa.getId());

        if (puntoList.isEmpty()) {
            String mensaje = "No se encontraron puntos acumulados para la recompensa.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(mensaje));
        }

        Object[] fila = puntoList.get(0);
        Long valorAcumuladoRecompensa;

        try {
            valorAcumuladoRecompensa = ((Number) fila[2]).longValue();
        } catch (ClassCastException e) {
            String mensaje = "Error al procesar los puntos acumulados.";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(mensaje));
        }

        log.info("valorRecompensa - " + valorRecompensa);
        log.info("valorAcumuladoRecompensa - " + valorAcumuladoRecompensa);
        ResponseEntity<?> responseEntity = null;
        if (valorRecompensa > valorAcumuladoRecompensa) {
            long puntosFaltantes = valorRecompensa - valorAcumuladoRecompensa;
            String mensaje = "Lo sentimos... Te faltan " + puntosFaltantes + " puntos para canjear tu recompensa '" + recompensa.getNombre() + "'.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(mensaje));
        } else {
            try {
                usuarioRecompensa.setValor(valorRecompensa);
                usuarioRecompensa.setDateCreated(LocalDateTime.now());
                responseEntity = ResponseEntity.ok(canjeRepository.save(usuarioRecompensa));
                puntoRepository.updateCampoByRecompensaId(recompensa.getId(), usuarioRecompensa.getUsuarioId());

                long diff = Math.abs(valorRecompensa - valorAcumuladoRecompensa);
                if (!(diff == 0)) {
                    Punto punto = new Punto(recompensa, usuarioRecompensa.getUsuarioId(), (int) diff);
                    punto.setDateCreated(LocalDateTime.now());
                    punto.setActivo(true);
                    puntoRepository.save(punto);
                }

                return responseEntity;
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse(responseEntity.toString()));
    }


}