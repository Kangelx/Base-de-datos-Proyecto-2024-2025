package com.api.reto.controllers.excelGeneration;

import com.api.reto.services.excelGeneration.EstadisticasIncidenciasExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excel")
public class EstadisticasIncidenciasExcellController {
    @Autowired
    private EstadisticasIncidenciasExcelService estadisticasIncidenciasExcelService;

    @GetMapping("/estadisticasTipos")
    public ResponseEntity<String> generarExcelEstadisticasIncidencias() {
        try {
            String filePath = estadisticasIncidenciasExcelService.generarExcelEstadisticasIncidencias();
            return ResponseEntity.ok(filePath);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al generar el archivo de estadísticas: " + e.getMessage());
        }
    }
}
