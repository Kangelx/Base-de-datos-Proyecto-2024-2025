package com.api.reto.controllers.basicFunctions;

import com.api.reto.models.PerfilesEntity;
import com.api.reto.services.basics.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/perfil") // End point para operaciones relacionadas con perfiles
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public ArrayList<PerfilesEntity> getPerfiles() {
        return perfilService.getPerfiles();
    }

    @PostMapping(path = "")
    public ResponseEntity<?> savePerfil(@RequestBody PerfilesEntity perfil) {
        try {
            // Verificar si el ID del perfil ya existe en la base de datos
            Integer perfilId = perfil.getPersonalId();
            Optional<PerfilesEntity> existingPerfilOptional = perfilService.getById(perfilId);
            if (existingPerfilOptional.isPresent()) {
                // El ID ya existe, devolver un mensaje de error
                return ResponseEntity.status(HttpStatus.CONFLICT).body("El perfil con el ID proporcionado ya existe.");
            }

            // El ID no existe, guardar el perfil
            PerfilesEntity savedPerfil = perfilService.savePerfil(perfil);
            return ResponseEntity.ok(savedPerfil);
        } catch (Exception e) {
            // Si ocurre algún error durante el proceso de guardado, devolver un mensaje de error interno del servidor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el perfil: " + e.getMessage());
        }
    }
    @GetMapping(path = "/{id}")
    public Optional<PerfilesEntity> getPerfilById(@PathVariable Integer id) {
        return perfilService.getById(id);
    }

    @PutMapping("")
    public ResponseEntity<?> updatePerfilById(@RequestBody PerfilesEntity request) {
        try {
            // Obtener el ID del perfil del cuerpo de la solicitud
            int id = request.getPersonalId();

            // Verificar si el perfil con el ID especificado existe
            Optional<PerfilesEntity> existingPerfilOptional = perfilService.getById(id);
            if (!existingPerfilOptional.isPresent()) {
                // El perfil con el ID especificado no existe, devolver un mensaje de error
                return ResponseEntity.notFound().build();
            }

            // El perfil existe, proceder con la actualización
            PerfilesEntity updatedPerfil = perfilService.updateById(request, id);
            return ResponseEntity.ok(updatedPerfil);
        } catch (Exception e) {
            // Si ocurre algún error durante el proceso de actualización, devolver un mensaje de error interno del servidor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el perfil: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String deletePerfilById(@PathVariable("id") Integer id) {
        boolean deleted = perfilService.deletePerfil(id);
        if (deleted) {
            return "Perfil con ID " + id + " borrado correctamente.";
        } else {
            return "Error al borrar el perfil con ID " + id + ".";
        }
    }
}