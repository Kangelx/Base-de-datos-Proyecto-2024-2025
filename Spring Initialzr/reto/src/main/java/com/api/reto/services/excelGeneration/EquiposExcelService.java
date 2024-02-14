package com.api.reto.services.excelGeneration;

import com.api.reto.models.EquiposEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

@Service

public class EquiposExcelService {
    public void generateExcel(ArrayList<EquiposEntity> equipos) throws IOException {
        // Crear un nuevo libro de Excel
        Workbook workbook = new XSSFWorkbook();
        // Crear una nueva hoja en el libro
        Sheet sheet = workbook.createSheet("Equipos");

        // Crear el encabezado en negrita
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Crear la fila del encabezado
        Row headerRow = sheet.createRow(0);

        // Definir los nombres de las columnas
        String[] columns = {"ID", "Tipo Equipo", "Fecha de Adquisición", "Etiqueta", "Marca", "Modelo", "Descripción", "Baja", "Aula", "Puesto"};

        // Crear las celdas del encabezado
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Llenar el resto de las filas
        int rowNum = 1;
        for (EquiposEntity equipo : equipos) {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0).setCellValue(equipo.getId());
            row.createCell(1).setCellValue(equipo.getTipoEquipo().toString());
            row.createCell(2).setCellValue(equipo.getFechaAdquisicion().toString());
            row.createCell(3).setCellValue(equipo.getEtiqueta());
            row.createCell(4).setCellValue(equipo.getMarca());
            row.createCell(5).setCellValue(equipo.getModelo());
            row.createCell(6).setCellValue(equipo.getDescripcion());
            row.createCell(7).setCellValue(equipo.getBaja() != null ? equipo.getBaja() : 0); // Si es nulo, poner 0
            row.createCell(8).setCellValue(equipo.getPuesto() != null ? equipo.getPuesto() : 0); // Si es nulo, poner 0
        }

        // Ajustar el ancho de las columnas automáticamente
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Guardar el libro de Excel
        try (FileOutputStream fileOut = new FileOutputStream("equipos.xlsx")) {
            workbook.write(fileOut);
        }

        // Cerrar el libro de Excel
        workbook.close();
    }
}
