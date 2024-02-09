package com.api.reto.repositories;

import com.api.reto.models.IncidenciasEntity;
import com.api.reto.models.PersonalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonalRepository extends JpaRepository<PersonalEntity, Integer> {
}
