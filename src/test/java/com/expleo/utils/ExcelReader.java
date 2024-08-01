package com.expleo.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExcelReader {

    private static final String EXCEL_FILE_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\FeatureData.xlsx";
    private static Map<String, Map<Integer, Map<Integer, String>>> workbookData = new ConcurrentHashMap<>();

    static {
    	System.out.println("Excel file path--> "+EXCEL_FILE_PATH);
        try (FileInputStream fileInputStream = new FileInputStream(EXCEL_FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                String sheetName = sheet.getSheetName();
                Map<Integer, Map<Integer, String>> sheetData = new HashMap<>();

                for (Row row : sheet) {
                    Map<Integer, String> rowData = new HashMap<>();
                    for (Cell cell : row) {
                        rowData.put(cell.getColumnIndex(), cell.toString());
                    }
                    sheetData.put(row.getRowNum(), rowData);
                }
                workbookData.put(sheetName, sheetData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Map<Integer, Map<Integer, String>>> getWorkbookData() {
        return workbookData;
    }

    public static Map<Integer, Map<Integer, String>> getSheetData(String sheetName) {
        return workbookData.get(sheetName);
    }

    public static String getCellData(String sheetName, int rowNum, int colNum) {
        return workbookData.get(sheetName).get(rowNum).get(colNum);
    }
}
