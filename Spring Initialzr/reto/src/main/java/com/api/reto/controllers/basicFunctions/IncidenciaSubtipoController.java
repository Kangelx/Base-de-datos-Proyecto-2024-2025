package com.api.reto.controllers.basicFunctions;

import com.api.reto.models.IncidenciasSubtiposEntity;
import com.api.reto.services.basics.IncidenciaSubtiposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/incidenciasubtipo")
public class IncidenciaSubtipoController {
    @Autowired
    private IncidenciaSubtiposService incidenciaSubtiposService;

    @GetMapping
    public ArrayList<IncidenciasSubtiposEntity> getIncidenciaSubtipo() {
        return this.incidenciaSubtiposService.getIncidenciasSubtipos();
    }

    @PostMapping(path = "/post")
    public IncidenciasSubtiposEntity saveIncidenciaSubtipo(@RequestBody IncidenciasSubtiposEntity incidenciaSubtipo) {
        return this.incidenciaSubtiposService.saveIncidenciaSubtipo(incidenciaSubtipo);
    }

    @GetMapping(path = "/{id}")
    public Optional<IncidenciasSubtiposEntity> getIncidenciaSubtipoById(@PathVariable Integer id) {
        return this.incidenciaSubtiposService.getById(id);
    }

<<<<<<< HEAD
    @PutMapping("/put/{id}")
=======
    @PutMapping("/put{id}")
>>>>>>> e66289f6c4129b5717f58873deb75ab03ea278d9
    public IncidenciasSubtiposEntity updateIncidenciaSubtipoById(@RequestBody IncidenciasSubtiposEntity request, @PathVariable Integer id) {
        return this.incidenciaSubtiposService.updateById(request, id);
    }

<<<<<<< HEAD
    @DeleteMapping(path = "/del/{id}")
=======
    @DeleteMapping(path = "/del{id}")
>>>>>>> e66289f6c4129b5717f58873deb75ab03ea278d9

    public String deleteById(@PathVariable("id") Integer id) {
        boolean ok = this.incidenciaSubtiposService.deleteIncidenciaSubtipo(id);
        if (ok) {
            return "Aula con id " + id + " borrado.";
        } else {
            return "Error, no se encuentra el aula con id " + id + ".";
        }
    }
}
