package com.api.reto.services.basics;

import com.api.reto.models.IncidenciasEntity;
import com.api.reto.repositories.IIncidenciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class IncidenciaService {
<<<<<<< HEAD
=======

>>>>>>> e66289f6c4129b5717f58873deb75ab03ea278d9
    @Autowired
    IIncidenciasRepository incidenciasRepository;

    public ArrayList<IncidenciasEntity> getIncidencias() {
        return (ArrayList<IncidenciasEntity>) incidenciasRepository.findAll();
    }

    public IncidenciasEntity saveIncidencia(IncidenciasEntity incidencia) {
        return incidenciasRepository.save(incidencia);
    }

    public Optional<IncidenciasEntity> getById(Integer id) {
        return incidenciasRepository.findById(id);
    }

    public IncidenciasEntity updateById(IncidenciasEntity request, Integer id) {
        IncidenciasEntity incidencia = incidenciasRepository.findById(id).orElse(null);
        if (incidencia != null) {
            incidencia.setTipo(request.getTipo());
            incidencia.setSubtipoId(request.getSubtipoId());
            incidencia.setFechaCreacion(request.getFechaCreacion());
            incidencia.setFechaCierre(request.getFechaCierre());
            incidencia.setDescripcion(request.getDescripcion());
            incidencia.setEstado(request.getEstado());
            incidencia.setAdjuntoUrl(request.getAdjuntoUrl());
            incidencia.setCreadorId(request.getCreadorId());
            incidencia.setResponsableId(request.getResponsableId());
            incidencia.setEquipoId(request.getEquipoId());
            incidencia.setPrioridad(request.getPrioridad());
            incidenciasRepository.save(incidencia);
        }
        return incidencia;
    }

    public boolean deleteIncidencia(Integer id) {
        try {
            incidenciasRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
