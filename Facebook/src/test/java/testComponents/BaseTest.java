package testComponents;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import rsa_PageObjects.LoginPage;

public class BaseTest {
	public   static WebDriver driver;

	public LoginPage loginpage;

	public WebDriver getDriver() { // WebDriver driver = getDriver(); // read from properties file read maven
									// commands
		try {
			Properties testData = new Properties();
			String path = System.getProperty("user.dir") + "//src//main//java//resources//GlobalData.properties";
			
			FileInputStream fileInputStream = new FileInputStream(path);
			testData.load(fileInputStream);
			String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
					: testData.getProperty("browser");// java ternary operator use maven commands to set gloal
														// parameters
			// String browserName = testData.getProperty("browser");
			fileInputStream.close();

			if (browserName.toLowerCase().contains("chrome")) {
				System.setProperty("webdriver.chrome.driver", "D://drivers//chromedriver.exe");
				ChromeOptions options = new ChromeOptions();// for headless mode
				options.getBrowserName();
				options.getBrowserVersion();
				options.getPlatformName();
				if (browserName.contains("headless")) {
					options.addArguments("--headless=new");
				}
				driver = new ChromeDriver(options);

				driver.manage().window().setSize(new org.openqa.selenium.Dimension(1440, 900));
			} 

			else if (browserName.toLowerCase().contains("firefox")) {
				System.setProperty("webdriver.gecko.driver", "D://drivers//geckodriver.exe");

				FirefoxOptions options = new FirefoxOptions();
				// if (browserName.contains("headless")) {
				// options.addArguments("--headless=new");
				// } deprecated

				driver = new FirefoxDriver(options);
				driver.manage().window().maximize();

			}

			else if (browserName.toLowerCase().contains("edge")) {
				System.setProperty("webdriver.edge.driver", "D://drivers//msedgedriver.exe");
				driver = new EdgeDriver();
				driver.manage().window().maximize();
			} else {
				System.out.println("Unsupported browser: " + browserName + "Check driverinfo in properties file");

			}

			// here we can write the code for opening website use the driver to navigate to
			// a web page and perform tests

		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
		return driver;
	}

	
	  @BeforeMethod(alwaysRun = true)
	  
	  public LoginPage returnloginpage() {
	  
	  driver = getDriver();
	  
	  loginpage = new LoginPage(driver); // *** this will activate the variable in line 19  LoginPage loginpage;
	   loginpage.geturl();
	  
	  return loginpage;// an instance of login page
	  
	  }
	 

	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException { // json - string -
																								// hasmap

		String jsonContent = new String(Files.readAllBytes(Paths.get(filepath))); // Use double slashes to escape
																					// backslashes in file path

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}
	
	

	public String getScreenShot(String MethodName,WebDriver driver) throws IOException 
	
	

	{
		TakesScreenshot ts = (TakesScreenshot) driver;// casting
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//FailedTestsScreenshots//" + MethodName + ".png");
		String Screenshotpath = System.getProperty("user.dir") + "//FailedTestsScreenshots//" + MethodName + ".png";
		FileUtils.copyFile(source, file);
		return Screenshotpath;

	}
	

	
	/*
	 * public String CaptureScreen(String imageName,WebDriver driver ) { File
	 * sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 * String encodedBase64 = null; FileInputStream fileInputStreamReader = null;
	 * try { fileInputStreamReader = new FileInputStream(sourceFile); byte[] bytes =
	 * new byte[(int)sourceFile.length()]; fileInputStreamReader.read(bytes);
	 * encodedBase64 = new String(Base64.encodeBase64(bytes));
	 * 
	 * String screenShotDestination = System.getProperty("user.dir") +
	 * "//FailedTestsScreenshots//" + imageName + ".png"; File destination = new
	 * File(screenShotDestination); FileUtils.copyFile(sourceFile, destination);
	 * 
	 * } catch (IOException e) { e.printStackTrace(); } return
	 * "data:image/png;base64,"+encodedBase64; }
	 */




	

	// @AfterMethod(alwaysRun = true)
//@AfterTest

	public void tearDown() {

		driver.close();
	}
	

	public void ExtentReportsConfig() // Extent Reports File Name Document Tile same as page title Tester Name
	{
	    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yy hh:mm:ss aa");
	    Date date = new Date(0);
	    String dateStr = format.format(date);
	    
		String path = System.getProperty("user.dir") + "//Reports//Spark.html";
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
	
		spark.config().setReportName("Web Automation Results");// report name
		spark.config().setDocumentTitle("Test Results");// document title
		spark.config().setTheme(Theme.DARK);
		spark.config().setTimelineEnabled(true);
		extent.attachReporter(spark);
		
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName();
		String browserVersion = (String) cap.getCapability("browserVersion");
		
		String osName1 = System.getProperty("os.name");
		
		extent.setSystemInfo("Browser", browserName);
		extent.setSystemInfo("Browser Version", browserVersion);
		extent.setSystemInfo("Platform", osName1);
		extent.setSystemInfo("Tester", "Sachin");
		extent.setSystemInfo("Resolution", getscreenResolution());
        extent.setSystemInfo("Java Version : ", System.getProperty("java.version"));
        extent.setSystemInfo("OS Version",System.getProperty("os.version"));
        
		extent.createTest("MyFirstTest").log(Status.INFO, "This is a logging event for MyFirstTest");
		

	}
	
	
    
	

	
	
	
	
	public  String  getscreenResolution() {
		
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] devices = ge.getScreenDevices();
        String screenResolution = "";

        for (GraphicsDevice device : devices) {
            GraphicsConfiguration config = device.getDefaultConfiguration();
            Rectangle bounds = config.getBounds();
            int width = bounds.width;
            int height = bounds.height;
            
             screenResolution = width + "X" + height;
           
	}
		return screenResolution;

}
}
