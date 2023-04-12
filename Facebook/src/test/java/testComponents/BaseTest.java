package testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

}
