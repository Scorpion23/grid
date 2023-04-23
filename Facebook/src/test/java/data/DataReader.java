package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
	
	
	public ArrayList<String> getData(String filePath, String sheetName, String testCaseName) throws IOException {
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

		// skip header row
		Iterator<Row> rowIterator = sheet.rowIterator();
		
		
		rowIterator.next(); // skip header row
		
		
		
		
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();// we are in the next row after skipping the header
			
			// Find the row containing the testCaseName data.
			Cell testCaseCell = row.getCell(testCaseColumnIndex); 
			if (testCaseCell != null && testCaseCell.getCellType() == CellType.STRING && testCaseCell.getStringCellValue().equalsIgnoreCase(testCaseName)) {
				
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
				return testData;//ArrayList 
			}
		}

		throw new IllegalArgumentException("Test case not found in sheet: " + sheetName);
	}
    


	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {

		// read json to string
		String jsonContent = new String(Files.readAllBytes(Paths.get(filepath)));
		// give UTF_8 deprecated message

		// String to Hashmap using jackson databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}


	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;   
	String path;
	
	public DataReader(String path)
	{
		this.path=path;
	}
		
	public int getRowCount(String sheetName) throws IOException 
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;		
	}
	

	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	
	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try{
		data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	


	public   void main(String[] args) throws IOException {
		String filePath = System.getProperty("user.dir") +"//src//test//java//data//demodata.xlsx";
		String sheetName = "Registrationpage";
		String testCaseName = "Register";

		ArrayList<String> testData = getData(filePath, sheetName, testCaseName);
		System.out.println("Test Data for Test Case " + testCaseName + ": " + testData);
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
  
