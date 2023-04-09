package data;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	/**
	 * Get data from a specific test case in an Excel sheet.
	 *
	 * @param filePath     the path to the Excel file.
	 * @param sheetName    the name of the sheet containing the test data.
	 * @param testCaseName the name of the test case whose data is to be retrieved.
	 * @return an ArrayList containing the test data for the specified test case.
	 * @throws IOException if an I/O error occurs while accessing the Excel file.
	 */
	
	
    public static ArrayList<String> getData(String filePath, String sheetName, String testCaseName) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fis);

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet not found: " + sheetName);
        }

        // Find the column containing the test case names.
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) {
            throw new IllegalArgumentException("Sheet is empty: " + sheetName);
        }

        int testCaseColumnIndex = -1;
        Iterator<Cell> cellIterator = headerRow.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase("Testcases")) {
                testCaseColumnIndex = cell.getColumnIndex();
                break;
            }
        }
        if (testCaseColumnIndex == -1) {
            throw new IllegalArgumentException("Testcases column not found in sheet: " + sheetName);
        }

        // Find the row containing the test case data.
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next(); // skip header row
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Cell testCaseCell = row.getCell(testCaseColumnIndex);
            if (testCaseCell != null && testCaseCell.getCellType() == CellType.STRING && testCaseCell.getStringCellValue().equalsIgnoreCase(testCaseName)) {
                // Found the row containing the test case data.
                ArrayList<String> testData = new ArrayList<String>();
                Iterator<Cell> testDataIterator = row.cellIterator();
                while (testDataIterator.hasNext()) {
                    Cell testDataCell = testDataIterator.next();
                    if (testDataCell.getCellType() == CellType.STRING) {
                        testData.add(testDataCell.getStringCellValue());
                    } else if (testDataCell.getCellType() == CellType.NUMERIC) {
                        testData.add(NumberToTextConverter.toText(testDataCell.getNumericCellValue()));
                    } else {
                        testData.add("");
                    }
                }
                return testData;
            }
        }

        throw new IllegalArgumentException("Test case not found in sheet: " + sheetName);
    }
	
	
    public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
    	
    	//read json to string
        String jsonContent = new String(Files.readAllBytes(Paths.get(filepath)));
        //give UTF_8 deprecated message

        //String to Hashmap using jackson databind
        
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});

        return data;
    }





  public static void main(String[] args) throws IOException { 
	  String filePath = "C:\\Users\\Dhoni\\Downloads\\demodata.xlsx"; String sheetName = "testdata";
  String testCaseName = "Login";
  
  ArrayList<String> testData = getData(filePath, sheetName, testCaseName);
  System.out.println("Test Data for Test Case " + testCaseName + ": " +
  testData); 
  } 
  
  
  //Multidimensional Arrays HashMaps
  
  public static Object[][] testData = new Object[][] {
      // Test Case 1
      {
          // Key-Value pairs for Test Case 1
          new HashMap<String, String>() {{
              put("username", "user1");
              put("password", "pass1");
              put("expectedResult", "success");
          }}
      },
      // Test Case 2
      {
          // Key-Value pairs for Test Case 2
          new HashMap<String, String>() {{
              put("username", "user2");
              put("password", "pass2");
              put("expectedResult", "failure");
          }}
      },
      // Add more test cases with their respective key-value pairs as needed
  };
  
  }
  
