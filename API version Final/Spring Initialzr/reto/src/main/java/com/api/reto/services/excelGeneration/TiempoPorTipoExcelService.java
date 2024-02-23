package com.api.reto.services.excelGeneration;


import com.api.reto.enums.TipoEnum;
import com.api.reto.models.IncidenciasEntity;
import com.api.reto.repositories.IIncidenciasRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class TiempoPorTipoExcelService {

    @Autowired
    private IIncidenciasRepository incidenciasRepository;

    public String generarExcelTiempoPorTipo() throws Exception {
        List<IncidenciasEntity> incidencias = incidenciasRepository.findAll();

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Tiempo por Tipo");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);
            String[] headers = {"Tipo", "Tiempo Total"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
            }

            Map<TipoEnum, Long> tiempoPorTipo = calcularTiempoPorTipo(incidencias);

            int rowNum = 1;
            for (TipoEnum tipo : tiempoPorTipo.keySet()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(tipo.toString());
                row.createCell(1).setCellValue(formatearTiempo(tiempoPorTipo.get(tipo)));
            }

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                String fileName = "Tiempo_Por_Tipo_" + obtenerFechaHoraActual() + ".xlsx";
                saveFile(out.toByteArray(), fileName);
                return "Ruta de archivo: Informes/" + fileName;
            }
        }
    }

    private Map<TipoEnum, Long> calcularTiempoPorTipo(List<IncidenciasEntity> incidencias) {
        Map<TipoEnum, Long> tiempoPorTipo = new HashMap<>();

        for (IncidenciasEntity incidencia : incidencias) {
            TipoEnum tipo = incidencia.getTipo();
            Time tiempo = incidencia.getTiempo();

            if (tiempo != null) {
                long horas = tiempo.getHours();
                long minutos = tiempo.getMinutes();
                long segundos = tiempo.getSeconds();
                long duracionEnSegundos = horas * 3600 + minutos * 60 + segundos;

                tiempoPorTipo.merge(tipo, duracionEnSegundos, Long::sum);
            }
        }

        return tiempoPorTipo;
    }

    private String formatearTiempo(long segundosTotales) {
        long horas = segundosTotales / 3600;
        long minutos = (segundosTotales % 3600) / 60;
        long segundos = segundosTotales % 60;

        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    private String obtenerFechaHoraActual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return dateFormat.format(new Date());
    }

    private void saveFile(byte[] bytes, String fileName) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream("Informes/" + fileName)) {
            fileOutputStream.write(bytes);
        }
    }
}