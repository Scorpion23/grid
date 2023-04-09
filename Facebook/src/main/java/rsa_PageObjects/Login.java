package rsa_PageObjects;

import org.openqa.selenium.WebDriver;

import rsa_AbstractComponents.AbstractComponents;

public class Login extends AbstractComponents{
	
	WebDriver driver;

	public Login(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
	}

}
