package com.api.reto.controllers.basicFunctions;

import com.api.reto.models.PerfilesEntity;
import com.api.reto.services.basics.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/perfil") // End point para operaciones relacionadas con perfiles
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public ArrayList<PerfilesEntity> getPerfiles() {
        return perfilService.getPerfiles();
    }

    @PostMapping(path = "/post")
    public PerfilesEntity savePerfil(@RequestBody PerfilesEntity perfil) {
        return perfilService.savePerfil(perfil);
    }

    @GetMapping(path = "/{id}")
    public Optional<PerfilesEntity> getPerfilById(@PathVariable Integer id) {
        return perfilService.getById(id);
    }

    @PutMapping("/{id}")
    public PerfilesEntity updatePerfilById(@RequestBody PerfilesEntity request, @PathVariable Integer id) {
        return perfilService.updateById(request, id);
    }

    @DeleteMapping("/{id}")
    public String deletePerfilById(@PathVariable("id") Integer id) {
        boolean deleted = perfilService.deletePerfil(id);
        if (deleted) {
            return "Perfil con ID " + id + " borrado correctamente.";
        } else {
            return "Error al borrar el perfil con ID " + id + ".";
        }
    }
}