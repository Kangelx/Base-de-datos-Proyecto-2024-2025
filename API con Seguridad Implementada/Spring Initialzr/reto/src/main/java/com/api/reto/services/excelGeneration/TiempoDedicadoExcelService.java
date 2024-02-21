package com.api.reto.services.excelGeneration;


import com.api.reto.models.IncidenciasEntity;
import com.api.reto.repositories.IIncidenciasRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TiempoDedicadoExcelService {

    @Autowired
    private IIncidenciasRepository incidenciasRepository;

    public String generarExcelTiempoDedicado() throws Exception {
        List<IncidenciasEntity> incidencias = incidenciasRepository.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = "Tiempo_Dedicado_" + sdf.format(new Date()) + ".xlsx";

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Tiempo Dedicado");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Tiempo", "Estado", "Fecha Creación", "Fecha Cierre"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowNum = 1;
            for (IncidenciasEntity incidencia : incidencias) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(incidencia.getNum());
                if (incidencia.getTiempo() != null) {
                    row.createCell(1).setCellValue(incidencia.getTiempo().toString());
                } else {
                    row.createCell(1).setCellValue("");
                }
                String estado = incidencia.getEstado().toString();
                Cell estadoCell = row.createCell(2);
                estadoCell.setCellValue(estado);
                if (estado.equalsIgnoreCase("resuelta")) {
                    CellStyle style = workbook.createCellStyle();
                    style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    estadoCell.setCellStyle(style);
                }
                row.createCell(3).setCellValue(incidencia.getFechaCreacion().toString());
                if (incidencia.getFechaCierre() != null) {
                    row.createCell(4).setCellValue(incidencia.getFechaCierre().toString());
                } else {
                    row.createCell(4).setCellValue("");
                }
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