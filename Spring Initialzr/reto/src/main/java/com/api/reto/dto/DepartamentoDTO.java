package com.api.reto.dto;

public class DepartamentoDTO {
    private int id;
    private String cod;
    private String nombre;
    private byte activo;
    private Integer jefeDepartamentoId; // Tipo Integer para el ID del jefe del departamento

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

    public Integer getJefeDepartamentoId() {
        return jefeDepartamentoId;
    }

    public void setJefeDepartamentoId(Integer jefeDepartamentoId) {
        this.jefeDepartamentoId = jefeDepartamentoId;
    }
}