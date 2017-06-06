package com.kat.usepoi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UsePOI {
	private static final int Cell_TYPE_STRING = 0;

	public static void main(String[] args) throws IOException {
		FileInputStream fileIn = new FileInputStream("C:\\JY\\fortest.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fileIn);
		fileIn.close();

		XSSFSheet sheet = workbook.getSheetAt(0);
		int rowLen = sheet.getLastRowNum();
		for(int i=0;i<rowLen;i++){
			XSSFRow row = sheet.getRow(i);
			if( row==null )continue;
			int cellNum = row.getLastCellNum();
			for(int j=0;j<cellNum;j++){
				XSSFCell rowCell = row.getCell(j);
				if( rowCell == null ) continue;
				System.out.print(rowCell.getStringCellValue()+"\t");
			}
				
			System.out.println();
		}
		//workbook.close();
		
		int newRowIndex = rowLen +1;
		XSSFRow newRow = sheet.createRow(newRowIndex);
		int cellIndex = 0;
		XSSFCell nameCell = newRow.createCell(cellIndex++,Cell_TYPE_STRING);
		nameCell.setCellValue("kat");
		XSSFCell nameCell2 = newRow.createCell(cellIndex++,Cell_TYPE_STRING);
		nameCell2.setCellValue("katherine");
		FileOutputStream fileOut = new FileOutputStream("C:\\JY\\fortest.xlsx");
		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
		workbook.close();
	}
}
