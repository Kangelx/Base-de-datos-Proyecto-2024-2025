package com.api.reto.models;
import com.api.reto.enums.EstadoEnum;
import com.api.reto.enums.PrioridadEnum;
import com.api.reto.enums.TipoEnum;
import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

@Entity
@Table(name = "incidencias", schema = "tic_incidencias", catalog = "")
public class IncidenciasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num", nullable = false)
    private int num;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoEnum  tipo;
    @ManyToOne
    @JoinColumn(name = "subtipo_id", foreignKey = @ForeignKey(name = "FK_INCIDENCIA_INCIDENCIASUBTIPO"))
    private IncidenciasSubtiposEntity subtipoId;
    @Basic
    @Column(name = "fecha_creacion", nullable = false)
    private Timestamp fechaCreacion;
    @Basic
    @Column(name = "fecha_cierre", nullable = true)
    private Timestamp fechaCierre;
    @Basic
    @Column(name = "descripcion", nullable = false, length = -1)
    private String descripcion;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoEnum estado;
    @Basic
    @Column(name = "adjunto_url", nullable = true, length = -1)
    private String adjuntoUrl;
    @Basic
    @Column(name = "tiempo",nullable = true)
    private Time tiempo;
    @ManyToOne
    @JoinColumn(name = "creador_id", foreignKey = @ForeignKey(name = "FK_PERSONALCREADOR_INCIDENCIA"))
    private PersonalEntity creadorId;
    @ManyToOne
    @JoinColumn(name = "responsable_id", foreignKey = @ForeignKey(name = "FK_PERSONALRESPONSABLE_INCIDENCIA"))
    private PersonalEntity responsableId;
    @ManyToOne
    @JoinColumn(name = "equipo_id", foreignKey = @ForeignKey(name = "FK_EQUIPO_INCIDENCIA"))
    private EquiposEntity equipoId;
    @Enumerated(EnumType.STRING)
    @Column(name = "prioridad", nullable = false)
    private PrioridadEnum prioridad;

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

    public IncidenciasSubtiposEntity getSubtipoId() {
        return subtipoId;
    }

    public void setSubtipoId(IncidenciasSubtiposEntity subtipoId) {
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

    public PersonalEntity getCreadorId() {
        return creadorId;
    }

    public void setCreadorId(PersonalEntity creadorId) {
        this.creadorId = creadorId;
    }

    public PersonalEntity getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(PersonalEntity responsableId) {
        this.responsableId = responsableId;
    }

    public EquiposEntity getEquipoId() {
        return equipoId;
    }

    public void setEquipoId(EquiposEntity equipoId) {
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

    @Override
    public String toString() {
        return "IncidenciasEntity{" +
                "num=" + num +
                ", tipo=" + tipo +
                ", subtipoId=" + subtipoId +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaCierre=" + fechaCierre +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", adjuntoUrl='" + adjuntoUrl + '\'' +
                ", tiempo=" + tiempo +
                ", creadorId=" + creadorId +
                ", responsableId=" + responsableId +
                ", equipoId=" + equipoId +
                ", prioridad=" + prioridad +
                '}';
    }
}
