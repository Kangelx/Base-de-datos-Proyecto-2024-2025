package com.api.reto.controllers.excelGeneration;


import com.api.reto.services.excelGeneration.TiempoPorTipoExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excel")
public class TiempoPorTipoExcelController {

    @Autowired
    private TiempoPorTipoExcelService tiempoPorTipoExcelService;

    @GetMapping("/tiempoTipo")
    public ResponseEntity<String> generarExcelTiempoPorTipo() {
        try {
            String filePath = tiempoPorTipoExcelService.generarExcelTiempoPorTipo();
            return ResponseEntity.ok(filePath);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al generar el archivo de tiempo por tipo: " + e.getMessage());
        }
    }
}