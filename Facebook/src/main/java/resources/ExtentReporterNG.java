package resources;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import org.testng.TestNG;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReporterNG  {
	
	public  static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"//reports//Spark.html";
		 ExtentReports extent = new ExtentReports(); 
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		extent.attachReporter(spark);
		
		  spark.config().setReportName("Web Automation Results"); // report name
		    spark.config().setDocumentTitle("Test Results"); // document title
		    spark.config().setTheme(Theme.DARK);
		    spark.config().setTimelineEnabled(true);
		    
		    String osName = System.getProperty("os.name"); // Get OS name from system property
			/*
			 * Capabilities cap = ((RemoteWebDriver) driver).getCapabilities(); String
			 * browserName = cap.getBrowserName(); String browserVersion = (String)
			 * cap.getCapability("browserVersion");
			 */
		
		    extent.setSystemInfo("Platform", osName);
		    extent.setSystemInfo("Java Version : ", System.getProperty("java.version"));
		    spark.config().setReportName("Web Automation Results");// set report name
		    spark.config().setDocumentTitle("Test Results");// set document title
		  // Object of extent reports	
		// ATTACH THE meta data to the object
		extent.setSystemInfo("Tester", "Sachin");	
		return extent;
	}
	
	public static ExtentReports extentReportsConfig() {
		

	    String path = System.getProperty("user.dir") + "//reports//Spark.html";
	    ExtentReports extent = new ExtentReports();
	    ExtentSparkReporter spark = new ExtentSparkReporter(path);

	    spark.config().setReportName("Web Automation Results"); // report name
	    spark.config().setDocumentTitle("Test Results"); // document title
	    spark.config().setTheme(Theme.DARK);
	    spark.config().setTimelineEnabled(true);
	    extent.attachReporter(spark);

	    String browserName = System.getProperty("browserName"); // Get browser name from system property
	    String browserVersion = System.getProperty("browserVersion"); // Get browser version from system property

	    String osName = System.getProperty("os.name"); // Get OS name from system property

	    extent.setSystemInfo("Browser", browserName);
	    extent.setSystemInfo("Browser Version", browserVersion);
	    extent.setSystemInfo("Platform", osName);
	    extent.setSystemInfo("Tester", "Sachin");
	    extent.setSystemInfo("Resolution", getscreenResolution());
	    extent.setSystemInfo("Java Version : ", System.getProperty("java.version"));
	    extent.setSystemInfo("OS Version", System.getProperty("os.version"));

	    extent.createTest("MyFirstTest").log(Status.INFO, "This is a logging event for MyFirstTest");
	    return extent;
	}


	public static  String  getscreenResolution() {
		
		
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



		

