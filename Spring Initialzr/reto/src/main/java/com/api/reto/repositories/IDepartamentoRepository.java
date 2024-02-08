package com.api.reto.repositories;

import com.api.reto.models.DepartamentosEntity;
import com.api.reto.models.EquiposEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartamentoRepository extends JpaRepository<DepartamentosEntity, Integer> {
}
