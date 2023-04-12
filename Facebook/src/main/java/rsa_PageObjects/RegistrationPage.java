package rsa_PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import rsa_AbstractComponents.AbstractComponents;

public class RegistrationPage extends AbstractComponents {
	
	WebDriver driver;
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(id  ="firstName") private WebElement firstnamee;
	@FindBy(id  ="lastName") private WebElement lastnamee;
	@FindBy(id  ="userEmail") private WebElement emaile;
	@FindBy(id  ="userMobile") private WebElement mobilenoe;
	@FindBy(id  ="userPassword") private WebElement passworde;
	@FindBy(id  ="confirmPassword") private WebElement confirmPassword;
	@FindBy(id  ="login") private WebElement loginbutton;
	@FindBy(xpath="//span[text()='Male']") private WebElement gender;
	@FindBy(css="[formcontrolname='occupation']") private WebElement occupation;
	@FindBy(css="[type='checkbox']") private WebElement checkbox;
	@FindBy(xpath ="//div[text()='Registered Successfully']") private WebElement registrationmsg;
	
	public void fillregistrationform(String firstname,String lastname,String email, String mobileno,String password, String confirmpassword) {
		
		driver.get("https://rahulshettyacademy.com/client/auth/register");
		
		firstnamee.sendKeys(firstname);
		lastnamee.sendKeys(lastname);
		emaile.sendKeys(email);
		mobilenoe.sendKeys(email);
		Select dropdown = new Select(occupation);
		dropdown.selectByValue("1");
		gender.click();
		passworde.sendKeys(password);
		confirmPassword.sendKeys(confirmpassword);
		checkbox.click();
		loginbutton.click();
		
	}
	
	
	//depends on fillregistrationform
	public String assertregistration() {
		
		super.waituntilvisibility(registrationmsg);
		String registrationmessage = registrationmsg.getText();
		System.out.println(registrationmessage);
		return registrationmessage;
	}
	
	
}
