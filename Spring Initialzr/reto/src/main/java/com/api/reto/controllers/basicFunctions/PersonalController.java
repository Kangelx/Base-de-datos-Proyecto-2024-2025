package com.api.reto.controllers.basicFunctions;

import com.api.reto.models.PersonalEntity;
import com.api.reto.services.basics.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping
    public ArrayList<PersonalEntity> getPersonal() {
        return personalService.getPersonal();
    }

    @PostMapping("/post")
    public PersonalEntity savePersona(@RequestBody PersonalEntity persona) {
        return personalService.savePersonal(persona);
    }

    @GetMapping("/{id}")
    public Optional <PersonalEntity> getPersonaById(@PathVariable Integer id) {
        return this.personalService.getById(id);
    }

    @PutMapping("/put/{id}")
    public PersonalEntity updatePersonalById(@RequestBody PersonalEntity request, @PathVariable Integer id) {

        return this.personalService.updateById(request,id);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePersonalById(@PathVariable Integer id) {
        boolean deleted = personalService.deletePersonal(id);
        if (deleted) {
            return "Persona con id " + id + " borrada.";
        } else {
            return "Error, no se encuentra la persona con id " + id + ".";
        }
    }
}