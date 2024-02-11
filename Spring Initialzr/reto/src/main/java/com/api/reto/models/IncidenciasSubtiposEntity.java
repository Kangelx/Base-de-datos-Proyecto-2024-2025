package com.api.reto.models;

import com.api.reto.enums.TipoEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "incidencias_subtipos", schema = "tic_incidencias", catalog = "")
public class IncidenciasSubtiposEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoEnum tipo;
    @Basic
    @Column(name = "subtipo_nombre", nullable = false, length = 20)
    private String subtipoNombre;

    @Column(name = "sub_subtipo", nullable = true, length = 40)
    private String subSubtipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
