package com.api.reto.controllers.excelGeneration;

import com.api.reto.services.excelGeneration.AdministradorTiempoDedicadoExcelService;
import com.api.reto.services.excelGeneration.IncidenciasAbiertasExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excel")
public class AdministradorTiempoDedicadoExcelController {
    @Autowired
    private AdministradorTiempoDedicadoExcelService administradorTiempoDedicadoExcelService;

    @GetMapping("/listaIncidencias")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<String> generarReporteIncidencias() {
        try {
            String filePath = administradorTiempoDedicadoExcelService.generarExcelIncidencias();
            return ResponseEntity.ok(filePath);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al generar el archivo: " + e.getMessage());
        }
    }
}