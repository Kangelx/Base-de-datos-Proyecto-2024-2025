package com.api.reto.services.excelGeneration;

import com.api.reto.models.AulasEntity;
import com.api.reto.services.basics.AulaService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.ArrayList;

@Service
public class AulasExcelService {

    @Autowired
    private AulaService aulaService;

    public void generarArchivoExcel() {
        try {
            ArrayList<AulasEntity> aulas = aulaService.getAulas();

            // Crear nuevo libro de Excel
            Workbook workbook = new XSSFWorkbook();
            Sheet hoja = workbook.createSheet("Aulas");

            // Crear estilos para encabezados
            CellStyle estiloEncabezados = workbook.createCellStyle();
            Font fuenteEncabezados = workbook.createFont();
            fuenteEncabezados.setBold(true);
            estiloEncabezados.setFont(fuenteEncabezados);

            // Escribir encabezados con estilo
            Row encabezados = hoja.createRow(0);
            encabezados.createCell(0).setCellValue("Num");
            encabezados.createCell(1).setCellValue("Codigo");
            encabezados.createCell(2).setCellValue("Descripcion");
            encabezados.createCell(3).setCellValue("Planta");
            for (Cell cell : encabezados) {
                cell.setCellStyle(estiloEncabezados);
            }

            // Escribir datos de las aulas
            int filaActual = 1;
            for (AulasEntity aula : aulas) {
                Row fila = hoja.createRow(filaActual++);
                fila.createCell(0).setCellValue(aula.getNum());
                fila.createCell(1).setCellValue(aula.getCodigo());
                fila.createCell(2).setCellValue(aula.getDescripcion());
                fila.createCell(3).setCellValue(aula.getPlanta());
            }

            // Ajustar anchos de columnas
            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);
            hoja.autoSizeColumn(2);
            hoja.autoSizeColumn(3);

            // Guardar el archivo Excel
            String nombreArchivo = "aulas.xlsx";
            try (FileOutputStream outputStream = new FileOutputStream(nombreArchivo)) {
                workbook.write(outputStream);
            }

            System.out.println("Archivo Excel generado exitosamente: " + nombreArchivo);
        } catch (Exception e) {
            System.err.println("Error al generar el archivo Excel: " + e.getMessage());
            e.printStackTrace();
        }
    }
}