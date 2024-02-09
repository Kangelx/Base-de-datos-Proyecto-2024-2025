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
    public ResponseEntity<?> saveDepartamento(@RequestBody DepartamentosEntity departamento,  Integer jefedep_id) {
        try {
            // Buscar la entidad PersonalEntity correspondiente al ID proporcionado
            Optional<PersonalEntity> optionalPersonal = personalService.getById(jefedep_id);
            if (optionalPersonal.isPresent()) {
                // Si se encuentra la entidad PersonalEntity, obténla y asígnala al departamento
                PersonalEntity jefeDepartamento = optionalPersonal.get();
                departamento.setJefedepId(jefeDepartamento);

                // Guardar el departamento
                DepartamentosEntity nuevoDepartamento = departamentoService.saveDepartamento(departamento);

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
        Optional<DepartamentosEntity> departamentoOptional = departamentoService.getById(id);
        if (departamentoOptional.isPresent()) {
            return ResponseEntity.ok(departamentoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

  /*  @PutMapping("/put/{id}")
    public ResponseEntity<?> updateDepartamentoById(@RequestBody DepartamentosEntity request, @PathVariable Integer id) {
        try {
            // Obtener la entidad PersonalEntity correspondiente al ID proporcionado
            Optional<PersonalEntity> optionalJefeDepartamento = personalService.getById(request.getJefedepId());
            if (optionalJefeDepartamento.isPresent()) {
                PersonalEntity jefeDepartamento = optionalJefeDepartamento.get();
                // Establecer la entidad PersonalEntity como jefe de departamento en la entidad DepartamentosEntity
                request.setJefedepId(jefeDepartamento);
                // Actualizar el departamento
                DepartamentosEntity departamentoActualizado = departamentoService.updateById(request, id);
                return ResponseEntity.ok(departamentoActualizado);
            } else {
                return ResponseEntity.badRequest().body("No se encontró ningún empleado con el ID proporcionado.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

    @DeleteMapping(path = "/del/{id}")
    public ResponseEntity<String> deleteDepartamentoById(@PathVariable("id") Integer id) {
        boolean ok = departamentoService.deleteDepartamento(id);
        if (ok) {
            return ResponseEntity.ok("Departamento con id " + id + " borrado.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}