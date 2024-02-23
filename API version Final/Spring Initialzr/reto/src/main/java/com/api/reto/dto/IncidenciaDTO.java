package com.api.reto.dto;

import com.api.reto.enums.EstadoEnum;
import com.api.reto.enums.PrioridadEnum;
import com.api.reto.enums.TipoEnum;
import com.api.reto.models.IncidenciasEntity;

import java.sql.Time;
import java.sql.Timestamp;

public class IncidenciaDTO {
    private int num;
    private TipoEnum tipo;
    private Integer subtipoId;
    private Timestamp fechaCreacion;
    private Timestamp fechaCierre;
    private String descripcion;
    private EstadoEnum estado;
    private String adjuntoUrl;
    private Integer creadorId;
    private Integer responsableId;
    private Integer equipoId;
    private PrioridadEnum prioridad;
    private Time tiempo;
    public IncidenciaDTO(IncidenciasEntity incidencia) {
        this.num = incidencia.getNum();
        this.tipo = incidencia.getTipo();
        this.fechaCreacion = incidencia.getFechaCreacion();
        this.fechaCierre = incidencia.getFechaCierre();
        this.descripcion = incidencia.getDescripcion();
        this.estado = incidencia.getEstado();
        this.adjuntoUrl = incidencia.getAdjuntoUrl();
        this.tiempo = incidencia.getTiempo();
        this.prioridad = incidencia.getPrioridad();
    }

    public IncidenciaDTO() {
    }

    public IncidenciaDTO(TipoEnum tipo, String descripcion, Timestamp fechaCreacion, Timestamp fechaCierre, EstadoEnum estado, String adjuntoUrl, PrioridadEnum prioridad) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaCierre = fechaCierre;
        this.estado = estado;
        this.adjuntoUrl = adjuntoUrl;
        this.prioridad = prioridad;
    }
    // Getters y setters
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public Integer getSubtipoId() {
        return subtipoId;
    }

    public void setSubtipoId(Integer subtipoId) {
        this.subtipoId = subtipoId;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Timestamp getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Timestamp fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    public String getAdjuntoUrl() {
        return adjuntoUrl;
    }

    public void setAdjuntoUrl(String adjuntoUrl) {
        this.adjuntoUrl = adjuntoUrl;
    }

    public Integer getCreadorId() {
        return creadorId;
    }

    public void setCreadorId(Integer creadorId) {
        this.creadorId = creadorId;
    }

    public Integer getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(Integer responsableId) {
        this.responsableId = responsableId;
    }

    public Integer getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(Integer equipoId) {
        this.equipoId = equipoId;
    }

    public PrioridadEnum getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(PrioridadEnum prioridad) {
        this.prioridad = prioridad;
    }

    public Time getTiempo() {
        return tiempo;
    }

    public void setTiempo(Time tiempo) {
        this.tiempo = tiempo;
    }
}
