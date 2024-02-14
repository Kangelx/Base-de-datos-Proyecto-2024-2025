package com.api.reto.controllers.basicFunctions;

import com.api.reto.dto.PersonalDTO;
import com.api.reto.models.DepartamentosEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.services.basics.DepartamentoService;
import com.api.reto.services.basics.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;
    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public ArrayList<PersonalEntity> getPersonal() {
        return personalService.getPersonal();
    }


    @PostMapping("")
    public ResponseEntity<?> savePersona(@RequestBody PersonalDTO personaDTO) {
        try {
            // Verificar si el ID de la persona ya existe
            Integer personaId = personaDTO.getId();
            Optional<PersonalEntity> existingPersonaOptional = personalService.getById(personaId);
            if (existingPersonaOptional.isPresent()) {
                // El ID de la persona ya existe, devolver un mensaje de error
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID de la persona proporcionado ya existe.");
            }

            // Convertir el DTO de persona a entidad de persona
            PersonalEntity persona = personalService.convertToEntity(personaDTO);

            // Guardar y retornar la persona
            PersonalEntity savedPersona = personalService.savePersonal(persona);
            return ResponseEntity.ok(savedPersona);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la persona: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Optional <PersonalEntity> getPersonaById(@PathVariable Integer id) {
        return this.personalService.getById(id);
    }

    @PutMapping("")
    public ResponseEntity<?> updatePersonalById(@RequestBody PersonalDTO request) {
        try {

            Optional<PersonalEntity> optionalPersonal = personalService.getById(request.getId());

            if (optionalPersonal.isPresent()) {
                PersonalEntity personal = optionalPersonal.get();


                Integer departamentoId = request.getDepartamentoId();
                if (departamentoId != null) {
                    // Obtener la entidad DepartamentosEntity correspondiente al ID del departamento
                    Optional<DepartamentosEntity> optionalDepartamento = departamentoService.getByIdDTO(departamentoId);

                    if (!optionalDepartamento.isPresent()) {
                        throw new IllegalArgumentException("No se encontró ningún departamento con el ID proporcionado.");
                    }

                    DepartamentosEntity departamento = optionalDepartamento.get();
                    // Asignar el departamento al personal
                    personal.setDepartamentoId(departamento);
                } else {
                    // Si no se proporcionó el ID del departamento, mantener el departamento del personal como estaba
                }

                // Actualizar los campos del personal
                personal.setDni(request.getDni());
                personal.setNombre(request.getNombre());
                personal.setApellido1(request.getApellido1());
                personal.setApellido2(request.getApellido2());
                personal.setDireccion(request.getDireccion());
                personal.setLocalidad(request.getLocalidad());
                personal.setCp(request.getCp());
                personal.setTlf(request.getTlf());
                personal.setActivo(request.getActivo());

                personalService.savePersonal(personal);

                return ResponseEntity.ok(personal); // Retorna la entidad actualizada
            } else {
                throw new IllegalArgumentException("No se encontró ningún personal con el ID proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            // Manejar excepciones
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public String deletePersonalById(@PathVariable Integer id) {
        boolean deleted = personalService.deletePersonal(id);
        if (deleted) {
            return "Persona con id " + id + " borrada.";
        } else {
            return "Error, no se encuentra la persona con id " + id + ".";
        }
    }

}