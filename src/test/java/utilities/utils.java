package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class utils {

    private static Workbook workbook;
    private static Sheet sheet;
    private static String excelFilePath;


    public static void loadExcel(String filePath, String sheetName) throws IOException {
        excelFilePath = filePath;
        FileInputStream inputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheet(sheetName);
    }

    // Get data from a cell
    public static String getCellData(int rowNum, int colNum) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        return cell.getStringCellValue();
    }


    public static void setCellData(int rowNum, int colNum, String value) {
        Row row = sheet.getRow(rowNum);
        Cell cell = row.createCell(colNum);
        cell.setCellValue(value);
    }


    public static void saveAndClose() throws IOException {
        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }


    public static int getRowCount() {
        return sheet.getLastRowNum() + 1;
    }

}
