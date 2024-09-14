package com.bimbonet.bimbonet_lealtad.Entities;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "acciones")
public class Accion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "tiene_recompensa", nullable = false)
    private Boolean tieneRecompensa;

    @Column(name = "date_created", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateCreated;

    @Transient
    private List<AccionRecompensa> accionRecompensaList;

    public Accion() {}

    public Accion(Long id) {
        this.id = id;
    }

    public Accion(String descripcion, Boolean tieneRecompensa, LocalDateTime dateCreated) {
        this.descripcion = descripcion;
        this.tieneRecompensa = tieneRecompensa;
        this.dateCreated = dateCreated;
    }

    public Accion(Long id, String descripcion, Boolean tieneRecompensa, LocalDateTime dateCreated) {
        this.id = id;
        this.descripcion = descripcion;
        this.tieneRecompensa = tieneRecompensa;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getTieneRecompensa() {
        return tieneRecompensa;
    }

    public void setTieneRecompensa(Boolean tieneRecompensa) {
        this.tieneRecompensa = tieneRecompensa;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<AccionRecompensa> getAccionRecompensaList() {
        return accionRecompensaList;
    }

    public void setAccionRecompensaList(List<AccionRecompensa> accionRecompensaList) {
        this.accionRecompensaList = accionRecompensaList;
    }
}
