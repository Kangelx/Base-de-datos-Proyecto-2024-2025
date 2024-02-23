package com.api.reto.controllers.FileBase64Controller;

import com.api.reto.services.base64.FileBase64Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/adjuntos")
public class FileBase64Controller {

    @Autowired
    private FileBase64Service servicioArchivo;

    @PostMapping("/subir")
    public ResponseEntity<String> subirArchivo(@RequestBody String nombre, String nombreArchivo, @RequestBody String contenidoBase64) {
        try {
            String rutaArchivo = servicioArchivo.guardarArchivo(contenidoBase64, nombreArchivo);
            return ResponseEntity.ok("Archivo guardado en: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error guardando el archivo");
        }
    }

    @GetMapping("/descargar/{nombre}")
    public ResponseEntity<String> descargarArchivo(@PathVariable String nombre) {
        try {
            String contenidoBase64 = servicioArchivo.cargarArchivoComoBase64(nombre);
            return ResponseEntity.ok(contenidoBase64);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error cargando el archivo");
        }
    }
}