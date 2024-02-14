package com.api.reto.controllers.excelGeneration;

import com.api.reto.models.IncidenciasEntity;
import com.api.reto.services.excelGeneration.IncidenciasExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/excel")
public class IncidenciasExcelController {

    @Autowired
    private IncidenciasExcelService excelService;

    @GetMapping("/incidencias")
    public String generateExcel() {
        try {
            excelService.generateExcel();
            return "Documento Excel de Incidencias generado satisfactoriamente.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al generar el documento Excel de Incidencias.";
        }
    }
}