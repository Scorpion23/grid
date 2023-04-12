package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rsa_PageObjects.LoginPage;

public class BaseTest {
	public WebDriver driver;
	
	
	
	
	public WebDriver getDriver() {  //  WebDriver driver = getDriver(); // read from properties file read maven commands
		try {
			Properties testData = new Properties();
			FileInputStream fileInputStream = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\website_Resources\\GlobalData.properties");
			testData.load(fileInputStream);
			String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):testData.getProperty("browser");//java ternary operator  use maven commands to set gloal parameters
			//String browserName = testData.getProperty("browser");
			fileInputStream.close();

			if (browserName.contains("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dhoni\\Desktop\\Drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();// for headless mode
				if(browserName.contains("headless"))
				{options.addArguments("headless");
				}
				driver = new ChromeDriver(options);
				 driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 900));
				
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "C:\\Users\\Dhoni\\Desktop\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();

			} else if (browserName.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver", "C:\\Users\\Dhoni\\Desktop\\Drivers\\msedgedriver.exe");
				driver = new EdgeDriver();

			} else {
				System.out.println("Unsupported browser: " + browserName);

			}
			driver.manage().window().maximize();
			// here we can write the code for opening website use the driver to navigate to
			// a web page and perform tests

		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
		return driver;
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {  // json - string - hasmap 

	    String jsonContent = new String(Files.readAllBytes(Paths.get(filepath)));  // Use double slashes to escape backslashes in file path

	    ObjectMapper mapper = new ObjectMapper();
	    List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
	    return data;
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir" + "//reports//"+testCaseName + ".png"));
		FileUtils.copyFile(Source, file);
		
		return System.getProperty("user.dir") + "//reports//"+ testCaseName + ".png";//returns path
	}
	
	
	@BeforeMethod(alwaysRun = true)
	
	public LoginPage returnloginpage() {

		driver = getDriver();

		 LoginPage loginpage= new LoginPage(driver);   // *** this will activate the variable in line 19 public LoginPage loginpage;
		 loginpage.geturl();

		return loginpage;// an instance of login page

	}
	
	
	@AfterMethod(alwaysRun = true)
	
	public void tearDown() {
		
		driver.close();
	}
	
	

}
