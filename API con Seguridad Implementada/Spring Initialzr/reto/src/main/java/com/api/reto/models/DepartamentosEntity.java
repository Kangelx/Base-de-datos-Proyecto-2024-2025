package com.api.reto.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "departamentos", schema = "tic_incidencias", catalog = "")
public class DepartamentosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "cod", nullable = false, length = 6)
    private String cod;
    @Basic
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @Basic
    @Column(name = "activo", nullable = false)
    private byte activo;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "jefedep_id",foreignKey = @ForeignKey(name="FK_PERSONAL_DEPARTAMENTO"))
    private PersonalEntity jefedepId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte getActivo() {
        return activo;
    }

    public void setActivo(byte activo) {
        this.activo = activo;
    }

    public PersonalEntity getJefedepId() {
        return jefedepId;
    }

    public void setJefedepId(PersonalEntity jefedepId) {
        this.jefedepId = jefedepId;
    }

}
