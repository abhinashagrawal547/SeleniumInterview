package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static boolean verifyAValueiSPresent(String fileName, String value) throws IOException {
		File file = new File(fileName);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		Iterator<Row> itr = sheet.rowIterator();
		while(itr.hasNext()) {
			Row row = itr.next();
			Iterator<Cell> cellIterator = row.iterator();
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				
				switch(cell.getCellType()) {
					case STRING:
						System.out.println(cell.getStringCellValue());
						if(cell.getStringCellValue().equals(value)) {
							wb.close();
							return true;
						}
						break;
					case NUMERIC:
						System.out.println(cell.getNumericCellValue());
						break;
				}
			}
		}
		wb.close();
		return false;
	}
}
