package com.api.reto.repositories;

import com.api.reto.models.EquiposEntity;
import com.api.reto.models.IncidenciasSubtiposEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIncidenciasSubtiposRepository extends JpaRepository<IncidenciasSubtiposEntity, Integer> {
}
