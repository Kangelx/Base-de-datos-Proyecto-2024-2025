package com.api.reto.security;

import com.api.reto.dto.IncidenciaDTO;

import java.util.List;

public class DevolverTodoDTO {

    private String nombre;
    private AuthTokenResponse token;
    private List<IncidenciaDTO> listaIncidencias;
    private int id;

    public DevolverTodoDTO() {
    }

    public DevolverTodoDTO(String nombre, AuthTokenResponse token, List<IncidenciaDTO> listaIncidencias, int id) {
        this.nombre = nombre;
        this.token = token;
        this.listaIncidencias = listaIncidencias;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public AuthTokenResponse getToken() {
        return token;
    }

    public void setToken(AuthTokenResponse token) {
        this.token = token;
    }

    public List<IncidenciaDTO> getListaIncidencias() {
        return listaIncidencias;
    }

    public void setListaIncidencias(List<IncidenciaDTO> listaIncidencias) {
        this.listaIncidencias = listaIncidencias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
