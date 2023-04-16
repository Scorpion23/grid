package rsa_Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import rsa_PageObjects.DashboardPage;
import testComponents.BaseTest;

public class ErrorValidations extends BaseTest {


	String email = "dhoni@yopmail.com";
	String password = "Sachin@123";
	String product ="adidas";



	@Test
	public void correctlogin() {

		DashboardPage dashboard = loginpage.getloginpage(email, password);
		dashboard.waituntillpageload();
		String loginmsg = dashboard.loginassert();
		Boolean log = loginmsg.toLowerCase().contains("fail");
		Assert.assertTrue(log);
		System.out.println("Login Success passed");
		
		

	}
	

	
	



	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
