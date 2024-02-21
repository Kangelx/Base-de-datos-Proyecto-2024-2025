package com.api.reto.security;

import com.api.reto.models.PerfilesEntity;
import com.api.reto.repositories.IPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IPerfilRepository perfilesRepository; // Asume que este es tu repositorio JPA para PerfilesEntity

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aquí, asumimos que "educantabria" es el campo utilizado como nombre de usuario para la autenticación
        PerfilesEntity perfil = perfilesRepository.findByEducantabria(username);
        if (perfil == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con educantabria: " + username);
        }

        // Convertir el enum de perfil a una lista de GrantedAuthority.
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(perfil.getPerfil().name()));

        // Crear y retornar un objeto UserDetails con la información cargada
        return new org.springframework.security.core.userdetails.User(perfil.getEducantabria(), perfil.getPassword(), authorities);
    }
}
