package com.api.reto.services;

import com.api.reto.models.AulasEntity;
import com.api.reto.repositories.IAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AulaService {
    @Autowired
    IAulaRepository aulaRepository;

    public ArrayList<AulasEntity> getAulas() {
        return (ArrayList<AulasEntity>) aulaRepository.findAll();
    }

    public AulasEntity saveAula(AulasEntity aula) {
        return aulaRepository.save(aula);
    }

    public Optional<AulasEntity> getById(Integer id) {
        return aulaRepository.findById(id);
    }

    public AulasEntity updateById(AulasEntity request, Integer id) {
        AulasEntity aula = aulaRepository.findById(id).get();
        aula.setDescripcion(request.getDescripcion());
        aula.setNum(request.getNum());
        aula.setCodigo(request.getCodigo());
        aula.setPlanta(request.getPlanta());
        aulaRepository.save(aula);
        return aula;
    }

    public Boolean deleteAula(Integer id) {
        try {
            aulaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
