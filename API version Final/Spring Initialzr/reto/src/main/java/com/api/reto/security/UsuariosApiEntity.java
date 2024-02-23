package com.api.reto.security;

import com.api.reto.enums.PerfilEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios_api")
public class UsuariosApiEntity {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private PerfilEnum rol;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PerfilEnum getRol() {
        return rol;
    }

    public void setRol(PerfilEnum rol) {
        this.rol = rol;
    }
}
