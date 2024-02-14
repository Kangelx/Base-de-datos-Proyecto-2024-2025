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
import org.apache.poi.sl.draw.geom.GuideIf;
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
        IncidenciasEntity incidenciaEnt = new IncidenciasEntity();
        PersonalEntity personaEnt = new PersonalEntity();
        EquiposEntity equipoEnt=new EquiposEntity();
        Integer responsableId;
        Integer equipoId;
        Integer incidenciaId=incidenciaDTO.getNum();
        try {
            // Verificar si el ID ya existe
            Optional<IncidenciasEntity> existingIncidencia = incidenciaService.getById(incidenciaId);
            if (existingIncidencia.isPresent()) {
                // Si el ID ya existe, lanzar un error
                return ResponseEntity.badRequest().body("La incidencia con el ID " + incidenciaDTO.getNum() + " no existe.");
            } else {
                responsableId = incidenciaDTO.getResponsableId();
                if (responsableId == null) {
                    incidenciaEnt.setResponsableId(null);
                } else {
                    Optional<PersonalEntity> exitingPersonalEntity = personalService.getById(responsableId);
                    if (exitingPersonalEntity.isPresent()) {
                        personaEnt = exitingPersonalEntity.get();
                        incidenciaEnt.setResponsableId(personaEnt);
                    } else {
                        return ResponseEntity.badRequest().body("La persona con el ID " + incidenciaDTO.getResponsableId() + " no existe.");
                    }
                }
                equipoId = incidenciaDTO.getEquipoId();
                if (equipoId == null) {
                    incidenciaEnt.setEquipoId(null);
                } else {
                    Optional<EquiposEntity>existingEquipo=equiposService.getById(equipoId);
                    if(existingEquipo.isPresent()){
                        equipoEnt=existingEquipo.get();
                        incidenciaEnt.setEquipoId(equipoEnt);
                    }else{
                        return ResponseEntity.badRequest().body("El equipo con el ID " + incidenciaDTO.getEquipoId()+ " no existe.");
                    }
                }
            }
            incidenciaEnt.setTipo(incidenciaDTO.getTipo());
            incidenciaEnt.setFechaCreacion(incidenciaDTO.getFechaCreacion());
            incidenciaEnt.setFechaCierre(incidenciaDTO.getFechaCierre());
            incidenciaEnt.setDescripcion(incidenciaDTO.getDescripcion());
            incidenciaEnt.setEstado(incidenciaDTO.getEstado());
            incidenciaEnt.setAdjuntoUrl(incidenciaDTO.getAdjuntoUrl());
            incidenciaEnt.setPrioridad(incidenciaDTO.getPrioridad());
            Optional<IncidenciasSubtiposEntity> subtipoOptional = subtiposService.getById(incidenciaDTO.getSubtipoId());
            subtipoOptional.ifPresent(incidenciaEnt::setSubtipoId);
            Optional<PersonalEntity> creadorOptional = personalService.getById(incidenciaDTO.getCreadorId());
            creadorOptional.ifPresent(incidenciaEnt::setCreadorId);


            IncidenciasEntity savedIncidencia = incidenciaService.saveIncidencia(incidenciaEnt);
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
        // Extraer el num (ID) directamente del DTO
        Integer incidenciaNum = incidenciaDTO.getNum();
        if (incidenciaNum == null) {
            return ResponseEntity.badRequest().body("El número de incidencia es necesario para actualizar.");
        }

        try {
            // Verificar si la incidencia existe
            Optional<IncidenciasEntity> existingIncidenciaOpt = incidenciaService.getById(incidenciaNum);
            if (!existingIncidenciaOpt.isPresent()) {
                return ResponseEntity.badRequest().body("La incidencia con el número " + incidenciaNum + " no existe.");
            }

            IncidenciasEntity incidenciaEnt = existingIncidenciaOpt.get();
            // Actualizar los campos de la entidad con los datos del DTO
            actualizarCamposIncidencia(incidenciaEnt, incidenciaDTO);

            IncidenciasEntity updatedIncidencia = incidenciaService.saveIncidencia(incidenciaEnt);
            return ResponseEntity.ok(updatedIncidencia);
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
    private void actualizarCamposIncidencia(IncidenciasEntity incidenciaEnt, IncidenciaDTO incidenciaDTO) {
        // Actualizar campos básicos
        incidenciaEnt.setTipo(incidenciaDTO.getTipo());
        incidenciaEnt.setFechaCreacion(incidenciaDTO.getFechaCreacion());
        incidenciaEnt.setFechaCierre(incidenciaDTO.getFechaCierre());
        incidenciaEnt.setDescripcion(incidenciaDTO.getDescripcion());
        incidenciaEnt.setEstado(incidenciaDTO.getEstado());
        incidenciaEnt.setAdjuntoUrl(incidenciaDTO.getAdjuntoUrl());
        incidenciaEnt.setPrioridad(incidenciaDTO.getPrioridad());

        // Actualizar el responsable si se proporciona
        if (incidenciaDTO.getResponsableId() != null) {
            personalService.getById(incidenciaDTO.getResponsableId()).ifPresent(incidenciaEnt::setResponsableId);
        } else {
            incidenciaEnt.setResponsableId(null);
        }

        // Actualizar el equipo si se proporciona
        if (incidenciaDTO.getEquipoId() != null) {
            equiposService.getById(incidenciaDTO.getEquipoId()).ifPresent(incidenciaEnt::setEquipoId);
        } else {
            incidenciaEnt.setEquipoId(null);
        }

        // Actualizar el subtipo si se proporciona
        if (incidenciaDTO.getSubtipoId() != null) {
            subtiposService.getById(incidenciaDTO.getSubtipoId()).ifPresent(incidenciaEnt::setSubtipoId);
        }
    }
}
