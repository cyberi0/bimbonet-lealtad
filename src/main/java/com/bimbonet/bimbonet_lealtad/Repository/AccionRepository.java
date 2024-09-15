package com.bimbonet.bimbonet_lealtad.Repository;

import com.bimbonet.bimbonet_lealtad.Entities.Accion;
import com.bimbonet.bimbonet_lealtad.Entities.AccionRecompensa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccionRepository extends JpaRepository<Accion, Long> {
    Optional<Accion> findById(Long id);
    List<Accion> findAll();
    List<Accion> findByTieneRecompensa(Boolean tieneRecompensa);

}
