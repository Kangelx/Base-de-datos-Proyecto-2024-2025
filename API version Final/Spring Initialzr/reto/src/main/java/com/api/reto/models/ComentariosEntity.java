package com.api.reto.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "comentarios", schema = "tic_incidencias", catalog = "")
public class ComentariosEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "texto", nullable = false, length = -1)
    private String texto;
    @Basic
    @Column(name = "fechahora", nullable = false)
    private Timestamp fechahora;
    @ManyToOne
    @JoinColumn(name = "incidencia_num",foreignKey = @ForeignKey(name="FK_INCIDENCIA_COMENTARIO"))
    private IncidenciasEntity incidenciaNum;
    @ManyToOne
    @JoinColumn(name = "personal_id",foreignKey = @ForeignKey(name="FK_PERSONAL_COMENTARIO"))
    private PersonalEntity personalId;
    @Basic
    @Column(name = "adjunto_url", nullable = true, length = -1)
    private String adjuntoUrl;

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

    public IncidenciasEntity getIncidenciaNum() {
        return incidenciaNum;
    }

    public void setIncidenciaNum(IncidenciasEntity incidenciaNum) {
        this.incidenciaNum = incidenciaNum;
    }

    public PersonalEntity getPersonalId() {
        return personalId;
    }

    public void setPersonalId(PersonalEntity personalId) {
        this.personalId = personalId;
    }

    public String getAdjuntoUrl() {
        return adjuntoUrl;
    }

    public void setAdjuntoUrl(String adjuntoUrl) {
        this.adjuntoUrl = adjuntoUrl;
    }
}
