package com.api.reto.dto;


import com.api.reto.models.IncidenciasEntity;
import com.api.reto.models.PersonalEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;

public class ComentarioDTO {
    private int id;
    private String texto;
    private Timestamp fechahora;
    private Integer incidenciaNum;
    private Integer personalId;
    private String adjuntoUrl;

    // Constructor
    public ComentarioDTO() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Timestamp getFechahora() {
        return fechahora;
    }

    public void setFechahora(Timestamp fechahora) {
        this.fechahora = fechahora;
    }

    public Integer getIncidenciaNum() {
        return incidenciaNum;
    }

    public void setIncidenciaNum(Integer incidenciaNum) {
        this.incidenciaNum = incidenciaNum;
    }

    public Integer getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Integer personalId) {
        this.personalId = personalId;
    }

    public String getAdjuntoUrl() {
        return adjuntoUrl;
    }

    public void setAdjuntoUrl(String adjuntoUrl) {
        this.adjuntoUrl = adjuntoUrl;
    }
}