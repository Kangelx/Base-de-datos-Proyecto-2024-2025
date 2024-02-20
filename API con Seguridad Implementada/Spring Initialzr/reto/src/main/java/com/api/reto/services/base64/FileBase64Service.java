package com.api.reto.services.base64;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
public class FileBase64Service {

    @Value("${directorio.subida}")
    private String directorioSubida;

    public String guardarArchivo(String contenidoBase64, String nombreArchivo) throws IOException {
        // Decodificar el contenido Base64 a bytes
        byte[] bytesDecodificados = Base64.getDecoder().decode(contenidoBase64);
        // Crear el camino del archivo destino en el sistema de archivos
        Path archivoDestino = Paths.get(directorioSubida).resolve(nombreArchivo).normalize().toAbsolutePath();
        // Escribir los bytes decodificados en el archivo destino
        Files.write(archivoDestino, bytesDecodificados);
        // Devolver la ruta absoluta del archivo guardado
        return archivoDestino.toString();
    }

    public String cargarArchivoComoBase64(String nombreArchivo) throws IOException {
        // Construir el camino hacia el archivo basado en el directorio de subida y el nombre del archivo
        Path caminoArchivo = Paths.get(directorioSubida).resolve(nombreArchivo).normalize().toAbsolutePath();
        // Leer el contenido del archivo en un arreglo de bytes
        byte[] contenidoArchivo = FileUtils.readFileToByteArray(caminoArchivo.toFile());
        // Codificar el contenido del archivo a una cadena Base64 y devolverla
        return Base64.getEncoder().encodeToString(contenidoArchivo);
    }
}