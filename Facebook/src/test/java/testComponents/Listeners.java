package testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	


	ExtentTest test;

	ExtentReports extent = ExtentReporterNG.getReportObject();/// Object of ExtentReports class

	ThreadLocal<ExtentTest> ObjThreadLocal = new ThreadLocal<ExtentTest>(); // Thread safe; object of ThreadLocal test is pushed into ObjThreadLocal by using set 
	//method and retrieved using get method

	@Override

	public void onTestStart(ITestResult result) {

		// TODO Auto-generated method stub
		

		
		
		test = extent.createTest(result.getMethod().getMethodName());// unique thread id(ErrorValidationTest)->test
		ObjThreadLocal.set(test);//pushing our object into Threadlocal for test object is assigned unique thread id and pushed into thread local
	}//set method pushes the object into ThreadLocal

	@Override

	public void onTestSuccess(ITestResult result) {

		// TODO Auto-generated method stub

		ObjThreadLocal.get().log(Status.PASS, "Test Passed");

	}

	@Override

	public void onTestFailure(ITestResult result) {


		//String filePathBASE64="";
		ObjThreadLocal.get().fail(result.getThrowable());// get method retrieves that object =  test.fail(result.getThrowable());
		
		
	
		String filePath="";
	//1.screenshot 2.Attach to the report

	try {

	driver= (WebDriver) result.getTestClass().getRealClass().getField("driver")

	.get(result.getInstance());



	} catch (Exception e1) {

	// TODO Auto-generated catch block

	e1.printStackTrace();

	}

	
	
	try {


		//filePathBASE64 =takeScreenshotBASE64Driver(driver);
		filePath = getScreenshot(result.getMethod().getMethodName(),driver);

	} catch (IOException e) {

	// TODO Auto-generated catch block

	e.printStackTrace();
	

	}
	
	//	test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

	ObjThreadLocal.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());




	}
	@Override

	public void onTestSkipped(ITestResult result) {

		// TODO Auto-generated method stub

	}

	@Override

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		// TODO Auto-generated method stub
        System.out.println("Test failed but it is in defined success ratio " + result.getMethod().getMethodName());

	}

	@Override

	public void onStart(ITestContext context) {

		// TODO Auto-generated method stub

	}

	@Override

	public void onFinish(ITestContext context) {

		// TODO Auto-generated method stub

		extent.flush();

	}

}