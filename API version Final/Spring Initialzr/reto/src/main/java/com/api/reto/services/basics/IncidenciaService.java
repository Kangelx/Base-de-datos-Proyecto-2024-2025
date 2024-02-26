package com.api.reto.services.basics;

import com.api.reto.dto.IncidenciaDTO;
import com.api.reto.enums.EstadoEnum;
import com.api.reto.enums.TipoEnum;
import com.api.reto.models.IncidenciasEntity;
import com.api.reto.repositories.IIncidenciasRepository;
import jakarta.persistence.EntityNotFoundException;
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
import java.util.stream.Collectors;

@Repository
public class IncidenciaService {
    @Autowired
    IIncidenciasRepository incidenciasRepository;

    public List<IncidenciaDTO> obtenerIncidenciasPorTipo(TipoEnum tipo) {
        return incidenciasRepository.findByTipo(tipo).stream().map(incidencia ->
                new IncidenciaDTO(
                        incidencia.getTipo(),
                        incidencia.getDescripcion(),
                        incidencia.getFechaCreacion(),
                        incidencia.getFechaCierre(),
                        incidencia.getEstado(),
                        incidencia.getAdjuntoUrl(),
                        incidencia.getPrioridad()
                )
        ).collect(Collectors.toList());
    }


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

        String nombreArchivo = "Incidencias_Por_Usuario_" + System.currentTimeMillis() + ".xlsx";
        Path pathCarpeta = Path.of("Informes");
        if(!Files.exists(pathCarpeta)) {
            Files.createDirectories(pathCarpeta); // Asegura la existencia del directorio "Informes"
        }
        String rutaArchivo = pathCarpeta.resolve(nombreArchivo).toString();

        // Lógica de funcionalidad para generar el Excel
        try (Workbook workbook = new XSSFWorkbook()) {

            try (FileOutputStream outputStream = new FileOutputStream(rutaArchivo)) {
                workbook.write(outputStream);
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el Excel de incidencias por usuario", e);
        }
        return rutaArchivo;
    }

    //Adjuntos

    public void actualizarUrlAdjuntoIncidencia(Integer incidenciaId, String urlAdjunto) {
        IncidenciasEntity incidencia = incidenciasRepository.findById(incidenciaId)
                .orElseThrow(() -> new EntityNotFoundException("Incidencia no encontrada"));
        incidencia.setAdjuntoUrl(urlAdjunto);
        incidenciasRepository.save(incidencia);
    }
}
