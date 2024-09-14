package com.bimbonet.bimbonet_lealtad.Repository;

import com.bimbonet.bimbonet_lealtad.Entities.AccionRecompensa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccionRecompensaRepository extends JpaRepository<AccionRecompensa, Long> {
    Optional<AccionRecompensa> findById(Long id);
    List<AccionRecompensa> findAll();
    List<AccionRecompensa> findByAccionId(Long accionId);
}