package com.api.reto.repositories;

import com.api.reto.models.PerfilesEntity;
import com.api.reto.models.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface IPerfilRepository extends JpaRepository<PerfilesEntity, Integer> {

    PerfilesEntity findByEducantabria(String username);
}
