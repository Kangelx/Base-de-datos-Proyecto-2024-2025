package com.api.reto.services.basics;

import com.api.reto.models.DepartamentosEntity;
<<<<<<< HEAD
import com.api.reto.models.PersonalEntity;
=======
>>>>>>> e66289f6c4129b5717f58873deb75ab03ea278d9
import com.api.reto.repositories.IDepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DepartamentoService {
    @Autowired
    private IDepartamentoRepository departamentoRepository;

    public ArrayList<DepartamentosEntity> getDepartamentos() {
<<<<<<< HEAD
        //Convertir PersonalEntity atributo Integer
        for (DepartamentosEntity jefe:departamentoRepository.findAll()) {
            jefe.setJefedepartamentoIdInteger(jefe.getJefedepId().getId());
        }
=======
>>>>>>> e66289f6c4129b5717f58873deb75ab03ea278d9
        return (ArrayList<DepartamentosEntity>) departamentoRepository.findAll();
    }

    public DepartamentosEntity saveDepartamento(DepartamentosEntity departamento) {
        return departamentoRepository.save(departamento);
    }

    public Optional<DepartamentosEntity> getById(Integer id) {
        return departamentoRepository.findById(id);
    }

    public DepartamentosEntity updateById(DepartamentosEntity request, Integer id) {
        DepartamentosEntity departamento = departamentoRepository.findById(id).orElse(null);
        if (departamento != null) {
            departamento.setCod(request.getCod());
            departamento.setNombre(request.getNombre());
            departamento.setActivo(request.getActivo());
            departamento.setJefedepId(request.getJefedepId());
            departamentoRepository.save(departamento);
        }
        return departamento;
    }

    public Boolean deleteDepartamento(Integer id) {
        try {
            departamentoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
