package com.api.reto.repositories;

import com.api.reto.models.AulasEntity;
import com.api.reto.models.IncidenciasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIncidenciasRepository extends JpaRepository<IncidenciasEntity, Integer> {
}
