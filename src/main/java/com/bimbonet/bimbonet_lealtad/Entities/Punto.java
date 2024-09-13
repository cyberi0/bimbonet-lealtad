package com.bimbonet.bimbonet_lealtad.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "puntos")
public class Punto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recompensa_id")
    private Recompensa recompensaId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateCreated;

    // Constructor vacío
    public Punto() {}

    public Punto(Long id) {
        this.id = id;
    }

    public Punto(Recompensa recompensaId, Long usuarioId, Integer cantidad) {
        this.recompensaId = recompensaId;
        this.usuarioId = usuarioId;
        this.cantidad = cantidad;
    }

    public Recompensa getRecompensaId() {
        return recompensaId;
    }

    public void setRecompensaId(Recompensa recompensaId) {
        this.recompensaId = recompensaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
