package com.api.reto.services.excelGeneration;


import com.api.reto.models.IncidenciasSubtiposEntity;
import com.api.reto.repositories.IIncidenciasSubtiposRepository;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class EstadisticasIncidenciasExcelService {

    @Autowired
    private IIncidenciasSubtiposRepository incidenciasSubtiposRepository;

    public String generarExcelEstadisticasIncidencias() throws Exception {
        List<IncidenciasSubtiposEntity> subtiposIncidencias = incidenciasSubtiposRepository.findAll();
        Map<String, Double> estadisticas = calcularEstadisticas(subtiposIncidencias);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = "Estadisticas_Incidencias_" + sdf.format(new Date()) + ".xlsx";

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Estadisticas de Incidencias");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);
        Cell headerCellTipo = headerRow.createCell(0);
        headerCellTipo.setCellValue("Tipo");
        headerCellTipo.setCellStyle(headerCellStyle);
        Cell headerCellPorcentaje = headerRow.createCell(1);
        headerCellPorcentaje.setCellValue("Porcentaje");
        headerCellPorcentaje.setCellStyle(headerCellStyle);

        int rowNum = 1;
        for (Map.Entry<String, Double> entry : estadisticas.entrySet()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue());
        }

        // Agregar gráfico
        addChartToSheet(sheet);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            workbook.write(out);
            saveFile(out.toByteArray(), fileName);
            return "Ruta de archivo: Informes/" + fileName;
        } finally {
            workbook.close();
        }
    }

    private void addChartToSheet(XSSFSheet sheet) {
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 10, 20);

        XSSFChart chart = drawing.createChart(anchor);
        chart.setTitleText("Porcentaje de Incidencias por Tipo");
        chart.setTitleOverlay(false);

        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP_RIGHT);

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("Tipo");
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("Porcentaje");
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        XDDFDataSource<String> tipos = XDDFDataSourcesFactory.fromStringCellRange(sheet,
                new CellRangeAddress(1, sheet.getLastRowNum(), 0, 0));
        XDDFNumericalDataSource<Double> porcentajes = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                new CellRangeAddress(1, sheet.getLastRowNum(), 1, 1));

        XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
        XDDFChartData.Series series = data.addSeries(tipos, porcentajes);
        series.setTitle("Porcentaje de Incidencias", null);
        chart.plot(data);

        XDDFBarChartData bar = (XDDFBarChartData) data;
        bar.setBarDirection(BarDirection.COL);
    }

    private Map<String, Double> calcularEstadisticas(List<IncidenciasSubtiposEntity> subtiposIncidencias) {
        Map<String, Integer> countMap = new LinkedHashMap<>();
        int total = 0;

        for (IncidenciasSubtiposEntity subtipo : subtiposIncidencias) {
            String tipo = subtipo.getTipo().toString();
            countMap.put(tipo, countMap.getOrDefault(tipo, 0) + 1);
            total++;
        }

        Map<String, Double> porcentajeMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            double porcentaje = (entry.getValue() * 100.0) / total;
            porcentajeMap.put(entry.getKey(), porcentaje);
        }
        return porcentajeMap;
    }

    private void saveFile(byte[] bytes, String fileName) throws Exception {
        try (FileOutputStream fileOutputStream = new FileOutputStream("Informes/" + fileName)) {
            fileOutputStream.write(bytes);
        }
    }
}