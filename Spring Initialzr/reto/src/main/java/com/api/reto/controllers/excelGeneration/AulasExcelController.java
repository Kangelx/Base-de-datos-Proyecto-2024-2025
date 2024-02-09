package com.api.reto.controllers.excelGeneration;

import com.api.reto.services.excelGeneration.AulasExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excel")
public class AulasExcelController {

    @Autowired
    private AulasExcelService excelService;

    @GetMapping("/aulas")
    public ResponseEntity<String> generarExcelAulas() {
        try {
            excelService.generarArchivoExcel();
            return ResponseEntity.status(HttpStatus.OK).body("Archivo Excel generado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al generar el archivo Excel: " + e.getMessage());
        }
    }
}