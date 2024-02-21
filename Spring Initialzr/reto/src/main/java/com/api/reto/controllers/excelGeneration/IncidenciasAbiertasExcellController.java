package com.api.reto.controllers.excelGeneration;

import com.api.reto.models.IncidenciasEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.services.basics.IncidenciaService;
import com.api.reto.services.excelGeneration.IncidenciasAbiertasExcelService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/excel")
public class IncidenciasAbiertasExcellController {

    @Autowired
    private IncidenciasAbiertasExcelService incidenciasAbiertasExcelService;

    @GetMapping("/incidenciasUsuarios")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<String> generarReporteIncidencias() {
        try {
            String filePath = incidenciasAbiertasExcelService.generarExcelIncidenciasPorUsuario();
            return ResponseEntity.ok(filePath);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al generar el archivo: " + e.getMessage());
        }
    }
}