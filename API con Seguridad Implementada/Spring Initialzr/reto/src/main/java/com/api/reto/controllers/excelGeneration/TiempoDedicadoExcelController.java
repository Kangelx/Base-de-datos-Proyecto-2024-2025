package com.api.reto.controllers.excelGeneration;

import com.api.reto.services.excelGeneration.TiempoDedicadoExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excel")
public class TiempoDedicadoExcelController {

    @Autowired
    private TiempoDedicadoExcelService tiempoDedicadoExcelService;

    @GetMapping("/incidenciasTiempo")
    public ResponseEntity<String> generarExcelTiempoDedicado() {
        try {
            String filePath = tiempoDedicadoExcelService.generarExcelTiempoDedicado();
            return ResponseEntity.ok(filePath);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al generar el archivo de tiempo dedicado: " + e.getMessage());
        }
    }
}