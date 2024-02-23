package com.api.reto.repositories;

import com.api.reto.models.ComentariosEntity;
import com.api.reto.models.EquiposEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IComentarioRepository extends JpaRepository<ComentariosEntity, Integer> {

}
