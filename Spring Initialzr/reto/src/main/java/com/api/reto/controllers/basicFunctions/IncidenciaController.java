package com.api.reto.controllers.basicFunctions;

import com.api.reto.models.IncidenciasEntity;
import com.api.reto.services.basics.IncidenciaService;
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

    @PostMapping(path = "/post")
    public IncidenciasEntity saveIncidencia(@RequestBody IncidenciasEntity incidencia) {
        return this.incidenciaService.saveIncidencia(incidencia);
    }

    @GetMapping("/{id}")
    public Optional<IncidenciasEntity> getIncidenciaById(@PathVariable Integer id) {
        return this.incidenciaService.getById(id);
    }

<<<<<<< HEAD
    @PutMapping("/put/{id}")
=======
    @PutMapping("/put{id}")
>>>>>>> e66289f6c4129b5717f58873deb75ab03ea278d9
    public IncidenciasEntity updateIncidenciaById(@RequestBody IncidenciasEntity incidencia, @PathVariable Integer id) {
        return this.incidenciaService.updateById(incidencia, id);
    }

<<<<<<< HEAD
    @DeleteMapping("/del/{id}")
=======
    @DeleteMapping("/del{id}")
>>>>>>> e66289f6c4129b5717f58873deb75ab03ea278d9
    public String deleteIncidencia(@PathVariable("id") Integer id) {
        boolean ok = incidenciaService.deleteIncidencia(id);
        if (ok) {
            return "Incidencia con id " + id + " borrada.";
        } else {
            return "Error, no se encuentra la incidencia con id " + id + ".";
        }
    }

}
