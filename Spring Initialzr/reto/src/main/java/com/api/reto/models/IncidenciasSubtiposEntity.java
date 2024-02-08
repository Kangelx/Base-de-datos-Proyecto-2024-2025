package com.api.reto.models;

import jakarta.persistence.*;

@Entity
@Table(name = "incidencias_subtipos", schema = "tic_incidencias", catalog = "")
public class IncidenciasSubtiposEntity {
    public enum TipoEnum {
        EQUIPOS,
        CUENTAS,
        WIFI,
        INTERNET,
        SOFTWARE
    }
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "tipo", nullable = false)
    private TipoEnum tipo;
    @Basic
    @Column(name = "subtipo_nombre", nullable = false, length = 20)
    private String subtipoNombre;
    @Basic
    @Column(name = "sub_subtipo", nullable = true, length = 40)
    private String subSubtipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoEnum tipo) {
        this.tipo = tipo;
    }

    public String getSubtipoNombre() {
        return subtipoNombre;
    }

    public void setSubtipoNombre(String subtipoNombre) {
        this.subtipoNombre = subtipoNombre;
    }

    public String getSubSubtipo() {
        return subSubtipo;
    }

    public void setSubSubtipo(String subSubtipo) {
        this.subSubtipo = subSubtipo;
    }
}
