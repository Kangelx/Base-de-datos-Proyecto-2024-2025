package com.api.reto.services.basics;

import com.api.reto.models.PersonalEntity;
import com.api.reto.repositories.IPersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonalService {

    @Autowired
    private IPersonalRepository personalRepository;

    public ArrayList<PersonalEntity> getPersonal() {
        return (ArrayList<PersonalEntity>) personalRepository.findAll();
    }

    public PersonalEntity savePersonal(PersonalEntity personal) {
        return personalRepository.save(personal);
    }

    public Optional<PersonalEntity> getById(Integer id) {
        return personalRepository.findById(id);
    }

    public PersonalEntity updateById(PersonalEntity request, Integer id) {
        PersonalEntity personal = personalRepository.findById(id).orElse(null);
        if (personal != null) {
            personal.setDni(request.getDni());
            personal.setNombre(request.getNombre());
            personal.setApellido1(request.getApellido1());
            personal.setApellido2(request.getApellido2());
            personal.setDireccion(request.getDireccion());
            personal.setLocalidad(request.getLocalidad());
            personal.setCp(request.getCp());
            personal.setTlf(request.getTlf());
            personal.setActivo(request.getActivo());
            personal.setDepartamentoId(request.getDepartamentoId());
            personalRepository.save(personal);
        }
        return personal;
    }

    public boolean deletePersonal(Integer id) {
        try {
            personalRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}