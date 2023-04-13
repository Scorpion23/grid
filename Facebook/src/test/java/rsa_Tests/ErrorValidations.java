package rsa_Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import rsa_PageObjects.DashboardPage;
import rsa_PageObjects.LoginPage;
import testComponents.BaseTest;

public class ErrorValidations extends BaseTest {
	
	
	WebDriver driver;
	
	String email = "dhoni@yopmail.com";
	String password = "fgfggfg";
	
    public ErrorValidations(WebDriver driver) {
        this.driver = driver;
    }
	
    
    @Test
	public void correctlogin() {
		LoginPage loginpage = new LoginPage(driver);
		
		DashboardPage dashboard =loginpage.getloginpage(email,password);
		
		
		if (driver instanceof ChromeDriver) {
			dashboard.waituntillpageloadChromiumEngine();
			String loginmsg =dashboard.loginassert();
			Boolean log = loginmsg.toLowerCase().contains("success");
			Assert.assertTrue(log);
			System.out.println("Login Success passed");
			
		}else if (driver instanceof FirefoxDriver) {
			
			dashboard.waituntillpageloadfirefox();
			String loginmsg =dashboard.loginassert();
			Boolean log = loginmsg.toLowerCase().contains("success");
			Assert.assertTrue(log);
			System.out.println("Login Success passed");
			
		}else if (driver instanceof EdgeDriver){
			
			dashboard.waituntillpageloadChromiumEngine();
			String loginmsg =dashboard.loginassert();
			Boolean log = loginmsg.toLowerCase().contains("success");
			Assert.assertTrue(log);
			System.out.println("Login Success passed");
		}else {
			
			System.out.println("Browser not supported");
		}
		
		{
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
