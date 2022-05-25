package com.demo6.shop.excel;


import com.demo6.shop.dto.ScheduleDTO;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CurrentDateExcelExpoter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ScheduleDTO> scheduleDTOS;

    public CurrentDateExcelExpoter(List<ScheduleDTO> scheduleDTOS) {
        this.scheduleDTOS = scheduleDTOS;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("CurrentExcel");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(15);
        style.setFont(font);

        createCell(row, 0, "Name", style);
        createCell(row, 1, "Quantity", style);
        createCell(row, 2, "RemainingAmount", style);
        createCell(row, 3, "Price", style);
        createCell(row, 4, "ExpirationDate", style);
        createCell(row, 5, "TotaPriceInDay", style);
        createCell(row,6,"Exist",style);


    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Float) {
            cell.setCellValue((Float) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
            formatDateCell(workbook, cell);
        } else if (value instanceof BigDecimal) {
            cell.setCellValue( ((BigDecimal) value).intValue());
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        }
    }

    private void writeDataLines() {
        Integer rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (ScheduleDTO scheduleDTO : scheduleDTOS) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            Integer remainingAmount = scheduleDTO.getRemainingAmount() == null ? 0 : scheduleDTO.getRemainingAmount();
            createCell(row, columnCount++, scheduleDTO.getName(), style);
            createCell(row, columnCount++, scheduleDTO.getQuantity(), style);
            createCell(row, columnCount++, remainingAmount, style);
            createCell(row, columnCount++, scheduleDTO.getPrice(), style);
            createCell(row, columnCount++, scheduleDTO.getExpirationDate(), style);
            createCell(row, columnCount++, Math.ceil(scheduleDTO.getTotaPrice()), style);
            createCell(row, columnCount++, scheduleDTO.getStatus(), style);
        }

    }
    private void formatDateCell(XSSFWorkbook workbook, Cell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        cell.setCellStyle(cellStyle);
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }
}