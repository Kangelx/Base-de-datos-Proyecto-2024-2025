package com.api.reto.controllers;


import com.api.reto.models.EquiposEntity;
import com.api.reto.services.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/equipo") //End point para buscar por equipo
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public ArrayList<EquiposEntity> getEquipos() {
        return this.equipoService.getEquipos();
    }

    @PostMapping(path = "/post")
    public EquiposEntity saveEquipo(@RequestBody EquiposEntity equipo) {
        return this.equipoService.saveEquipo(equipo);
    }

    @GetMapping(path = "/{id}")
    public Optional<EquiposEntity> getEquipoById(@PathVariable Integer id) {
        return this.equipoService.getById(id);
    }

    @PutMapping("/put{id}")
    public EquiposEntity updateEquipoById(@RequestBody EquiposEntity request, @PathVariable Integer id) {
        return this.equipoService.updateById(request, id);
    }

    @DeleteMapping(path = "/del{id}")

    public String deleteById(@PathVariable("id") Integer id) {
        boolean ok = this.equipoService.deleteEquipo(id);
        if (ok) {
            return "Equipo con id " + id + " borrado.";
        } else {
            return "Error, no se encuentra el equipo con id " + id + ".";
        }
    }
}
