package com.api.reto.services;

import com.api.reto.models.AulasEntity;
import com.api.reto.repositories.IAulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AulaService {
    @Autowired
    IAulaRepository aulaRepository;

<<<<<<< HEAD
    public ArrayList<AulasEntity> getAulas() {
=======
<<<<<<< HEAD
    public ArrayList<AulasEntity> getAulas() {
        return (ArrayList<AulasEntity>) aulaRepository.findAll();
    }

    public AulasEntity saveAula(AulasEntity aula) {
        return aulaRepository.save(aula);
    }

    public Optional<AulasEntity> getById(Integer id) {
        return aulaRepository.findById(id);
    }

    public AulasEntity updateById(AulasEntity request, Integer id) {
        AulasEntity aula = aulaRepository.findById(id).get();
=======
    public ArrayList<AulasEntity>getAulas(){
>>>>>>> c0f9bb5a87bba794458de9efb89eda964d78be69
        return (ArrayList<AulasEntity>) aulaRepository.findAll();
    }

    public AulasEntity saveAula(AulasEntity aula) {
        return aulaRepository.save(aula);
    }

    public Optional<AulasEntity> getById(Integer id) {
        return aulaRepository.findById(id);
    }
<<<<<<< HEAD

    public AulasEntity updateById(AulasEntity request, Integer id) {
        AulasEntity aula = aulaRepository.findById(id).get();
=======
    public AulasEntity updateById(AulasEntity request,Integer id){
        AulasEntity aula=aulaRepository.findById(id).get();
>>>>>>> ed18ab7994a95d3e9d80eb4f272c384927257e39
>>>>>>> c0f9bb5a87bba794458de9efb89eda964d78be69
        aula.setDescripcion(request.getDescripcion());
        aula.setNum(request.getNum());
        aula.setCodigo(request.getCodigo());
        aula.setPlanta(request.getPlanta());
        aulaRepository.save(aula);
        return aula;
    }
<<<<<<< HEAD

    public Boolean deleteAula(Integer id) {
        try {
            aulaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
=======
<<<<<<< HEAD

    public Boolean deleteAula(Integer id) {
        try {
            aulaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
=======
    public Boolean deleteAula(Integer id){
        try{
            aulaRepository.deleteById(id);
            return true;
        }catch (Exception e){
>>>>>>> ed18ab7994a95d3e9d80eb4f272c384927257e39
>>>>>>> c0f9bb5a87bba794458de9efb89eda964d78be69
            return false;
        }
    }

}
