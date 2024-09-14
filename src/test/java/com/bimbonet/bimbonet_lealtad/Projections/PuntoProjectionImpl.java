package com.bimbonet.bimbonet_lealtad.Projections;
import com.bimbonet.bimbonet_lealtad.Entities.Proyections.PuntoProjection;

public class PuntoProjectionImpl implements PuntoProjection {
    private Long recompensaId;
    private String nombre;
    private Long cantidad;

    public PuntoProjectionImpl(Long recompensaId, String nombre, Long cantidad) {
        this.recompensaId = recompensaId;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    @Override
    public Long getUsuarioId() {
        return 0L;
    }

    @Override
    public Long getRecompensaId() {
        return recompensaId;
    }

    @Override
    public Long getValorAcumulado() {
        return 0L;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getCantidad() {
        return cantidad;
    }
}
