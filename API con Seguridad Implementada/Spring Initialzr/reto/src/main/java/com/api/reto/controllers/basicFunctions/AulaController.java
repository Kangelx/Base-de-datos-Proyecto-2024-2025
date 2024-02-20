package com.api.reto.controllers.basicFunctions;


import com.api.reto.models.AulasEntity;
import com.api.reto.services.basics.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/aula") // End point para buscar por aulas

public class AulaController {

    @Autowired
    private AulaService aulaService;

    @GetMapping
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<ArrayList<AulasEntity>> getAulas() {
        ArrayList<AulasEntity> aulas = this.aulaService.getAulas();
        return ResponseEntity.ok(aulas);
    }

    @PostMapping(path = "")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<AulasEntity> saveAula(@RequestBody AulasEntity aula) {
        Optional<AulasEntity> existingAulaOptional = this.aulaService.getById(aula.getNum());
        if (existingAulaOptional.isPresent()) {
            // El ID ya existe, lo que significa que se está intentando modificar una fila existente
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        try {
            AulasEntity savedAula = this.aulaService.saveAula(aula);
            return ResponseEntity.ok(savedAula);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<AulasEntity> getAulaById(@PathVariable Integer id) {
        Optional<AulasEntity> aulaOptional = this.aulaService.getById(id);
        if (aulaOptional.isPresent()) {
            return ResponseEntity.ok(aulaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<AulasEntity> updateAulaById(@RequestBody AulasEntity request) {
        Integer id = request.getNum();
        if (id == null) {

            return ResponseEntity.badRequest().build();
        }

        Optional<AulasEntity> existingAulaOptional = this.aulaService.getById(id);
        if (!existingAulaOptional.isPresent()) {

            return ResponseEntity.notFound().build();
        }

        try {
            AulasEntity updatedAula = this.aulaService.updateById(request, id);
            return ResponseEntity.ok(updatedAula);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) {
        boolean ok = this.aulaService.deleteAula(id);
        if (ok) {
            return ResponseEntity.ok("Aula con id " + id + " borrado.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, no se encuentra el aula con id " + id + ".");
        }
    }
}