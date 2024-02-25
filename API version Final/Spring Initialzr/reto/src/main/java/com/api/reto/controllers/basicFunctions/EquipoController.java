package com.api.reto.controllers.basicFunctions;


import com.api.reto.dto.EquipoDTO;
import com.api.reto.models.AulasEntity;
import com.api.reto.models.EquiposEntity;
import com.api.reto.services.basics.AulaService;
import com.api.reto.services.basics.EquipoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/equipo") //End point para buscar por equipo
public class EquipoController {
    @Autowired
    private EquipoService equipoService;
    @Autowired
    private AulaService aulaService;

    @GetMapping
    @PreAuthorize("hasAuthority('administrador')")
    public ArrayList<EquiposEntity> getEquipos() {
        return this.equipoService.getEquipos();
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('administrador')")
    public EquiposEntity saveEquipo(@RequestBody EquipoDTO equipoDTO) {
        int equipoId = equipoDTO.getId();

        // Verificar si el equipo ya existe en la base de datos
        Optional<EquiposEntity> existingEquipoOptional = equipoService.getById(equipoId);
        if (existingEquipoOptional.isPresent()) {
            throw new IllegalStateException("No se puede modificar un equipo existente. Use un endpoint PUT para actualizar equipos.");
        }

        // El equipo no existe, proceder con la inserción
        EquiposEntity equipoEntity = new EquiposEntity();
        Optional<AulasEntity> aulaDelEquipo = aulaService.getById(equipoDTO.getAulaNum());
        equipoEntity.setTipoEquipo(equipoDTO.getTipoEquipo());
        equipoEntity.setId(equipoDTO.getId());
        equipoEntity.setBaja(equipoDTO.getBaja());
        equipoEntity.setEtiqueta(equipoDTO.getEtiqueta());
        equipoEntity.setDescripcion(equipoDTO.getDescripcion());
        equipoEntity.setFechaAdquisicion(equipoDTO.getFechaAdquisicion());
        equipoEntity.setMarca(equipoDTO.getMarca());
        equipoEntity.setModelo(equipoDTO.getModelo());
        equipoEntity.setPuesto(equipoDTO.getPuesto());
        if (aulaDelEquipo.isPresent())
            equipoEntity.setAula_num(aulaDelEquipo.get());

        return equipoService.saveEquipo(equipoEntity);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('administrador')")
    public Optional<EquiposEntity> getEquipoById(@PathVariable Integer id) {
        return this.equipoService.getById(id);
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('administrador')")
    public EquiposEntity updateEquipoById(@RequestBody EquipoDTO equipoDTO) {
        try {
            int id = equipoDTO.getId(); // Obtener el ID del DTO

            EquiposEntity equipoEntity = new EquiposEntity();
            Optional<AulasEntity> aulaDelEquipo = aulaService.getById(equipoDTO.getAulaNum());

            // Verificar si el equipo existe en la base de datos
            Optional<EquiposEntity> existingEquipoOptional = equipoService.getById(id);
            if (!existingEquipoOptional.isPresent()) {
                throw new EntityNotFoundException("El equipo con ID " + id + " no existe en la base de datos.");
            }

            equipoEntity.setTipoEquipo(equipoDTO.getTipoEquipo());
            equipoEntity.setId(equipoDTO.getId());
            equipoEntity.setBaja(equipoDTO.getBaja());
            equipoEntity.setEtiqueta(equipoDTO.getEtiqueta());
            equipoEntity.setDescripcion(equipoDTO.getDescripcion());
            equipoEntity.setFechaAdquisicion(equipoDTO.getFechaAdquisicion());
            equipoEntity.setMarca(equipoDTO.getMarca());
            equipoEntity.setModelo(equipoDTO.getModelo());
            equipoEntity.setPuesto(equipoDTO.getPuesto());

            if (aulaDelEquipo.isPresent()) {
                equipoEntity.setAula_num(aulaDelEquipo.get());
            } else {
                throw new EntityNotFoundException("El aula especificada para el equipo no existe en la base de datos.");
            }

            return equipoService.updateById(equipoEntity, id);
        } catch (EntityNotFoundException e) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar el equipo: " + e.getMessage(), e);
        }
    }


    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('administrador')")
    public String deleteById(@PathVariable("id") Integer id) {
        boolean ok = this.equipoService.deleteEquipo(id);
        if (ok) {
            return "Equipo con id " + id + " borrado.";
        } else {
            return "Error, no se encuentra el equipo con id " + id + ".";
        }
    }
}
