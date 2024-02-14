package com.api.reto.services.excelGeneration;

import com.api.reto.dto.IncidenciaDTO;
import com.api.reto.models.IncidenciasEntity;
import com.api.reto.repositories.IIncidenciasRepository;
import com.api.reto.services.basics.IncidenciaService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class IncidenciasExcelService {

    @Autowired
    private IIncidenciasRepository incidenciaRepository;

    public void generateExcel() throws IOException {
        List<IncidenciasEntity> incidencias = incidenciaRepository.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Incidencias");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        String[] columns = {"Id", "Tipo", "Subtipo ID", "Fecha Creación", "Fecha Cierre", "Descripción", "Estado", "Creador ID", "Responsable ID", "Equipo ID", "Prioridad"};
        Row headerRow = sheet.createRow(0);
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle.setFont(headerFont);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Llenar datos
        int rowNum = 1;
        for (IncidenciasEntity incidencia : incidencias) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(incidencia.getNum());
            row.createCell(1).setCellValue(incidencia.getTipo().name());
            row.createCell(2).setCellValue(String.valueOf(incidencia.getSubtipoId() != null ? incidencia.getSubtipoId().getId() : "N/A"));
            row.createCell(3).setCellValue(sdf.format(incidencia.getFechaCreacion()));
            row.createCell(4).setCellValue(incidencia.getFechaCierre() != null ? sdf.format(incidencia.getFechaCierre()) : "N/A");
            row.createCell(5).setCellValue(incidencia.getDescripcion());
            row.createCell(6).setCellValue(incidencia.getEstado().name());
            row.createCell(7).setCellValue(String.valueOf(incidencia.getCreadorId() != null ? incidencia.getCreadorId().getId() : "N/A"));
            row.createCell(8).setCellValue(String.valueOf(incidencia.getResponsableId() != null ? incidencia.getResponsableId().getId() : "N/A"));
            row.createCell(9).setCellValue(String.valueOf(incidencia.getEquipoId() != null ? incidencia.getEquipoId().getId() : "N/A"));
            row.createCell(10).setCellValue(incidencia.getPrioridad().name());
        }

        // Autoajustar columnas
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Escribir el archivo
        try (FileOutputStream fileOut = new FileOutputStream("Incidencias_Report.xlsx")) {
            workbook.write(fileOut);
        }
        workbook.close();
    }
}
