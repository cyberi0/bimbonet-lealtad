package com.bimbonet.bimbonet_lealtad.Entities.DTOs;

public class PuntoDTO {
    private Long recompensaId;
    private String recompensaNombre;
    private Long cantidadPuntos;

    public PuntoDTO(Long recompensaId, String recompensaNombre, Long cantidad) {
        this.recompensaId = recompensaId;
        this.recompensaNombre = recompensaNombre;
        this.cantidadPuntos = cantidad;
    }

    public Long getRecompensaId() {
        return recompensaId;
    }

    public void setRecompensaId(Long recompensaId) {
        this.recompensaId = recompensaId;
    }

    public String getRecompensaNombre() {
        return recompensaNombre;
    }

    public void setRecompensaNombre(String recompensaNombre) {
        this.recompensaNombre = recompensaNombre;
    }

    public Long getCantidadPuntos() {
        return cantidadPuntos;
    }

    public void setCantidadPuntos(Long cantidadPuntos) {
        this.cantidadPuntos = cantidadPuntos;
    }
}
