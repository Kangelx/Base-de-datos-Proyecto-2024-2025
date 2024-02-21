package com.api.reto.controllers.excelGeneration;


import com.api.reto.services.excelGeneration.IncidenciasResueltasExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/excel")
public class IncidenciasResueltasExcelController {

    @Autowired
    private IncidenciasResueltasExcelService incidenciasResueltasService;

    @GetMapping("/incidenciasResueltas")
    @PreAuthorize("hasAuthority('administrador')")
    public ResponseEntity<Resource> exportarIncidenciasResueltas() {
        try {
            String rutaArchivo = incidenciasResueltasService.generarArchivoExcelYGuardar();
            Path path = Paths.get(rutaArchivo);
            Resource resource = new InputStreamResource(Files.newInputStream(path));

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString());
            headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
            headers.add(HttpHeaders.PRAGMA, "no-cache");
            headers.add(HttpHeaders.EXPIRES, "0");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(Files.size(path))
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}