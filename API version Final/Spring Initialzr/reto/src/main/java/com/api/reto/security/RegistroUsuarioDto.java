package com.api.reto.security;

import com.api.reto.enums.PerfilEnum;

public class RegistroUsuarioDto {

    private String educantabria;
    private String password;
    private PerfilEnum perfil;

    // Getters y setters
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

    public PerfilEnum getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilEnum perfil) {
        this.perfil = perfil;
    }
}