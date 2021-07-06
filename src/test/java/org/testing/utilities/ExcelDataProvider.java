package org.testing.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.Test;

public class ExcelDataProvider {
	
	
	@Test
	public static Map<String,Object> testdata(String excelPath, String sheetName) throws IOException {
		
		ReadFromExcelFile file = new ReadFromExcelFile(excelPath,sheetName);
		XSSFSheet currentSheet =file.currentsheet();
		
		
	   int totalRows= file.rowCount();
	  int totalColumns= file.columnCount(0);
	  System.out.println("Total number of Physical rows in excel sheet: " + totalRows);
	  System.out.println("Total number of Physical columns in excel sheet: " + totalColumns);
	  
		Map<String, Object> params = new HashMap<String,Object>();
		

		for(int r=1;r<totalRows;r++) {
		
			String key= (String) file.getCellData(r, 0);		
			Object value=file.getCellData(r, 1);
			params.put(key, value);
				
		}
		System.out.println("Data present in excel sheet");
		Set<String> all_keys= params.keySet();
		for(Object i: all_keys) 
		{
		System.out.println("Key is: " +i + " Value is: "+ params.get(i));
		}
		return params;
		}
	
	@Test
	public static List<Map<String, Object>> testdata2(String excelPath, String sheetName) throws IOException {
		
		ReadFromExcelFile file = new ReadFromExcelFile(excelPath,sheetName);
		XSSFSheet currentSheet =file.currentsheet();
		
		
	   int totalRows= file.rowCount();
	  int totalColumns= file.columnCount(0);
	  System.out.println("Total number of Physical rows in excel sheet: " + totalRows);
	  System.out.println("Total number of Physical columns in excel sheet: " + totalColumns);
	  
	  List<Map<String, Object>> listMap =  new ArrayList<Map<String,Object>>();

		String s[] = new String[totalColumns];
		for(int c=0;c<totalColumns;c++) {
			String key= (String) file.getCellData(0, c);
			s[c]=key;
			
		}
		for(int i=0;i<s.length;i++) {
			System.out.println(s[i].toString());
		}

		
		for(int r=1;r<totalRows;r++) {
			Map<String, Object> params = new HashMap<String,Object>();

			for(int c=0;c<totalColumns;c++) {
		
			String key= s[c];		
			Object value=file.getCellData(r, c);
			params.put(key, value);
			}
			listMap.add(params);
				
		}
	
		return listMap;
		}

}
	
	


