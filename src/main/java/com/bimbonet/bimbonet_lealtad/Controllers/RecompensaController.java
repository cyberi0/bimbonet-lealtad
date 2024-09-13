package com.bimbonet.bimbonet_lealtad.Controllers;

import com.bimbonet.bimbonet_lealtad.Entities.Recompensa;
import com.bimbonet.bimbonet_lealtad.Repository.RecompensaRepository;
import com.bimbonet.bimbonet_lealtad.Services.RecompensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recompensas")
public class RecompensaController {
    @Autowired
    private RecompensaRepository recompensaRepository;

    @Autowired
    private RecompensaService recompensaService;

    @GetMapping("/{id}")
    public ResponseEntity<Recompensa> getRecompensa(@PathVariable Long id) {
        Recompensa recompensa = recompensaRepository.getById(id);
        return ResponseEntity.ok(recompensa);
    }

    @PostMapping("/registrar")
    public ResponseEntity<Recompensa> registrarPunto(@RequestBody Recompensa recompensa) {
        Recompensa recompensaRegistrada = recompensaService.registrarRecompensa(recompensa);
        return ResponseEntity.ok(recompensaRegistrada);
    }

}