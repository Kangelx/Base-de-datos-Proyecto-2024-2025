package com.api.reto.controllers.basicFunctions;

import com.api.reto.dto.DepartamentoDTO;
import com.api.reto.models.DepartamentosEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.services.basics.DepartamentoService;
import com.api.reto.services.basics.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('administrador')")
    public ArrayList<DepartamentoDTO> getDepartamentos() {
        return departamentoService.getDepartamentos();
    }

    @PostMapping(path = "")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<?> saveDepartamento(@RequestBody DepartamentoDTO departamentoDTO) {
        try {
            // Verificar si se proporcionó el ID del jefe de departamento y si es diferente de null
            Integer jefeDepId = departamentoDTO.getJefeDepartamentoId();
            if (jefeDepId != null) {
                // Obtener el jefe de departamento usando su ID
                Optional<PersonalEntity> optionalPersonal = personalService.getById(jefeDepId);
                if (!optionalPersonal.isPresent()) {
                    return ResponseEntity.badRequest().body("No se encontró ningún empleado con el ID proporcionado.");
                }
            }

            DepartamentosEntity departamentoEntity = new DepartamentosEntity();
            departamentoEntity.setCod(departamentoDTO.getCod());
            departamentoEntity.setNombre(departamentoDTO.getNombre());
            departamentoEntity.setActivo(departamentoDTO.getActivo());

            if (jefeDepId != null) {

                PersonalEntity jefeDepartamento = personalService.getById(jefeDepId).get();
                departamentoEntity.setJefedepId(jefeDepartamento);
            } else {

                departamentoEntity.setJefedepId(null);
            }


            DepartamentoDTO nuevoDepartamento = departamentoService.saveDepartamento(departamentoEntity);
            return ResponseEntity.ok(nuevoDepartamento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<?> getDepartamentoById(@PathVariable Integer id) {
        DepartamentoDTO departamentoDTO = departamentoService.getById(id);
        if (departamentoDTO != null) {
            return ResponseEntity.ok(departamentoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<?> updateDepartamentoById(@RequestBody DepartamentoDTO request) {
        DepartamentosEntity depEnt = new DepartamentosEntity();

        try {
            Integer departamentoId = request.getJefeDepartamentoId();
            depEnt.setNombre(request.getNombre());
            depEnt.setActivo(request.getActivo());
            depEnt.setCod(request.getCod());
            depEnt.setId(request.getId());

            if (departamentoId != null) {

                Optional<PersonalEntity> optionalPersona = personalService.getById(departamentoId);
                if (optionalPersona.isPresent()) {
                    PersonalEntity personalEntity = optionalPersona.get();
                    depEnt.setJefedepId(personalEntity);
                } else {
                    throw new IllegalArgumentException("No se encontró ningún empleado con el ID proporcionado.");
                }
            } else {
                depEnt.setJefedepId(null);
            }


            if (departamentoService.getById(request.getId()) != null) {
                departamentoService.saveDepartamento(depEnt);
                return ResponseEntity.ok(depEnt);
            } else {
                throw new IllegalArgumentException("No se encontró ningún departamento con el ID proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<String> deleteDepartamentoById(@PathVariable("id") Integer id) {
        boolean ok = departamentoService.deleteDepartamento(id);
        if (ok) {
            return ResponseEntity.ok("Departamento con id " + id + " borrado.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private DepartamentosEntity convertToEntity(DepartamentoDTO departamentoDTO) {
        DepartamentosEntity departamentoEntity = new DepartamentosEntity();
        departamentoEntity.setId(departamentoDTO.getId());
        departamentoEntity.setCod(departamentoDTO.getCod());
        departamentoEntity.setNombre(departamentoDTO.getNombre());
        departamentoEntity.setActivo(departamentoDTO.getActivo());


        Optional<PersonalEntity> optionalPersonal = personalService.getById(departamentoDTO.getJefeDepartamentoId());
        if (optionalPersonal.isPresent()) {

            departamentoEntity.setJefedepId(optionalPersonal.get());
        } else {

            throw new IllegalArgumentException("No se encontró ningún empleado con el ID proporcionado.");
        }

        return departamentoEntity;
    }
}