package rsa_Tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import rsa_PageObjects.DashboardPage;
import testComponents.BaseTest;

public class ErrorValidations extends BaseTest {


	String email = "dhoni@yopmail.com";
	String password = "Sachin@123";
	String product ="adidas";



	@Test
	public void correctlogin() throws IOException {

		DashboardPage dashboard = loginpage.getloginpage(email, password);
		dashboard.waituntillpageload();
		String loginmsg = dashboard.loginassert();
		Boolean log = loginmsg.toLowerCase().contains("fail");
		Assert.assertTrue(log);
		
		System.out.println("Login Success passed");
		
		

	}
	
	
	public void attach() throws IOException {
	ExtentReports extent = new ExtentReports();
	File file =new File(System.getProperty("user.dir")+"\\ExtentReports\\ereports.html");
	ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
	extent.attachReporter(spark);
	ExtentTest eTest1 =extent.createTest("TestOne");
	eTest1.log(Status.INFO, "TestOne Execution started");
	//eTest1.addScreenCaptureFromBase64String(takeScreenshot(),"Project Page");
	eTest1.addScreenCaptureFromPath(takeScreenshotAndReturnPath("Project Name"), "Title of the screenshot");
	extent.flush();
	Desktop.getDesktop().browse(file.toURI());
	}

	
	



	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
