package com.bimbonet.bimbonet_lealtad.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "accion_recompensa")
public class AccionRecompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accion_id", nullable = false)
    private Long accionId;

    @ManyToOne
    @JoinColumn(name = "recompensa_id")
    private Recompensa recompensaId;

    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateCreated;

    public AccionRecompensa() {}

    public AccionRecompensa(Long id) {
        this.id = id;
    }

    public AccionRecompensa(Long accionId, Recompensa recompensaId, LocalDateTime dateCreated) {
        this.accionId = accionId;
        this.recompensaId = recompensaId;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccionId() {
        return accionId;
    }

    public void setAccionId(Long accionId) {
        this.accionId = accionId;
    }

    public Recompensa getRecompensaId() {
        return recompensaId;
    }

    public void setRecompensaId(Recompensa recompensaId) {
        this.recompensaId = recompensaId;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
