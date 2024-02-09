package com.api.reto.repositories;

import com.api.reto.models.PerfilesEntity;
import com.api.reto.models.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPerfilRepository extends JpaRepository<PerfilesEntity, Integer> {
}
