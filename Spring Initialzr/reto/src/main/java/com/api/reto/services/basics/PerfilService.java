package com.api.reto.services.basics;

import com.api.reto.models.PerfilesEntity;
import com.api.reto.repositories.IPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private IPerfilRepository perfilRepository;

    public ArrayList<PerfilesEntity> getPerfiles() {
        return (ArrayList<PerfilesEntity>) perfilRepository.findAll();
    }

    public PerfilesEntity savePerfil(PerfilesEntity perfil) {
        return perfilRepository.save(perfil);
    }

    public Optional<PerfilesEntity> getById(Integer id) {
        return perfilRepository.findById(id);
    }

    public PerfilesEntity updateById(PerfilesEntity request, Integer id) {
        PerfilesEntity perfil = perfilRepository.findById(id).orElse(null);
        if (perfil != null) {
            perfil.setDominio(request.getDominio());
            perfil.setEducantabria(request.getEducantabria());
            perfil.setPassword(request.getPassword());
            perfil.setPerfil(request.getPerfil());
            perfilRepository.save(perfil);
        }
        return perfil;
    }

    public Boolean deletePerfil(Integer id) {
        try {
            perfilRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}