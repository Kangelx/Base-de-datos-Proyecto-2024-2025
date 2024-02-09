package com.api.reto.controllers;

import com.api.reto.models.IncidenciasEntity;
import com.api.reto.services.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/incidencia")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @GetMapping
    public ArrayList<IncidenciasEntity> getIncidencias() {
        return this.incidenciaService.getIncidencias();
    }

    @PostMapping
    public IncidenciasEntity saveIncidencia(@RequestBody IncidenciasEntity incidencia) {
        return this.incidenciaService.saveIncidencia(incidencia);
    }

    @GetMapping("/{id}")
    public Optional<IncidenciasEntity> getIncidenciaById(@PathVariable Integer id) {
        return this.incidenciaService.getById(id);
    }

    @PutMapping("/{id}")
    public IncidenciasEntity updateIncidenciaById(@RequestBody IncidenciasEntity incidencia, @PathVariable Integer id) {
        return this.incidenciaService.updateById(incidencia, id);
    }

    @DeleteMapping("/del{id}")
    public String deleteIncidencia(@PathVariable("id") Integer id) {
        boolean ok = incidenciaService.deleteIncidencia(id);
        if (ok) {
            return "Incidencia con id " + id + " borrada.";
        } else {
            return "Error, no se encuentra la incidencia con id " + id + ".";
        }
    }

}
