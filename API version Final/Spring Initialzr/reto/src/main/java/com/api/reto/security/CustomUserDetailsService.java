package com.api.reto.security;

import com.api.reto.models.PerfilesEntity;
import com.api.reto.repositories.IPerfilRepository;
import com.api.reto.repositories.IUsuarioApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioApiRepository usuarioApiRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuariosApiEntity usuarioApi = usuarioApiRepository.findByUsername(username);
        if (usuarioApi == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Asume que PerfilEnum tiene un método name() que devuelve el nombre del enumerado como String
        String roleName = usuarioApi.getRol().name();

        List<GrantedAuthority> authorities = Collections.singletonList(
                new SimpleGrantedAuthority(roleName)
        );

        return new User(usuarioApi.getUsername(), usuarioApi.getPassword(), authorities);
    }
}