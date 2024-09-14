package com.bimbonet.bimbonet_lealtad.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios_recompensas")
public class UsuarioRecompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recompensa_id", nullable = false)
    private Long recompensaId;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "valor", nullable = false)
    private Integer valor;

    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateCreated;

    public UsuarioRecompensa() {}

    public UsuarioRecompensa(Long id) {
        this.id = id;
    }

    public UsuarioRecompensa(Long usuarioId, Long recompensaId) {
        this.recompensaId = recompensaId;
        this.usuarioId = usuarioId;
    }

    public UsuarioRecompensa(Long recompensaId, Long usuarioId, Integer valor, LocalDateTime dateCreated) {
        this.recompensaId = recompensaId;
        this.usuarioId = usuarioId;
        this.valor = valor;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecompensaId() {
        return recompensaId;
    }

    public void setRecompensaId(Long recompensaId) {
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
