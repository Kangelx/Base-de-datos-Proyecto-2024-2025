package com.api.reto.repositories;

import com.api.reto.models.PerfilesEntity;
import com.api.reto.security.UsuariosApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioApiRepository extends JpaRepository<UsuariosApiEntity,String> {
    UsuariosApiEntity findByUsername(String username);
}
