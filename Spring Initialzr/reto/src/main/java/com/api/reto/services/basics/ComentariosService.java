package com.api.reto.services.basics;

import com.api.reto.models.AulasEntity;
import com.api.reto.models.ComentariosEntity;
import com.api.reto.repositories.IComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ComentariosService {
    @Autowired
    IComentarioRepository comentarioRepository;

    public ArrayList<ComentariosEntity> getComentarios() {
        return (ArrayList<ComentariosEntity>) comentarioRepository.findAll();
    }

    public ComentariosEntity saveComentario(ComentariosEntity comentario) {
        return comentarioRepository.save(comentario);
    }

    public Optional<ComentariosEntity> getById(Integer id) {
        return comentarioRepository.findById(id);
    }

    public ComentariosEntity updateById(ComentariosEntity request, Integer id) {
        ComentariosEntity comentario = comentarioRepository.findById(id).get();
        comentario.setId(request.getId());
        comentario.setTexto(request.getTexto());
        comentario.setFechahora(request.getFechahora());
        comentario.setIncidenciaNum(request.getIncidenciaNum());
        comentario.setPersonalId(request.getPersonalId());
        comentario.setAdjuntoUrl(request.getAdjuntoUrl());
        comentarioRepository.save(comentario);
        return comentario;
    }

    public Boolean deleteComentario(Integer id) {
        try {
            comentarioRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
