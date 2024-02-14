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
        // Obtener la persona que comenta
        Optional<PersonalEntity> optionalPersonaQueComenta = personalService.getById(comentarioDTO.getPersonalId());
        if (optionalPersonaQueComenta.isPresent()) {
            PersonalEntity personaQueComenta = optionalPersonaQueComenta.get();

            // Obtener la incidencia comentada
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

                // Guardar y retornar el comentario
                return comentarioService.saveComentario(comentario);
            } else {
                // Manejar el caso en que la incidencia comentada no existe
                throw new IllegalArgumentException("La incidencia comentada no existe.");
            }
        } else {
            // Manejar el caso en que la persona que comenta no existe
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
            // Obtener el ID del comentario del DTO
            Integer id = request.getId();

            // Verificar si el ID es válido
            if (id != null) {
                // Obtener el comentario a actualizar usando su ID
                Optional<ComentariosEntity> optionalComentario = comentarioService.getById(id);

                if (optionalComentario.isPresent()) {
                    ComentariosEntity comentario = optionalComentario.get();

                    // Convertir el DTO de comentario a entidad de comentario
                    Optional<PersonalEntity> personaQueComenta = personalService.getById(request.getPersonalId());
                    Optional<IncidenciasEntity> incidenciaComentada = incidenciaService.getById(request.getIncidenciaNum());

                    if (personaQueComenta.isPresent() && incidenciaComentada.isPresent()) {
                        PersonalEntity persona = personaQueComenta.get();
                        IncidenciasEntity incidencia = incidenciaComentada.get();

                        // Actualizar los campos del comentario
                        comentario.setTexto(request.getTexto());
                        comentario.setFechahora(request.getFechahora());
                        comentario.setAdjuntoUrl(request.getAdjuntoUrl());
                        comentario.setPersonalId(persona);
                        comentario.setIncidenciaNum(incidencia);

                        // Guardar el comentario actualizado en la base de datos
                        comentarioService.saveComentario(comentario);

                        return ResponseEntity.ok(comentario); // Retorna el comentario actualizado
                    } else {
                        // Manejar el caso en que la persona o la incidencia no existen
                        throw new IllegalArgumentException("La persona o la incidencia especificadas no existen.");
                    }
                } else {
                    // Manejar el caso en que el comentario no se encuentra
                    return ResponseEntity.notFound().build();
                }
            } else {
                // Manejar el caso en que el ID es nulo
                throw new IllegalArgumentException("El ID del comentario no puede ser nulo.");
            }
        } catch (IllegalArgumentException e) {
            // Manejar excepciones
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