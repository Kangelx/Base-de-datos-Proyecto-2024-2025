package com.api.reto.services.basics;

import com.api.reto.dto.PersonalDTO;
import com.api.reto.models.DepartamentosEntity;
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

    public PersonalDTO updateById(PersonalEntity request, Integer id) {
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
        return convertToDTO(personal);
    }

    public boolean deletePersonal(Integer id) {
        try {
            personalRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public PersonalDTO convertToDTO(PersonalEntity personal) {
        PersonalDTO dto = new PersonalDTO();
        dto.setId(personal.getId());
        dto.setDni(personal.getDni());
        dto.setNombre(personal.getNombre());
        dto.setApellido1(personal.getApellido1());
        dto.setApellido2(personal.getApellido2());
        dto.setDireccion(personal.getDireccion());
        dto.setLocalidad(personal.getLocalidad());
        dto.setCp(personal.getCp());
        dto.setTlf(personal.getTlf());
        dto.setActivo(personal.getActivo());
        if (personal.getDepartamentoId() != null) {
            dto.setDepartamentoId(personal.getDepartamentoId().getId());
        } else {
            dto.setDepartamentoId(null); // Si el departamento es nulo, establecer el ID como nulo
        }
        return dto;
    }
    public PersonalEntity convertToEntity(PersonalDTO personaDTO) {
        PersonalEntity personaEntity = new PersonalEntity();
        personaEntity.setId(personaDTO.getId());
        personaEntity.setDni(personaDTO.getDni());
        personaEntity.setNombre(personaDTO.getNombre());
        personaEntity.setApellido1(personaDTO.getApellido1());
        personaEntity.setApellido2(personaDTO.getApellido2());
        personaEntity.setDireccion(personaDTO.getDireccion());
        personaEntity.setLocalidad(personaDTO.getLocalidad());
        personaEntity.setCp(personaDTO.getCp());
        personaEntity.setTlf(personaDTO.getTlf());
        personaEntity.setActivo(personaDTO.getActivo());

        // Verificar si el departamentoId no es nulo antes de asignarlo
        if (personaDTO.getDepartamentoId() != null) {

            DepartamentosEntity departamento = new DepartamentosEntity();
            departamento.setId(personaDTO.getDepartamentoId());

            personaEntity.setDepartamentoId(departamento);
        }

        return personaEntity;
    }
}
