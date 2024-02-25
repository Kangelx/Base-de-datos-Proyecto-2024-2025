package com.api.reto.controllers.basicFunctions;
import com.api.reto.dto.PersonalDTO;
import com.api.reto.models.DepartamentosEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.services.basics.DepartamentoService;
import com.api.reto.services.basics.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('administrador')")
    public ArrayList<PersonalEntity> getPersonal() {
        return personalService.getPersonal();
    }


    @PostMapping("")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<?> savePersona(@RequestBody PersonalDTO personaDTO) {
        try {
            // Verificar si el ID de la persona ya existe
            Integer personaId = personaDTO.getId();
            Optional<PersonalEntity> existingPersonaOptional = personalService.getById(personaId);
            if (existingPersonaOptional.isPresent()) {
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
    @PreAuthorize("hasAuthority('administrador')")
    public Optional <PersonalEntity> getPersonaById(@PathVariable Integer id) {
        return this.personalService.getById(id);
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<?> updatePersonalById(@RequestBody PersonalDTO request) {
        try {
            Optional<PersonalEntity> optionalPersonal = personalService.getById(request.getId());

            if (!optionalPersonal.isPresent()) {
                throw new IllegalArgumentException("No se encontró ningún personal con el ID proporcionado.");
            }

            PersonalEntity personal = optionalPersonal.get();
            Integer departamentoId = request.getDepartamentoId();

            if (departamentoId != null) {
                // Intenta obtener la entidad Departamento correspondiente al ID del departamento
                Optional<DepartamentosEntity> optionalDepartamento = departamentoService.getByIdDTO(departamentoId);
                if (!optionalDepartamento.isPresent()) {
                    throw new IllegalArgumentException("No se encontró ningún departamento con el ID proporcionado.");
                }
                // Asignar el departamento al personal solo si se encuentra
                personal.setDepartamentoId(optionalDepartamento.get());
            }
          else{
                personal.setDni(request.getDni());
                personal.setNombre(request.getNombre());
                personal.setApellido1(request.getApellido1());
                personal.setApellido2(request.getApellido2());
                personal.setDireccion(request.getDireccion());
                personal.setLocalidad(request.getLocalidad());
                personal.setCp(request.getCp());
                personal.setTlf(request.getTlf());
                personal.setActivo(request.getActivo());
                personal.setDepartamentoId(null);
            }


            // Guardar la entidad personal actualizada
            personalService.savePersonal(personal);

            return ResponseEntity.ok(personal);
        } catch (IllegalArgumentException e) {

            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('administrador')")
    public String deletePersonalById(@PathVariable Integer id) {
        boolean deleted = personalService.deletePersonal(id);
        if (deleted) {
            return "Persona con id " + id + " borrada.";
        } else {
            return "Error, no se encuentra la persona con id " + id + ".";
        }
    }
}