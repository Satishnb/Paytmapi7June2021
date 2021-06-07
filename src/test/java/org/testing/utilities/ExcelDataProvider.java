package org.testing.utilities;

import java.io.IOException;

public class ExcelDataProvider {
	
	public static void testdata(String excelPath, int sheetIndex) throws IOException {
		
		ReadFromExcelFile file = new ReadFromExcelFile(excelPath,sheetIndex);
	   int totalRows= file.rowCount();
	  int totalColumns= file.columnCount(0);
		for(int r=1;r<totalRows;r++) {
			for(int c=0;c<totalColumns;c++) {
				System.out.println(r+ "| "+ c);
				Object celldata= file.getCellData(r, c);
				
				System.out.println(celldata);

			}
	}
	}
	
	
	public static void main(String[] args) throws IOException {
		testdata("../paytm_api/excel/data.xlsx",0);
	}

}
