package com.api.reto.repositories;

import com.api.reto.models.AulasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAulaRepository extends JpaRepository<AulasEntity, Integer> {

}
