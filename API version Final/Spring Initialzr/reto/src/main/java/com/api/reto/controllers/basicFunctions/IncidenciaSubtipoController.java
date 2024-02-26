package com.api.reto.controllers.basicFunctions;

import com.api.reto.models.IncidenciasSubtiposEntity;
import com.api.reto.services.basics.IncidenciaSubtiposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/incidenciasubtipo")
public class IncidenciaSubtipoController {
    @Autowired
    private IncidenciaSubtiposService incidenciaSubtiposService;
    @GetMapping
    @PreAuthorize("hasAuthority('administrador')")
    public ArrayList<IncidenciasSubtiposEntity> getIncidenciaSubtipo() {
        return this.incidenciaSubtiposService.getIncidenciasSubtipos();
    }
    @PostMapping("")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<?> saveIncidenciaSubtipo(@RequestBody IncidenciasSubtiposEntity incidenciaSubtipo) {
        try {

            if (incidenciaSubtipo.getId() != null) {

                Optional<IncidenciasSubtiposEntity> existingSubtipoOptional = incidenciaSubtiposService.getById(incidenciaSubtipo.getId());
                if (existingSubtipoOptional.isPresent()) {

                    return ResponseEntity.status(HttpStatus.CONFLICT).body("El ID proporcionado ya existe.");
                }
            }
            IncidenciasSubtiposEntity savedSubtipo = incidenciaSubtiposService.saveIncidenciaSubtipo(incidenciaSubtipo);
            return ResponseEntity.ok(savedSubtipo);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el subtipo de incidencia: " + e.getMessage());
        }
    }
    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('administrador')")
    public Optional<IncidenciasSubtiposEntity> getIncidenciaSubtipoById(@PathVariable Integer id) {
        return this.incidenciaSubtiposService.getById(id);
    }
    @PutMapping("")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<?> updateIncidenciaSubtipoById(@RequestBody IncidenciasSubtiposEntity request) {
        try {
            int id = request.getId();

            Optional<IncidenciasSubtiposEntity> existingSubtipoOptional = incidenciaSubtiposService.getById(id);
            if (!existingSubtipoOptional.isPresent()) {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El ID proporcionado no existe.");
            }
            IncidenciasSubtiposEntity updatedSubtipo = this.incidenciaSubtiposService.updateById(request, id);
            return ResponseEntity.ok(updatedSubtipo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el subtipo de incidencia: " + e.getMessage());
        }
    }
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('administrador')")
    public String deleteById(@PathVariable("id") Integer id) {
        boolean ok = this.incidenciaSubtiposService.deleteIncidenciaSubtipo(id);
        if (ok) {
            return "Aula con id " + id + " borrado.";
        } else {
            return "Error, no se encuentra el aula con id " + id + ".";
        }
    }
}
