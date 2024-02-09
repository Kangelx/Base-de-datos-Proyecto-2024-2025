package com.api.reto.services.basics;

import com.api.reto.models.EquiposEntity;
import com.api.reto.repositories.IEquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class EquipoService {
    @Autowired
    IEquipoRepository equipoRepository;

    public ArrayList<EquiposEntity> getEquipos() {

        return (ArrayList<EquiposEntity>) equipoRepository.findAll();
    }

    public EquiposEntity saveEquipo(EquiposEntity equipo) {
        return equipoRepository.save(equipo);
    }

    public Optional<EquiposEntity> getById(Integer id) {
        return equipoRepository.findById(id);
    }

    public EquiposEntity updateById(EquiposEntity request, Integer id) {
        EquiposEntity equipo = equipoRepository.findById(id).get();
        equipo.setTipoEquipo(request.getTipoEquipo());
        equipo.setFechaAdquisicion(request.getFechaAdquisicion());
        equipo.setEtiqueta(request.getEtiqueta());
        equipo.setMarca(request.getMarca());
        equipo.setModelo(request.getModelo());
        equipo.setDescripcion(request.getDescripcion());
        equipo.setBaja(request.getBaja());
        equipo.setAula_num(request.getAula_num());
        equipo.setPuesto(request.getPuesto());
        equipoRepository.save(equipo);
        return equipo;

    }

    public Boolean deleteEquipo(Integer id) {
        try {
            equipoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
