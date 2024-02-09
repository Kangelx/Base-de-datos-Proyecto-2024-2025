package com.api.reto.controllers;

import com.api.reto.models.DepartamentosEntity;
import com.api.reto.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public ArrayList<DepartamentosEntity> getDepartamentos() {
        return departamentoService.getDepartamentos();
    }

    @PostMapping(path = "/post")
    public DepartamentosEntity saveDepartamento(@RequestBody DepartamentosEntity departamento) {
        return departamentoService.saveDepartamento(departamento);
    }

    @GetMapping(path = "/{id}")
    public Optional<DepartamentosEntity> getDepartamentoById(@PathVariable Integer id) {
        return departamentoService.getById(id);
    }

    @PutMapping("/put/{id}")
    public DepartamentosEntity updateDepartamentoById(@RequestBody DepartamentosEntity request, @PathVariable Integer id) {
        return departamentoService.updateById(request, id);
    }

    @DeleteMapping(path = "/del/{id}")
    public String deleteDepartamentoById(@PathVariable("id") Integer id) {
        boolean ok = departamentoService.deleteDepartamento(id);
        if (ok) {
            return "Departamento con id " + id + " borrado.";
        } else {
            return "Error, no se encuentra el departamento con id " + id + ".";
        }

    }
}