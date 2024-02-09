package com.api.reto.controllers;

import com.api.reto.models.AulasEntity;
import com.api.reto.models.ComentariosEntity;
import com.api.reto.repositories.IComentarioRepository;
import com.api.reto.services.ComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/comentario") // Endpoint para comentarios
public class ComentarioController {

    @Autowired
    private ComentariosService comentarioService;

    @GetMapping
    public ArrayList<ComentariosEntity> getComentarios() {
        return this.comentarioService.getComentarios();
    }

    @PostMapping("/post")
    public ComentariosEntity saveComentario(@RequestBody ComentariosEntity comentario) {
        return this.comentarioService.saveComentario(comentario);
    }

    @GetMapping("/{id}")
    public Optional<ComentariosEntity> getComentarioById(@PathVariable Integer id) {
        return this.comentarioService.getById(id);
    }

    @PutMapping("/put/{id}")
    public ComentariosEntity updateComentarioById(@RequestBody ComentariosEntity request, @PathVariable Integer id) {
        return this.comentarioService.updateById(request, id);
    }

    @DeleteMapping("/del/{id}")
    public String deleteComentarioById(@PathVariable("id") Integer id) {
        boolean ok = this.comentarioService.deleteComentario(id);
        if (ok) {
            return "Comentario con ID " + id + " borrado.";
        } else {
            return "Error, no se encuentra el comentario con ID " + id + ".";
        }
    }
}