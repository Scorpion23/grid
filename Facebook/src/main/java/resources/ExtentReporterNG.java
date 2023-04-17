package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporterNG  {
	
	public  static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"//reports//Spark.html";
		 ExtentReports extent = new ExtentReports(); 
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		extent.attachReporter(reporter);
		
		reporter.config().setReportName("Web Automation Results");// set report name
		reporter.config().setDocumentTitle("Test Results");// set document title
		  // Object of extent reports	
		// ATTACH THE meta data to the object
		extent.setSystemInfo("Tester", "Sachin");	
		return extent;
	}
	
	


	
	
	
	
}



		

