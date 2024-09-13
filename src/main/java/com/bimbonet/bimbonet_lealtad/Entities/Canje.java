package com.bimbonet.bimbonet_lealtad.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "puntos")
public class Canje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recompensa_id")
    private Recompensa recompensaId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "valor")
    private Integer valor;

    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateCreated;

    // Constructor vacío
    public Canje() {}

    public Canje(Long id) {
        this.id = id;
    }

    public Canje(Recompensa recompensaId, Long usuarioId, Integer valor) {
        this.recompensaId = recompensaId;
        this.usuarioId = usuarioId;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
