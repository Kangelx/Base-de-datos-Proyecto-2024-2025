package com.api.reto.controllers.FileBase64Controller;

import com.api.reto.dto.ArchivoDTO;
import com.api.reto.services.base64.FileBase64Service;
import com.api.reto.services.basics.ComentariosService;
import com.api.reto.services.basics.IncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/adjuntos")
public class FileBase64Controller {

    @Autowired
    private FileBase64Service servicioArchivo;

    @Autowired
    private ComentariosService comentariosService;

    @Autowired
    private IncidenciaService incidenciaService;
    @PostMapping("/subir/{tipo}/{id}")
    public ResponseEntity<String> subirArchivo(@PathVariable String tipo,
                                               @PathVariable Integer id,
                                               @RequestBody ArchivoDTO archivoDTO) {
        try {
            String rutaArchivo = servicioArchivo.guardarArchivo(archivoDTO.getContenidoBase64(), archivoDTO.getNombreArchivo());
            String urlArchivo = servicioArchivo.construirUrlArchivo(rutaArchivo);

            // Actualizar URL del adjunto en la tabla correspondiente
            if ("comentario".equals(tipo)) {
                comentariosService.actualizarUrlAdjuntoComentario(id, urlArchivo);
            } else if ("incidencia".equals(tipo)) {
                incidenciaService.actualizarUrlAdjuntoIncidencia(id, urlArchivo);
            } else {
                return ResponseEntity.badRequest().body("Tipo no válido. Debe ser 'comentario' o 'incidencia'.");
            }

            return ResponseEntity.ok("Archivo subido con éxito y URL actualizada en " + tipo);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al guardar el archivo: " + e.getMessage());
        }
    }

    @GetMapping("/descargar/{nombre}")
    public ResponseEntity<String> descargarArchivo(@PathVariable String nombre) {
        try {
            String contenidoBase64 = servicioArchivo.cargarArchivoComoBase64(nombre);
            return ResponseEntity.ok(contenidoBase64);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error al cargar el archivo: " + e.getMessage());
        }
    }
}