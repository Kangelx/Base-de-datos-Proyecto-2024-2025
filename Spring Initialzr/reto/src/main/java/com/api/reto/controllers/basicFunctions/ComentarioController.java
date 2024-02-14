package com.api.reto.controllers.basicFunctions;

import com.api.reto.dto.ComentarioDTO;
import com.api.reto.models.ComentariosEntity;
import com.api.reto.models.IncidenciasEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.services.basics.ComentariosService;
import com.api.reto.services.basics.IncidenciaService;
import com.api.reto.services.basics.PerfilService;
import com.api.reto.services.basics.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/comentario") // Endpoint para comentarios
public class ComentarioController {

    @Autowired
    private ComentariosService comentarioService;
    @Autowired
    PersonalService personalService;
    @Autowired
    IncidenciaService incidenciaService;

    @GetMapping
    public ArrayList<ComentariosEntity> getComentarios() {
        return this.comentarioService.getComentarios();
    }

    @PostMapping("")
    public ComentariosEntity saveComentario(@RequestBody ComentarioDTO comentarioDTO) {

        Optional<PersonalEntity> optionalPersonaQueComenta = personalService.getById(comentarioDTO.getPersonalId());
        if (optionalPersonaQueComenta.isPresent()) {
            PersonalEntity personaQueComenta = optionalPersonaQueComenta.get();


            Optional<IncidenciasEntity> optionalIncidenciaComentada = incidenciaService.getById(comentarioDTO.getIncidenciaNum());
            if (optionalIncidenciaComentada.isPresent()) {
                IncidenciasEntity incidenciaComentada = optionalIncidenciaComentada.get();

                // Crear un nuevo comentario
                ComentariosEntity comentario = new ComentariosEntity();
                comentario.setTexto(comentarioDTO.getTexto());
                comentario.setFechahora(comentarioDTO.getFechahora());
                comentario.setAdjuntoUrl(comentarioDTO.getAdjuntoUrl());
                comentario.setIncidenciaNum(incidenciaComentada);
                comentario.setPersonalId(personaQueComenta);


                return comentarioService.saveComentario(comentario);
            } else {

                throw new IllegalArgumentException("La incidencia comentada no existe.");
            }
        } else {

            throw new IllegalArgumentException("La persona que comenta no existe.");
        }
    }

    @GetMapping("/{id}")
    public Optional<ComentariosEntity> getComentarioById(@PathVariable Integer id) {
        return this.comentarioService.getById(id);
    }

    @PutMapping("")
    public ResponseEntity<?> updateComentarioById(@RequestBody ComentarioDTO request) {
        try {

            Integer id = request.getId();


            if (id != null) {

                Optional<ComentariosEntity> optionalComentario = comentarioService.getById(id);

                if (optionalComentario.isPresent()) {
                    ComentariosEntity comentario = optionalComentario.get();


                    Optional<PersonalEntity> personaQueComenta = personalService.getById(request.getPersonalId());
                    Optional<IncidenciasEntity> incidenciaComentada = incidenciaService.getById(request.getIncidenciaNum());

                    if (personaQueComenta.isPresent() && incidenciaComentada.isPresent()) {
                        PersonalEntity persona = personaQueComenta.get();
                        IncidenciasEntity incidencia = incidenciaComentada.get();


                        comentario.setTexto(request.getTexto());
                        comentario.setFechahora(request.getFechahora());
                        comentario.setAdjuntoUrl(request.getAdjuntoUrl());
                        comentario.setPersonalId(persona);
                        comentario.setIncidenciaNum(incidencia);


                        comentarioService.saveComentario(comentario);

                        return ResponseEntity.ok(comentario);
                    } else {

                        throw new IllegalArgumentException("La persona o la incidencia especificadas no existen.");
                    }
                } else {

                    return ResponseEntity.notFound().build();
                }
            } else {

                throw new IllegalArgumentException("El ID del comentario no puede ser nulo.");
            }
        } catch (IllegalArgumentException e) {

            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public String deleteComentarioById(@PathVariable("id") Integer id) {
        boolean ok = this.comentarioService.deleteComentario(id);
        if (ok) {
            return "Comentario con ID " + id + " borrado.";
        } else {
            return "Error, no se encuentra el comentario con ID " + id + ".";
        }
    }
}