package com.api.reto.controllers.basicFunctions;

import com.api.reto.dto.PersonalDTO;
import com.api.reto.models.DepartamentosEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.services.basics.DepartamentoService;
import com.api.reto.services.basics.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/post")
    public PersonalEntity savePersona(@RequestBody PersonalDTO personaDTO) {
        // Obtener el departamento correspondiente al ID proporcionado en el DTO
        Integer departamentoId = personaDTO.getDepartamentoId();
        Optional<DepartamentosEntity> optionalDepartamento = departamentoService.getByIdDTO(departamentoId);

        // Verificar si el departamento existe
        if (optionalDepartamento.isPresent()) {
            // Convertir el DTO de persona a entidad de persona
            PersonalEntity persona = personalService.convertToEntity(personaDTO);

            // Guardar y retornar la persona
            return personalService.savePersonal(persona);
        } else {
            // Si el departamento no existe, lanzar una excepción o manejar el error según sea necesario
            throw new IllegalArgumentException("El departamento con el ID proporcionado no existe.");
        }
    }
    @GetMapping("/{id}")
    public Optional <PersonalEntity> getPersonaById(@PathVariable Integer id) {
        return this.personalService.getById(id);
    }

    @PutMapping("/put")
    public ResponseEntity<?> updatePersonalById(@RequestBody PersonalDTO request) {
        try {
            // Obtener el personal a actualizar usando su ID
            Optional<PersonalEntity> optionalPersonal = personalService.getById(request.getId());

            if (optionalPersonal.isPresent()) {
                PersonalEntity personal = optionalPersonal.get();

                // Obtener la entidad DepartamentosEntity correspondiente al ID del departamento
                Optional<DepartamentosEntity> optionalDepartamento = departamentoService.getByIdDTO(request.getDepartamentoId());

                if (optionalDepartamento.isPresent()) {
                    DepartamentosEntity departamento = optionalDepartamento.get();

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
                    personal.setDepartamentoId(departamento);


                    personalService.savePersonal(personal);

                    return ResponseEntity.ok(personal); // Retorna la entidad actualizada
                } else {

                    throw new IllegalArgumentException("No se encontró ningún departamento con el ID proporcionado.");
                }
            } else {

                throw new IllegalArgumentException("No se encontró ningún personal con el ID proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            // Manejar excepciones
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/del/{id}")
    public String deletePersonalById(@PathVariable Integer id) {
        boolean deleted = personalService.deletePersonal(id);
        if (deleted) {
            return "Persona con id " + id + " borrada.";
        } else {
            return "Error, no se encuentra la persona con id " + id + ".";
        }
    }

}