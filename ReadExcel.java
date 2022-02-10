package week5.day2Assignment;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String[][] readExcel(String fileName) throws IOException {
		//set the path
		XSSFWorkbook wb=new XSSFWorkbook("./data/"+ fileName +".xlsx");
		
		//get into the specific sheet
		XSSFSheet ws = wb.getSheetAt(0);
		
		//get into the row
		XSSFRow row = ws.getRow(1);
		
		//get rowCount excluding header
		int rowCount = ws.getLastRowNum();
		//System.out.println(rowCount);
		
		//get rowCount including header
		int physicalNumberOfRows = ws.getPhysicalNumberOfRows();
		//System.out.println(physicalNumberOfRows);
		
		//get CellCount or column count
		int cellCount = ws.getRow(0).getLastCellNum();
		//System.out.println(cellCount);
		
		/*
		//get into the specific cell
		XSSFCell cell1 = row.getCell(0);
		
		//retrive the data from the cell
		
          String data1 = cell1.getStringCellValue();
         // System.out.println(data1);
          
          */
		
		String[][] data =new String[rowCount][cellCount];
          //get all the rows and column data
          System.out.println("--------------------");
          
          for(int i=1;i<=rowCount;i++) {
        	  XSSFRow row2 = ws.getRow(i);
        	 for(int j=0;j<cellCount;j++) {
        		 XSSFCell cell = row2.getCell(j);
        		 String cellValue = cell.getStringCellValue();
        		 data[i-1][j]=cellValue;
        	 } 
        	  
          }
          
          //close workBook
          wb.close();
          return data;
          
        	  
          
		
	}

}
