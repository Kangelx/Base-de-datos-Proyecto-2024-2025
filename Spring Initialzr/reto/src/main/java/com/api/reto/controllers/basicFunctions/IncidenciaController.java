package com.api.reto.controllers.basicFunctions;

import com.api.reto.dto.IncidenciaDTO;
import com.api.reto.models.EquiposEntity;
import com.api.reto.models.IncidenciasEntity;
import com.api.reto.models.IncidenciasSubtiposEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.services.basics.EquipoService;
import com.api.reto.services.basics.IncidenciaService;
import com.api.reto.services.basics.IncidenciaSubtiposService;
import com.api.reto.services.basics.PersonalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/incidencia")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private IncidenciaSubtiposService subtiposService;

    @Autowired
    private PersonalService personalService;

    @Autowired
    private EquipoService equiposService;

    @GetMapping
    public ArrayList<IncidenciasEntity> getIncidencias() {
        return this.incidenciaService.getIncidencias();
    }

    @PostMapping("")
    public ResponseEntity<?> createIncidencia(@RequestBody IncidenciaDTO incidenciaDTO) {
        try {
            // Convertir DTO a entidad y guardar la incidencia
            IncidenciasEntity incidenciaEntity = convertToEntity(incidenciaDTO);
            IncidenciasEntity savedIncidencia = incidenciaService.saveIncidencia(incidenciaEntity);
            return ResponseEntity.ok(savedIncidencia);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la incidencia: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Optional<IncidenciasEntity> getIncidenciaById(@PathVariable Integer id) {
        return this.incidenciaService.getById(id);
    }

    @PutMapping("")
    public ResponseEntity<?> updateIncidencia(@RequestBody IncidenciaDTO incidenciaDTO) {
        try {
            int id = incidenciaDTO.getNum(); // Obtener el ID del DTO
            Optional<IncidenciasEntity> existingIncidenciaOptional = incidenciaService.getById(id);
            if (!existingIncidenciaOptional.isPresent()) {
                throw new EntityNotFoundException("La incidencia con ID " + id + " no existe.");
            }

            IncidenciasEntity incidenciaEntity = convertToEntity(incidenciaDTO);
            IncidenciasEntity updatedIncidencia = incidenciaService.updateById(incidenciaEntity, id);
            return ResponseEntity.ok(updatedIncidencia);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la incidencia: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public String deleteIncidencia(@PathVariable("id") Integer id) {
        boolean ok = incidenciaService.deleteIncidencia(id);
        if (ok) {
            return "Incidencia con id " + id + " borrada.";
        } else {
            return "Error, no se encuentra la incidencia con id " + id + ".";
        }
    }
    // Método para convertir IncidenciaDTO a IncidenciasEntity
    private IncidenciasEntity convertToEntity(IncidenciaDTO incidenciaDTO) {
        IncidenciasEntity incidenciaEntity = new IncidenciasEntity();
        incidenciaEntity.setTipo(incidenciaDTO.getTipo());
        incidenciaEntity.setFechaCreacion(incidenciaDTO.getFechaCreacion());
        incidenciaEntity.setFechaCierre(incidenciaDTO.getFechaCierre());
        incidenciaEntity.setDescripcion(incidenciaDTO.getDescripcion());
        incidenciaEntity.setEstado(incidenciaDTO.getEstado());
        incidenciaEntity.setAdjuntoUrl(incidenciaDTO.getAdjuntoUrl());
        incidenciaEntity.setPrioridad(incidenciaDTO.getPrioridad());

        // Obtener y asociar subtipo, creador, responsable y equipo utilizando las IDs
        Optional<IncidenciasSubtiposEntity> subtipoOptional = subtiposService.getById(incidenciaDTO.getSubtipoId());
        subtipoOptional.ifPresent(incidenciaEntity::setSubtipoId);

        Optional<PersonalEntity> creadorOptional = personalService.getById(incidenciaDTO.getCreadorId());
        creadorOptional.ifPresent(incidenciaEntity::setCreadorId);

        Optional<PersonalEntity> responsableOptional = personalService.getById(incidenciaDTO.getResponsableId());
        responsableOptional.ifPresent(incidenciaEntity::setResponsableId);

        Optional<EquiposEntity> equipoOptional = equiposService.getById(incidenciaDTO.getEquipoId());
        equipoOptional.ifPresent(incidenciaEntity::setEquipoId);

        return incidenciaEntity;
    }

}
