package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReporterNG  {
	
	public  static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir") + "//Reports//Spark.html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");// set report name
		reporter.config().setDocumentTitle("Test Results");// set document title
		 ExtentReports extent = new ExtentReports();  // Object of extent reports	
		extent.attachReporter(reporter);// ATTACH THE meta data to the object
		extent.setSystemInfo("Tester", "Sachin");	
		return extent;
	}

	
	
}

		

