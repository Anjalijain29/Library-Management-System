package com.nrifintech.lms.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;

//import antlr.collections.List;
 

public class ExcelGenerateReport{
	
	
	public static void  acceptIt(List<String[]> a) throws Exception, Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");
		int rowCount = 0;
        
        for (String[] aBook : a ) {
            Row row = sheet.createRow(rowCount++);
             
            int columnCount = 0;
             
            for (String field : aBook) {
                Cell cell = row.createCell(columnCount++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof String) {
                    cell.setCellValue((String) field);
                }
            }
             
        }
        try (
        		
        		FileOutputStream outputStream = new FileOutputStream("/documents/demo.xlsx") 
        		
        		){
            	workbook.write(outputStream);
        }
        }

	
	//Passing Header as different string array
	public static void acceptIt(String[] headers, List<String[]> a) throws Exception, Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");
		int rowCount = 0;
		int colCount = 0;
		Row row = sheet.createRow(rowCount++);
        for (String header : headers) {
            Cell cell = row.createCell(colCount++);
            if (header instanceof String) {
                cell.setCellValue((String) header);
            } else if (header instanceof String) {
                cell.setCellValue((String) header);
            }
        }
        for (String[] aBook : a ) {
            row = sheet.createRow(rowCount++);
             
            int columnCount = 0;
             
            for (String field : aBook) {
                Cell cell = row.createCell(columnCount++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof String) {
                    cell.setCellValue((String) field);
                }
            }
             
        }
        String fileLocation = new File("src\\main\\resources\\static\\documents\\").getAbsolutePath() + "\\" + "demo.xlsx";
        try (FileOutputStream outputStream = new FileOutputStream(fileLocation) ){
            workbook.write(outputStream);
        }
        catch(Exception e) {
        	
        }
        finally {
        	workbook.close();
        }
        }
	
}