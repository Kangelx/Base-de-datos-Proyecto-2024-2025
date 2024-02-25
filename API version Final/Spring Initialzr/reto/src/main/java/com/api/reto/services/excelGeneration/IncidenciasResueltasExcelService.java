package com.api.reto.services.excelGeneration;

import com.api.reto.enums.EstadoEnum;
import com.api.reto.models.AulasEntity;
import com.api.reto.models.IncidenciasEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.services.basics.AulaService;
import com.api.reto.services.basics.IncidenciaService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidenciasResueltasExcelService {

    @Autowired
    private IncidenciaService incidenciaService;

    public String generarArchivoExcelYGuardar() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String fechaActual = sdf.format(new Date());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String nombreArchivo = "IncidenciasResueltas_" + fechaActual + ".xlsx";

        String directorio = "Informes";
        String rutaArchivo = directorio + "/" + nombreArchivo;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        // Crear la carpeta si no existe
        Files.createDirectories(Paths.get(directorio));

        try (Workbook workbook = new XSSFWorkbook()) {
            List<IncidenciasEntity> incidenciasResueltas = incidenciaService.getIncidenciasResueltas()
                    .stream()// Asegúrate de que este filtro sea correcto
                    .collect(Collectors.toList());
            Sheet hoja = workbook.createSheet("Incidencias Resueltas");


            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);

            // Definición de los títulos de columnas
            String[] columnas = {"ID", "Tipo", "Fecha Creación", "Fecha Cierre", "Descripción", "Estado", "Tiempo", "Responsable"};
            Row headerRow = hoja.createRow(0);
            for (int i = 0; i < columnas.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnas[i]);
                cell.setCellStyle(headerStyle);
            }

            // Rellenar las filas con los datos de las incidencias
            int rowNum = 1;
            for (IncidenciasEntity incidencia : incidenciasResueltas) {
                Row row = hoja.createRow(rowNum++);

                Cell cellId = row.createCell(0);
                cellId.setCellValue(incidencia.getNum());

                Cell cellTipo = row.createCell(1);
                cellTipo.setCellValue(incidencia.getTipo().name());

                Cell cellFechaCreacion = row.createCell(2);
                String fechaCreacionStr = incidencia.getFechaCreacion() != null ? dateFormat.format(incidencia.getFechaCreacion()) : "N/A";
                cellFechaCreacion.setCellValue(fechaCreacionStr);

                // Fecha de cierre
                Cell cellFechaCierre = row.createCell(3);
                String fechaCierreStr = incidencia.getFechaCierre() != null ? dateFormat.format(incidencia.getFechaCierre()) : "N/A";
                cellFechaCierre.setCellValue(fechaCierreStr);

                Cell cellDescripcion = row.createCell(4);
                cellDescripcion.setCellValue(incidencia.getDescripcion());

                Cell cellEstado = row.createCell(5);
                cellEstado.setCellValue(incidencia.getEstado().name());

                Cell cellTiempo = row.createCell(6);
                if(incidencia.getTiempo() != null) {
                    cellTiempo.setCellValue(timeFormat.format(incidencia.getTiempo()));
                } else {
                    cellTiempo.setCellValue("N/A");
                }

                Cell cellResponsable = row.createCell(7);
                if(incidencia.getResponsableId() != null) {
                    String nombreCompleto = incidencia.getResponsableId().getNombre() + " " + (incidencia.getResponsableId().getApellido1() != null ? incidencia.getResponsableId().getApellido1() : "") + " " + (incidencia.getResponsableId().getApellido2() != null ? incidencia.getResponsableId().getApellido2() : "");
                    cellResponsable.setCellValue(nombreCompleto.trim());
                } else {
                    cellResponsable.setCellValue("N/A");
                }
            }

            // Ajustar el tamaño de las columnas
            for (int i = 0; i < columnas.length; i++) {
                hoja.autoSizeColumn(i);
            }

            // Guardar el archivo en el disco
            try (FileOutputStream fileOut = new FileOutputStream(rutaArchivo)) {
                workbook.write(fileOut);
            }
        } catch (Exception e) {
            throw new Exception("Error al generar el archivo Excel de incidencias resueltas", e);
        }
        return rutaArchivo;
    }
}