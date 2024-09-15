package com.bimbonet.bimbonet_lealtad.Controllers;

import com.bimbonet.bimbonet_lealtad.Entities.Accion;
import com.bimbonet.bimbonet_lealtad.Entities.AccionRecompensa;
import com.bimbonet.bimbonet_lealtad.Repository.AccionRecompensaRepository;
import com.bimbonet.bimbonet_lealtad.Repository.AccionRepository;
import com.bimbonet.bimbonet_lealtad.Services.AccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/acciones")
public class AccionController {

    @Autowired
    private AccionRepository accionRepository;

    @Autowired
    private AccionRecompensaRepository accionRecompensaRepository;

    @Autowired
    private AccionService accionService;

    @GetMapping("/{id}")
    public ResponseEntity<Accion>  getAccion(@PathVariable Long id) {
        Accion recompensa = accionRepository.findById(id).orElse(null);
        return ResponseEntity.ok(recompensa);
    }

    @GetMapping("/all")
    public List<Accion> getAll() {
        List<Accion> acciones = accionRepository.findAll();

        for (Accion accion : acciones) {
            List<AccionRecompensa> accionRecompensas = accionRecompensaRepository.findByAccionId(accion.getId());
            accion.setAccionRecompensaList(accionRecompensas);
        }

        return acciones;
    }

    @GetMapping("/tiene-recompensa/{tieneRecompensa}")
    public List<Accion> findByTieneRecompensa(@PathVariable Boolean tieneRecompensa) {
        List<Accion> accionTieneRecompensList = accionRepository.findByTieneRecompensa(tieneRecompensa);
        for (Accion accion : accionTieneRecompensList) {
            List<AccionRecompensa> accionRecompensas = accionRecompensaRepository.findByAccionId(accion.getId());
            accion.setAccionRecompensaList(accionRecompensas);
        }

        return accionTieneRecompensList;
    }


    @PostMapping("/registrar")
    public ResponseEntity<Accion> registrarAccion(@RequestBody Accion accion) {
        accion.setDateCreated(LocalDateTime.now());
        Accion accionRegistrada = accionService.registrarAccion(accion);

        List<AccionRecompensa> accionRecompensas = accion.getAccionRecompensaList();
        for (AccionRecompensa accionRecompensa : accionRecompensas) {
            accionRecompensa.setDateCreated(LocalDateTime.now());
            accionRecompensa.setAccionId(accionRegistrada.getId());
            accionRecompensaRepository.save(accionRecompensa);
        }

        return ResponseEntity.ok(accionRegistrada);
    }

}