package com.api.reto.controllers.basicFunctions;

import com.api.reto.dto.DepartamentoDTO;
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
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @Autowired
    private PersonalService personalService;

    @GetMapping
    public ArrayList<DepartamentoDTO> getDepartamentos() {
        return departamentoService.getDepartamentos();
    }

    @PostMapping(path = "/post")
    public ResponseEntity<?> saveDepartamento(@RequestBody DepartamentoDTO departamentoDTO) {
        try {
            // Buscar la entidad PersonalEntity correspondiente al ID proporcionado
            Optional<PersonalEntity> optionalPersonal = personalService.getById(departamentoDTO.getJefeDepartamentoId());
            if (optionalPersonal.isPresent()) {
                // Si se encuentra la entidad PersonalEntity, obténla y asígnala al departamento
                PersonalEntity jefeDepartamento = optionalPersonal.get();

                // Crear una nueva instancia de DepartamentosEntity y establecer los valores desde el DTO
                DepartamentosEntity departamentoEntity = new DepartamentosEntity();
                departamentoEntity.setCod(departamentoDTO.getCod());
                departamentoEntity.setNombre(departamentoDTO.getNombre());
                departamentoEntity.setActivo(departamentoDTO.getActivo());
                departamentoEntity.setJefedepId(jefeDepartamento);

                // Guardar el departamento
                DepartamentoDTO nuevoDepartamento = departamentoService.saveDepartamento(departamentoEntity);

                return ResponseEntity.ok(nuevoDepartamento);
            } else {
                // Si no se encuentra la entidad PersonalEntity, devuelve una respuesta de error
                return ResponseEntity.badRequest().body("No se encontró ningún empleado con el ID proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            // Manejar otras excepciones, si es necesario
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getDepartamentoById(@PathVariable Integer id) {
        DepartamentoDTO departamentoDTO = departamentoService.getById(id);
        if (departamentoDTO != null) {
            return ResponseEntity.ok(departamentoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/put")
    public ResponseEntity<?> updateDepartamentoById(@RequestBody DepartamentoDTO request) {
        try {
            // Obtener el departamento a actualizar usando su ID
            Optional<DepartamentoDTO> optionalDepartamento = Optional.ofNullable(departamentoService.getById(request.getId()));

            if (optionalDepartamento.isPresent()) {
                DepartamentoDTO departamentoDTO = optionalDepartamento.get();

                // Obtener la entidad PersonalEntity correspondiente al ID del jefe del departamento
                Optional<PersonalEntity> optionalPersonal = personalService.getById(request.getJefeDepartamentoId());

                if (optionalPersonal.isPresent()) {
                    PersonalEntity jefeDepartamento = optionalPersonal.get();

                    // Actualizar los campos del departamento
                    departamentoDTO.setCod(request.getCod());
                    departamentoDTO.setNombre(request.getNombre());
                    departamentoDTO.setActivo(request.getActivo());
                    departamentoDTO.setJefeDepartamentoId(request.getJefeDepartamentoId());

                    // Convertir DepartamentoDTO a DepartamentosEntity
                    DepartamentosEntity departamentoEntity = convertToEntity(departamentoDTO);

                    // Guardar el departamento actualizado en la base de datos
                    departamentoService.saveDepartamento(departamentoEntity);

                    return ResponseEntity.ok(departamentoEntity); // Retorna la entidad actualizada
                } else {
                    // Manejar el caso en que el jefe del departamento no se encuentra
                    throw new IllegalArgumentException("No se encontró ningún empleado con el ID proporcionado.");
                }
            } else {
                // Manejar el caso en que el departamento no se encuentra
                throw new IllegalArgumentException("No se encontró ningún departamento con el ID proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            // Manejar excepciones
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/del/{id}")
    public ResponseEntity<String> deleteDepartamentoById(@PathVariable("id") Integer id) {
        boolean ok = departamentoService.deleteDepartamento(id);
        if (ok) {
            return ResponseEntity.ok("Departamento con id " + id + " borrado.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    // Método para convertir un DepartamentoDTO en una entidad DepartamentosEntity
    private DepartamentosEntity convertToEntity(DepartamentoDTO departamentoDTO) {
        DepartamentosEntity departamentoEntity = new DepartamentosEntity();
        departamentoEntity.setId(departamentoDTO.getId());
        departamentoEntity.setCod(departamentoDTO.getCod());
        departamentoEntity.setNombre(departamentoDTO.getNombre());
        departamentoEntity.setActivo(departamentoDTO.getActivo());

        // Obtener el jefe del departamento usando el ID proporcionado en el DTO
        Optional<PersonalEntity> optionalPersonal = personalService.getById(departamentoDTO.getJefeDepartamentoId());
        if (optionalPersonal.isPresent()) {
            // Si se encuentra el jefe del departamento, asignarlo a departamentoEntity
            departamentoEntity.setJefedepId(optionalPersonal.get());
        } else {
            // Manejar el caso en que el jefe del departamento no se encuentra
            // Lanzar una excepción indicando que no se encontró ningún empleado con el ID proporcionado
            throw new IllegalArgumentException("No se encontró ningún empleado con el ID proporcionado.");
        }

        return departamentoEntity;
    }
}