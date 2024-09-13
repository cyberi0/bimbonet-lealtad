package com.bimbonet.bimbonet_lealtad.Repository;

import com.bimbonet.bimbonet_lealtad.Entities.UsuarioRecompensa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CanjeRepository extends JpaRepository<UsuarioRecompensa, Long> {
    Optional<UsuarioRecompensa> findById(Long id);
    List<UsuarioRecompensa> findAll();
}
