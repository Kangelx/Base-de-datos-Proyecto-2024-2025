package com.api.reto.dto;

import com.api.reto.enums.TipoEquipoEnum;

import java.sql.Date;

public class EquipoDTO {
    private int id;
    private TipoEquipoEnum tipoEquipo;
    private Date fechaAdquisicion;
    private String etiqueta;
    private String marca;
    private String modelo;
    private String descripcion;
    private Byte baja;
    private Integer aulaNum;
    private Integer puesto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoEquipoEnum getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipoEnum tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Byte getBaja() {
        return baja;
    }

    public void setBaja(Byte baja) {
        this.baja = baja;
    }

    public Integer getAulaNum() {
        return aulaNum;
    }

    public void setAulaNum(Integer aulaNum) {
        this.aulaNum = aulaNum;
    }

    public Integer getPuesto() {
        return puesto;
    }

    public void setPuesto(Integer puesto) {
        this.puesto = puesto;
    }
}