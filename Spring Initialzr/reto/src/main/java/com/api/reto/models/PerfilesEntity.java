package com.api.reto.models;

import jakarta.persistence.*;

@Entity
@Table(name = "perfiles", schema = "tic_incidencias", catalog = "")
public class PerfilesEntity {
    public enum PerfilEnum {
        administrador,
        profesor,
        otros
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @ManyToOne
    @JoinColumn(name = "personal_id", foreignKey = @ForeignKey(name = "FK_PERSONAL_PERFIL"))
    private PersonalEntity personalId;
    @Basic
    @Column(name = "dominio", nullable = true, length = 15)
    private String dominio;
    @Basic
    @Column(name = "educantabria", nullable = true, length = 50)
    private String educantabria;
    @Basic
    @Column(name = "password", nullable = true, length = 32)
    private String password;
    @Basic
    @Column(name = "perfil", nullable = false)
    private PerfilEnum perfil;

    public PersonalEntity getPersonalId() {
        return personalId;
    }

    public void setPersonalId(PersonalEntity personalId) {
        this.personalId = personalId;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getEducantabria() {
        return educantabria;
    }

    public void setEducantabria(String educantabria) {
        this.educantabria = educantabria;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }
}
