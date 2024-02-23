package com.api.reto.services.excelGeneration;

import com.api.reto.models.IncidenciasEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.repositories.IIncidenciasRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class IncidenciasAbiertasExcelService {

    @Autowired
    private IIncidenciasRepository incidenciasRepository;

    public String generarExcelIncidenciasPorUsuario() throws Exception {
        List<IncidenciasEntity> todasLasIncidencias = incidenciasRepository.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String fileName = "Incidencias_Por_Usuario_" + sdf.format(new Date()) + ".xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Incidencias Usuario");

        Map<String, Object[]> data = new LinkedHashMap<>(); // LinkedHashMap para mantener el orden de inserción
        data.put("1", new Object[]{"ID Incidencia", "Tipo", "Fecha Inicio", "Fecha Fin", "Estado", "ID Usuario", "Nombre", "Apellido 1", "Apellido 2"});

        // Estilo para la cabecera (negrita)
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        int rowNumber = 0; // Comenzamos desde 0
        for (String key : data.keySet()) {
            Row row = sheet.createRow(rowNumber++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj != null) {
                    if (obj instanceof String) {
                        cell.setCellValue((String) obj);
                        // Aplicar estilo a la celda
                        cell.setCellStyle(style);
                    }
                }
            }
        }

        for (IncidenciasEntity inc : todasLasIncidencias) {
            PersonalEntity usuario = inc.getCreadorId(); // Asume getCreadorId() obtiene el usuario
            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(inc.getNum());
            row.createCell(1).setCellValue(inc.getTipo().toString());
            row.createCell(2).setCellValue(sdf.format(inc.getFechaCreacion()));
            row.createCell(3).setCellValue(inc.getFechaCierre() != null ? sdf.format(inc.getFechaCierre()) : "");
            row.createCell(4).setCellValue(inc.getEstado().toString());
            row.createCell(5).setCellValue(usuario.getId());
            row.createCell(6).setCellValue(usuario.getNombre());
            row.createCell(7).setCellValue(usuario.getApellido1());
            row.createCell(8).setCellValue(usuario.getApellido2() != null ? usuario.getApellido2() : "");
        }

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            workbook.write(out);
            saveFile(out.toByteArray(), fileName);
            return "Ruta de archivo: Informes/" + fileName;
        } finally {
            workbook.close();
        }
    }

    private void saveFile(byte[] bytes, String fileName) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream("Informes/" + fileName)) {
            fileOutputStream.write(bytes);
        }
    }
}