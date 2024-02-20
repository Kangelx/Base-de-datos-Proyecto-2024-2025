package com.api.reto.repositories;

import com.api.reto.models.EquiposEntity;
import com.api.reto.models.IncidenciasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEquipoRepository extends JpaRepository<EquiposEntity, Integer> {
}
