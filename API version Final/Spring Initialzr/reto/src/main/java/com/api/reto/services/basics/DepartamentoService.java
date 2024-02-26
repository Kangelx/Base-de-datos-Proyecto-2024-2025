package com.api.reto.services.basics;

import com.api.reto.dto.DepartamentoDTO;
import com.api.reto.models.DepartamentosEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.repositories.IDepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DepartamentoService {
    @Autowired
    private IDepartamentoRepository departamentoRepository;

    public ArrayList<DepartamentoDTO> getDepartamentos() {
        ArrayList<DepartamentoDTO> departamentosDTO = new ArrayList<>();
        for (DepartamentosEntity departamento : departamentoRepository.findAll()) {
            departamentosDTO.add(convertToDTO(departamento));
        }
        return departamentosDTO;
    }

    public DepartamentoDTO getById(Integer id) {
        Optional<DepartamentosEntity> departamentoOptional = departamentoRepository.findById(id);
        if (departamentoOptional.isPresent()) {
            return convertToDTO(departamentoOptional.get());
        }
        return null;
    }
    public Optional<DepartamentosEntity> getByIdDTO(Integer id) {
        return departamentoRepository.findById(id);
    }
    public DepartamentoDTO saveDepartamento(DepartamentosEntity departamento) {
        DepartamentosEntity savedDepartamento = departamentoRepository.save(departamento);
        return convertToDTO(savedDepartamento);
    }

    public DepartamentoDTO updateById(DepartamentosEntity request, Integer id) {
        DepartamentosEntity departamento = departamentoRepository.findById(id).orElse(null);
        if (departamento != null) {
            departamento.setCod(request.getCod());
            departamento.setNombre(request.getNombre());
            departamento.setActivo(request.getActivo());
            departamento.setJefedepId(request.getJefedepId());
            departamentoRepository.save(departamento);
            return convertToDTO(departamento);
        }
        return null;
    }
    public Boolean deleteDepartamento(Integer id) {
        try {
            departamentoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Método auxiliar para convertir DepartamentosEntity a DepartamentoDTO
    private DepartamentoDTO convertToDTO(DepartamentosEntity departamento) {
        DepartamentoDTO dto = new DepartamentoDTO();
        dto.setId(departamento.getId());
        dto.setCod(departamento.getCod());
        dto.setNombre(departamento.getNombre());
        dto.setActivo(departamento.getActivo());
        if (departamento.getJefedepId() != null) {
            dto.setJefeDepartamentoId(departamento.getJefedepId().getId());
        } else {
            dto.setJefeDepartamentoId(null); // Si el jefe del departamento es nulo, establecer el ID como nulo
        }
        return dto;
    }
}