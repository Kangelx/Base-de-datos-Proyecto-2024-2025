package com.api.reto.models;

import jakarta.persistence.*;

@Entity
@Table(name = "personal", schema = "tic_incidencias", catalog = "")
public class PersonalEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "dni", nullable = true, length = 9)
    private String dni;
    @Basic
    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;
    @Basic
    @Column(name = "apellido1", nullable = false, length = 25)
    private String apellido1;
    @Basic
    @Column(name = "apellido2", nullable = true, length = 25)
    private String apellido2;
    @Basic
    @Column(name = "direccion", nullable = true, length = 80)
    private String direccion;
    @Basic
    @Column(name = "localidad", nullable = true, length = 25)
    private String localidad;
    @Basic
    @Column(name = "cp", nullable = true, length = 5)
    private String cp;
    @Basic
    @Column(name = "tlf", nullable = true, length = 9)
    private String tlf;
    @Basic
    @Column(name = "activo", nullable = true)
    private Byte activo;
    @ManyToOne
    @JoinColumn(name = "departamento_id",foreignKey = @ForeignKey(name="FK_DEPARTAMENTO_PERSONAL"))
    private DepartamentosEntity departamentoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public Byte getActivo() {
        return activo;
    }

    public void setActivo(Byte activo) {
        this.activo = activo;
    }

    public DepartamentosEntity getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(DepartamentosEntity departamentoId) {
        this.departamentoId = departamentoId;
    }
}
