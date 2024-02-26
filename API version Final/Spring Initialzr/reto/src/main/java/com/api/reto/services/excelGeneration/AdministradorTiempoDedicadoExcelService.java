package com.api.reto.services.excelGeneration;

import com.api.reto.models.IncidenciasEntity;
import com.api.reto.repositories.IIncidenciasRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AdministradorTiempoDedicadoExcelService {

    @Autowired
    private IIncidenciasRepository incidenciasRepository;

    public String generarExcelIncidencias() throws Exception {
        List<IncidenciasEntity> incidencias = incidenciasRepository.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String fileName = "Incidencias_Completo_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".xlsx";

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Incidencias");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            String[] headers = {"ID", "Tipo", "Subtipo ID", "Estado", "Descripción", "Adjunto URL", "Tiempo",
                    "Responsable ID", "Fecha Creación", "Fecha Cierre", "Prioridad"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 1;
            for (IncidenciasEntity incidencia : incidencias) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(incidencia.getNum());
                row.createCell(1).setCellValue(incidencia.getTipo().toString());
                row.createCell(2).setCellValue(incidencia.getSubtipoId() != null ? String.valueOf(incidencia.getSubtipoId().getId()) : "N/A");
                row.createCell(3).setCellValue(incidencia.getEstado().toString());
                row.createCell(4).setCellValue(incidencia.getDescripcion());
                row.createCell(5).setCellValue(incidencia.getAdjuntoUrl() != null ? incidencia.getAdjuntoUrl() : "N/A");

                if (incidencia.getTiempo() != null) {

                    row.createCell(6).setCellValue(incidencia.getTiempo().toString());
                } else {
                    row.createCell(6).setCellValue("N/A");
                }
                row.createCell(7).setCellValue(incidencia.getResponsableId() != null ? String.valueOf(incidencia.getResponsableId().getId()) : "N/A");
                row.createCell(8).setCellValue(sdf.format(incidencia.getFechaCreacion()));
                row.createCell(9).setCellValue(incidencia.getFechaCierre() != null ? sdf.format(incidencia.getFechaCierre()) : "N/A");
                row.createCell(10).setCellValue(incidencia.getPrioridad().toString());
            }

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                saveFile(out.toByteArray(), fileName);
                return "Ruta de archivo: Informes/" + fileName;
            }
        }
    }

    private void saveFile(byte[] bytes, String fileName) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream("Informes/" + fileName)) {
            fileOutputStream.write(bytes);
        }
    }
}
