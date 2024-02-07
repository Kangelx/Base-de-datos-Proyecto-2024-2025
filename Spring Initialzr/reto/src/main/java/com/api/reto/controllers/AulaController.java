package com.api.reto.controllers;


import com.api.reto.models.AulasEntity;
import com.api.reto.services.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/aula") //End point para buscar por aulas
public class AulaController {

    @Autowired
    private AulaService aulaService;

    @GetMapping
    public ArrayList<AulasEntity> getAulas() {
        return this.aulaService.getAulas();
    }

    @PostMapping
    public AulasEntity saveAula(@RequestBody AulasEntity aula) {
        return this.aulaService.saveAula(aula);
    }

    @GetMapping(path = "/{id}")
    public Optional<AulasEntity> getAulaById(@PathVariable Integer id) {
        return this.aulaService.getById(id);
    }

    @PutMapping("/{id}")
    public AulasEntity updateAulaById(@RequestBody AulasEntity request, @PathVariable Integer id) {
        return this.aulaService.updateById(request, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Integer id) {
        boolean ok = this.aulaService.deleteAula(id);
        if (ok) {
            return "Aula con id " + id + " borrado.";
        }else{
            return "Error, no se encuentra el aula con id "+id+".";
        }

    }
}
