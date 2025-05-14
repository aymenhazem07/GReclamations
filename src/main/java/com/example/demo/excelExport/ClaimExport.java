package com.example.demo.excelExport;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.model.Claim;
import com.example.demo.model.Claim;
import com.example.demo.model.Objet;

public class ClaimExport {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Claim> listClaims;

    public ClaimExport(List<Claim> listClaims) {
        this.listClaims = listClaims;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Claims");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Claim ID", style);
        createCell(row, 1, "Name", style);
        createCell(row, 2, "E-mail", style);
        createCell(row, 3, "Objet", style);
        createCell(row, 4, "Description", style);
        createCell(row, 5, "Canal", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else {
            cell.setCellValue(value.toString());
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Claim claim : listClaims) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, claim.getId(), style);
            createCell(row, columnCount++, claim.getName(), style);
            createCell(row, columnCount++, claim.getEmail(), style);
            createCell(row, columnCount++, claim.getObjet(), style);
            createCell(row, columnCount++, claim.getDescription(), style);
            createCell(row, columnCount++, claim.getCanal(), style);
        }
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
