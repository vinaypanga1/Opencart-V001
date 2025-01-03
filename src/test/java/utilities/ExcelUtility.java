package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;

public class ExcelUtility {

    private FileInputStream fis;
    private FileOutputStream fos;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private String path;

    // Constructor to initialize Excel file path and sheet
    public ExcelUtility(String path, String sheetName) throws IOException {
        this.path = path;
        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            throw new RuntimeException("Sheet '" + sheetName + "' not found in the Excel file");
        }
    }

    // Method to get row count
    public int getRowCount() {
        return sheet.getLastRowNum() + 1; // Include header row
    }

    // Method to get column count
    public int getColCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    // Method to get cell data
    public String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        if (row == null) return "";

        Cell cell = row.getCell(colNum);
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    // Write data to a cell
    public void writeCellData(int rowNum, int colNum, String value) throws IOException {
        Row row = sheet.getRow(rowNum);
        if (row == null) row = sheet.createRow(rowNum);

        Cell cell = row.getCell(colNum);
        if (cell == null) cell = row.createCell(colNum);

        cell.setCellValue(value);

        fos = new FileOutputStream(path);
        workbook.write(fos);
        fos.close();
    }

    // Close resources
    public void close() throws IOException {
        if (fis != null) fis.close();
        if (fos != null) fos.close();
        if (workbook != null) workbook.close();
    }
}