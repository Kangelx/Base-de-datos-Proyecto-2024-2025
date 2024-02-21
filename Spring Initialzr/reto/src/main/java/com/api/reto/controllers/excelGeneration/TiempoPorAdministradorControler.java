package com.api.reto.controllers.excelGeneration;
import com.api.reto.services.excelGeneration.TiempoPorAdministradorExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/excel")
public class TiempoPorAdministradorControler {

    @Autowired
    private TiempoPorAdministradorExcelService tiempoPorAdministradorExcelService;

    @GetMapping("/tiempoPorAdministrador")
    public ResponseEntity<?> generarReporteTiempoPorAdministrador() {
        try {
            // Llama al servicio para generar el archivo Excel
            String filePath = tiempoPorAdministradorExcelService.generarExcelTiempoPorAdministrador();

            // Carga el archivo como un recurso
            Path path = Paths.get(filePath);
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

            // Construye la respuesta con el archivo para descargar
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName().toString())
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(resource);
        } catch (Exception e) {
            // Manejo de errores
            return new ResponseEntity<>("Error al generar el reporte: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
