package com.api.reto.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "incidencias", schema = "tic_incidencias", catalog = "")
public class IncidenciasEntity {
    public enum TipoEnum {
        EQUIPOS,
        CUENTAS,
        WIFI,
        INTERNET,
        SOFTWARE
    }
    public enum EstadoEnum {
        abierta,
        asignada,
        en_proceso,
        enviada_a_Infortec,
        resuelta,
        cerrada
    }
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "num", nullable = false)
    private int num;
    @Basic
    @Column(name = "tipo", nullable = false)
    private TipoEnum tipo;
    @ManyToOne
    @JoinColumn(name = "subtipo",foreignKey = @ForeignKey(name="FK_INCIDENCIA_INCIDENCIASUBTIPO"))
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
    @Basic
    @Column(name = "estado", nullable = false)
    private EstadoEnum estado;
    @Basic
    @Column(name = "adjunto_url", nullable = true, length = -1)
    private String adjuntoUrl;
    @ManyToOne
    @JoinColumn(name = "creador_id",foreignKey = @ForeignKey(name="FK_PERSONALCREADOR_INCIDENCIA"))
    private PersonalEntity creadorId;
    @ManyToOne
    @JoinColumn(name = "responsable_id",foreignKey = @ForeignKey(name="FK_PERSONALRESPONSABLE_INCIDENCIA"))
    private PersonalEntity responsableId;
    @ManyToOne
    @JoinColumn(name = "equipo_id",foreignKey = @ForeignKey(name="FK_EQUIPO_INCIDENCIA"))
    private EquiposEntity equipoId;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Object getTipo() {
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

    public Object getEstado() {
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
}
