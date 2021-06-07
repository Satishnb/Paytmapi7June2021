package org.testing.utilities;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.ss.usermodel.Row;

public class ReadFromExcelFile {
	
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	public ReadFromExcelFile(String excelPath, int sheetNum) throws IOException {
		workbook= new XSSFWorkbook(excelPath);
		 sheet= workbook.getSheetAt(sheetNum);	
	}
	
	public static int rowCount() throws IOException {
		 
		 int totalRows =sheet.getPhysicalNumberOfRows();
		 return totalRows;
	}
	
	public static int columnCount(int rowNum) throws IOException {
		 
		 int totalColumns =sheet.getRow(rowNum).getPhysicalNumberOfCells();
		 return totalColumns;
	}
	
	
	public static String getCellDataString(int rowNum, int colNum) throws IOException {
		 
		 String sCellData =sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		 return sCellData;
	}
	
	public static double getCellDataNumber(int rowNum, int colNum) throws IOException {
		 
		 double nCellData=0;
		try {
			nCellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
		} catch (Exception e) {
			System.out.println(e);
			// TODO Auto-generated catch block
		}
		 return nCellData;
	}
	
	public static Object getCellData(int rowNum, int colNum) throws IOException {
		 
		 Object nCellData=0;
		try {
			nCellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			return nCellData;
		} catch (Exception e) {
			//System.out.println(e);
		}
		
		try {
			nCellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
		} catch (Exception e) {
			//System.out.println(e);
		}
		 return nCellData;
	}
	
	

}
