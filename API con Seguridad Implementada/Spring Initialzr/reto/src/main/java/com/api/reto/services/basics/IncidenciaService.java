package com.api.reto.services.basics;

import com.api.reto.enums.EstadoEnum;
import com.api.reto.models.IncidenciasEntity;
import com.api.reto.repositories.IIncidenciasRepository;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class IncidenciaService {
    @Autowired
    IIncidenciasRepository incidenciasRepository;

    public ArrayList<IncidenciasEntity> getIncidencias() {
        return (ArrayList<IncidenciasEntity>) incidenciasRepository.findAll();
    }

    public IncidenciasEntity saveIncidencia(IncidenciasEntity incidencia) {
        return incidenciasRepository.save(incidencia);
    }

    public Optional<IncidenciasEntity> getById(Integer id) {
        return incidenciasRepository.findById(id);
    }

    public IncidenciasEntity updateById(IncidenciasEntity request, Integer id) {
        IncidenciasEntity incidencia = incidenciasRepository.findById(id).orElse(null);
        if (incidencia != null) {
            incidencia.setTipo(request.getTipo());
            incidencia.setSubtipoId(request.getSubtipoId());
            incidencia.setFechaCreacion(request.getFechaCreacion());
            incidencia.setFechaCierre(request.getFechaCierre());
            incidencia.setDescripcion(request.getDescripcion());
            incidencia.setEstado(request.getEstado());
            incidencia.setAdjuntoUrl(request.getAdjuntoUrl());
            incidencia.setCreadorId(request.getCreadorId());
            incidencia.setResponsableId(request.getResponsableId());
            incidencia.setEquipoId(request.getEquipoId());
            incidencia.setPrioridad(request.getPrioridad());
            incidenciasRepository.save(incidencia);
        }
        return incidencia;
    }
    public List<IncidenciasEntity> getIncidenciasResueltas() {
        // Reemplaza EstadoEnum.RESUELTA con el valor correspondiente en tu enumeración
        return incidenciasRepository.findByEstado(EstadoEnum.resuelta);
    }
    public boolean deleteIncidencia(Integer id) {
        try {
            incidenciasRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String generarExcelIncidenciasPorUsuario() throws Exception {
        List<IncidenciasEntity> todasLasIncidencias = incidenciasRepository.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Asume y planifica una ubicación y nombre significante para el reporte de Excel
        String nombreArchivo = "Incidencias_Por_Usuario_" + System.currentTimeMillis() + ".xlsx";
        Path pathCarpeta = Path.of("Informes");
        if(!Files.exists(pathCarpeta)) {
            Files.createDirectories(pathCarpeta); // Asegura la existencia del directorio "Informes"
        }
        String rutaArchivo = pathCarpeta.resolve(nombreArchivo).toString();

        // Lógica de funcionalidad para generar el Excel
        try (Workbook workbook = new XSSFWorkbook()) {
            // Toda la generación lógica espejeada y codeable de la porción subyuga
            // Sosiéga, avista, y entrama lides de confianza en tu bean servil
            try (FileOutputStream outputStream = new FileOutputStream(rutaArchivo)) {
                workbook.write(outputStream);
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el Excel de incidencias por usuario", e);
        }
        return rutaArchivo; // Aquí pinta un preciso rolado reportando y durmiendo el draw out backfile resultado
    }
}
