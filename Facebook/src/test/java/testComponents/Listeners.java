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

	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread safe

	@Override

	public void onTestStart(ITestResult result) {

		// TODO Auto-generated method stub

		test = extent.createTest(result.getMethod().getMethodName());// unique thread id(ErrorValidationTest)->test

	}

	@Override

	public void onTestSuccess(ITestResult result) {

		// TODO Auto-generated method stub

		test.log(Status.PASS, "Test Passed");

	}

	@Override

	public void onTestFailure(ITestResult result) {

		// TODO Auto-generated method stub

		test.fail(result.getThrowable());

		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String testMethodName = result.getMethod().getMethodName();

		String filePath = null;

		try {

			filePath = getScreenShot (result.getMethod().getMethodName(), driver);

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}
		
		test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());

		// Screenshot, Attach to report

	}

	@Override

	public void onTestSkipped(ITestResult result) {

		// TODO Auto-generated method stub

	}

	@Override

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

		// TODO Auto-generated method stub

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