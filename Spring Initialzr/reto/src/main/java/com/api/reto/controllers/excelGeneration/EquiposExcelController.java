package com.api.reto.controllers.excelGeneration;

import com.api.reto.models.EquiposEntity;
import com.api.reto.services.basics.EquipoService;
import com.api.reto.services.excelGeneration.EquiposExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/excel")
public class EquiposExcelController {
    @Autowired
    private EquiposExcelService excelService;
    @Autowired
    private EquipoService equipoService;

    @GetMapping("/equipos")
    public String generateExcel() {
        try {
            ArrayList<EquiposEntity> equipos = equipoService.getEquipos();
            excelService.generateExcel(equipos);
            return "Documento Excel generado satisfactoriamente.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al generar el documento Excel.";
        }
    }
}

