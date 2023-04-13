package rsa_PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rsa_AbstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents{
	
	WebDriver driver;

	public  LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id ="userEmail") private WebElement email;
	
	@FindBy(id ="userPassword") private WebElement passsword;
	@FindBy(id ="login") private WebElement loginbutton;
	
	
	
	public DashboardPage getloginpage(String emailid,String password) {
	driver.get("https://rahulshettyacademy.com/client/");
	email.sendKeys(emailid);
	passsword.sendKeys(password);
	loginbutton.click();
	DashboardPage dashboardpage = new DashboardPage(driver);
	return dashboardpage;
	}
	
	
	public void geturl(){
		
	driver.get("https://rahulshettyacademy.com/client");
	
	
	}
	
	
	
	
}
