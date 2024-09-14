package com.bimbonet.bimbonet_lealtad.Repository;

import com.bimbonet.bimbonet_lealtad.Entities.Punto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PuntoRepository extends JpaRepository<Punto, Long> {
    Optional<Punto> findById(Long id);

    List<Punto> findAll();

    @Query("SELECT p.recompensaId.id, p.recompensaId.nombre, SUM(p.cantidad) FROM Punto p WHERE p.usuarioId = :usuarioId AND p.activo=TRUE GROUP BY p.recompensaId.id, p.recompensaId.nombre")
    List<Object[]> sumarPuntosPorRecompensa(@Param("usuarioId") Long usuarioId);

    @Query("SELECT p.recompensaId.id, p.recompensaId.nombre, SUM(p.cantidad) FROM Punto p WHERE p.usuarioId = :usuarioId AND p.recompensaId.id = :recompensaId AND p.activo=TRUE GROUP BY p.recompensaId.id, p.recompensaId.nombre")
    List<Object[]> sumarPuntosPorRecompensaId(@Param("usuarioId") Long usuarioId, @Param("recompensaId") Long recompensaId);

    @Modifying
    @Transactional
    @Query("UPDATE Punto p SET p.activo = FALSE WHERE p.recompensaId.id = :recompensaId AND p.usuarioId = :usuarioId")
    void updateCampoByRecompensaId(@Param("recompensaId") Long recompensaId, @Param("usuarioId") Long usuarioId);



}
