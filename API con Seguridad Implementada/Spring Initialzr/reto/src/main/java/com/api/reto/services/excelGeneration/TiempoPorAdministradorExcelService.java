package com.api.reto.services.excelGeneration;


import com.api.reto.models.IncidenciasEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.repositories.IIncidenciasRepository;
import com.api.reto.repositories.IPersonalRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.api.reto.models.IncidenciasEntity;
import com.api.reto.models.PersonalEntity;
import com.api.reto.repositories.IIncidenciasRepository;
import com.api.reto.repositories.IPersonalRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
@Transactional
public class TiempoPorAdministradorExcelService {

    @Autowired
    private IIncidenciasRepository incidenciasRepository;

    @Autowired
    private IPersonalRepository personalRepository;

    public String generarExcelTiempoPorAdministrador() {
        try {
            List<IncidenciasEntity> incidencias = incidenciasRepository.findAll();
            Map<Integer, Long> tiempoTotalPorAdmin = incidencias.stream()
                    .filter(inc -> inc.getResponsableId() != null && inc.getTiempo() != null)
                    .collect(Collectors.groupingBy(inc -> inc.getResponsableId().getId(),
                            Collectors.summingLong(inc -> inc.getTiempo().getTime() + 3600000))); // Ajuste para la hora

            String fileName = "Tiempo_Por_Administrador_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".xlsx";

            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Tiempo Por Administrador");
            createHeaderRow(sheet);

            int rowNum = 1;
            for (Map.Entry<Integer, Long> entry : tiempoTotalPorAdmin.entrySet()) {
                Integer adminId = entry.getKey();
                Long tiempoTotal = entry.getValue();
                PersonalEntity administrador = personalRepository.findById(adminId).orElse(null);

                Row row = sheet.createRow(rowNum++);
                if (administrador != null) {
                    fillRowWithAdminData(row, administrador, tiempoTotal);
                }
            }

            String filePath = "Informes/" + fileName;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            Files.write(Paths.get(filePath), out.toByteArray());
            workbook.close();
            out.close();

            return "Archivo creado exitosamente: " + filePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al generar el archivo: " + e.getMessage();
        }
    }

    private void createHeaderRow(Sheet sheet) {
        Row headerRow = sheet.createRow(0);
        String[] headers = {"ID Administrador", "Nombre Encargado", "Tiempo Total"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }

    private void fillRowWithAdminData(Row row, PersonalEntity administrador, Long tiempoTotal) {
        row.createCell(0).setCellValue(administrador.getId());
        row.createCell(1).setCellValue(administrador.getNombre() + " " + administrador.getApellido1() + (administrador.getApellido2() != null ? " " + administrador.getApellido2() : ""));
        row.createCell(2).setCellValue(formatDuration(tiempoTotal));
    }

    private String formatDuration(long millis) {
        long hours = millis / 3600000;
        long minutes = (millis % 3600000) / 60000;
        return String.format("%d Horas %d Minutos", hours, minutes);
    }
}
