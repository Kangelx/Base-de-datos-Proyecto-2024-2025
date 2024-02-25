package com.api.reto.services.base64;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
public class FileBase64Service {

    @Value("${directorio.subida}")
    private String directorioSubida;
    @Value("${archivo.base-url}")
    private String baseUrl;
    public String guardarArchivo(String contenidoBase64, String nombreOriginal) throws IOException {
        // Decodificar el contenido Base64 a bytes
        byte[] bytesDecodificados = Base64.getDecoder().decode(contenidoBase64);


        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String marcaTemporal = ahora.format(formatter);


        String nombreArchivo = nombreOriginal + "_" + marcaTemporal;


        Path archivoDestino = Paths.get(directorioSubida).resolve(nombreArchivo).toAbsolutePath();


        if (!Files.exists(archivoDestino.getParent())) {
            Files.createDirectories(archivoDestino.getParent());
        }


        Files.write(archivoDestino, bytesDecodificados);


        return directorioSubida + "/" + nombreArchivo;
    }


    public String cargarArchivoComoBase64(String nombreArchivo) throws IOException {

        Path caminoArchivo = Paths.get(directorioSubida).resolve(nombreArchivo).normalize().toAbsolutePath();

        byte[] contenidoArchivo = FileUtils.readFileToByteArray(caminoArchivo.toFile());

        return Base64.getEncoder().encodeToString(contenidoArchivo);
    }

    public String construirUrlArchivo(String nombreArchivo) {

        return nombreArchivo;
    }
}