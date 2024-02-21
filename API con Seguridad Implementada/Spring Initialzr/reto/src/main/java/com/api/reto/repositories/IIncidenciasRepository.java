package com.api.reto.repositories;

import com.api.reto.enums.EstadoEnum;
import com.api.reto.models.AulasEntity;
import com.api.reto.models.IncidenciasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IIncidenciasRepository extends JpaRepository<IncidenciasEntity, Integer> {
    List<IncidenciasEntity> findByEstado(EstadoEnum estado);

}
