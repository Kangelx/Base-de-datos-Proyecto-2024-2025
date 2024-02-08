package com.api.reto.services;

import com.api.reto.models.AulasEntity;
import com.api.reto.models.IncidenciasSubtiposEntity;
import com.api.reto.repositories.IAulaRepository;
import com.api.reto.repositories.IIncidenciasSubtiposRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class IncidenciaSubtiposService {
    @Autowired
    IIncidenciasSubtiposRepository incidenciasSubtiposRepository;

    public ArrayList<IncidenciasSubtiposEntity> getIncidenciasSubtipos() {
        return (ArrayList<IncidenciasSubtiposEntity>) incidenciasSubtiposRepository.findAll();
    }

    public IncidenciasSubtiposEntity saveIncidenciaSubtipo(IncidenciasSubtiposEntity incidenciasSubtipos) {
        return incidenciasSubtiposRepository.save(incidenciasSubtipos);
    }

    public Optional<IncidenciasSubtiposEntity> getById(Integer id) {
        return incidenciasSubtiposRepository.findById(id);
    }

    public IncidenciasSubtiposEntity updateById(IncidenciasSubtiposEntity request, Integer id) {
        IncidenciasSubtiposEntity incidenciasSubtipos=incidenciasSubtiposRepository.findById(id).get();
        incidenciasSubtipos.setId(request.getId());
        incidenciasSubtipos.setTipo(request.getTipo());
        incidenciasSubtipos.setSubtipoNombre(request.getSubtipoNombre());
        incidenciasSubtipos.setSubSubtipo(request.getSubSubtipo());
        incidenciasSubtiposRepository.save(incidenciasSubtipos);
        return incidenciasSubtipos;
    }

    public Boolean deleteIncidenciaSubtipo(Integer id) {
        try {
            incidenciasSubtiposRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
