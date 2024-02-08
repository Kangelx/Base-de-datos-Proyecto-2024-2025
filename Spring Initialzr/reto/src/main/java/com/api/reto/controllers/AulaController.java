package com.api.reto.controllers;


import com.api.reto.models.AulasEntity;
import com.api.reto.services.AulaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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

<<<<<<< HEAD
    @PostMapping(path = "/post")
=======
<<<<<<< HEAD
    @PostMapping(path = "/post")
=======
    @PostMapping
>>>>>>> ed18ab7994a95d3e9d80eb4f272c384927257e39
>>>>>>> c0f9bb5a87bba794458de9efb89eda964d78be69
    public AulasEntity saveAula(@RequestBody AulasEntity aula) {
        return this.aulaService.saveAula(aula);
    }

    @GetMapping(path = "/{id}")
    public Optional<AulasEntity> getAulaById(@PathVariable Integer id) {
        return this.aulaService.getById(id);
    }

<<<<<<< HEAD
    @PutMapping("/put{id}")
=======
<<<<<<< HEAD
    @PutMapping("/put{id}")
=======
    @PutMapping("/{id}")
>>>>>>> ed18ab7994a95d3e9d80eb4f272c384927257e39
>>>>>>> c0f9bb5a87bba794458de9efb89eda964d78be69
    public AulasEntity updateAulaById(@RequestBody AulasEntity request, @PathVariable Integer id) {
        return this.aulaService.updateById(request, id);
    }

    @DeleteMapping(path = "/del{id}")

    public String deleteById(@PathVariable("id") Integer id) {
        boolean ok = this.aulaService.deleteAula(id);
        if (ok) {
            return "Aula con id " + id + " borrado.";
        } else {
            return "Error, no se encuentra el aula con id " + id + ".";
        }
    }

}
