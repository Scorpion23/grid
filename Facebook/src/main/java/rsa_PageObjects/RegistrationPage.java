package rsa_PageObjects;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

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
	
	@FindBy(xpath="//span[text()='Male']") private WebElement gender;
	@FindBy(css="[formcontrolname='occupation']") private WebElement occupation;
	@FindBy(css="[type='checkbox']") private WebElement checkbox;
	@FindBy(id="login")private WebElement Registerbtn;
	@FindBy(xpath ="//div[text()='Registered Successfully']") private WebElement registrationmsg;
	@FindBy(xpath ="//h1[text()='Account Created Successfully']") private WebElement Successful;
	@FindBy(id  ="login") private WebElement loginbutton;
	
	

	
	
	@FindBy(id  ="lastName") private WebElement Alastnamee;
	
	@FindBy(xpath  ="//div[starts-with(@aria-label,'Please')]") private WebElement AInvalidPassword;
	
	@FindBy(xpath = "//div[text()='*Please check above checkbox']") private WebElement Echeckbox;
	
	
			
	
	
	
	
	
	

	
	
	
	public void fillregistrationform(String firstname,String lastname,String email, String mobileno,String password, String confirmpassword) {
		
		
		firstnamee.sendKeys(firstname);
		lastnamee.sendKeys(lastname);
		emaile.sendKeys(email);
		mobilenoe.sendKeys(mobileno);
		Select dropdown = new Select(occupation);
		dropdown.selectByIndex(1);
		gender.click();
		passworde.sendKeys(password);
		confirmPassword.sendKeys(confirmpassword);
		checkbox.click();
		Registerbtn.click();
		loginbutton.click();
		String successmsg = Successful.getText();
		
		Assert.assertTrue(successmsg.toLowerCase().contains("Successfully"));
		
		
	}
	
	String message;
	
	
	@FindBy(xpath  ="//div[starts-with(@aria-label,'*First')]") private WebElement Efirstnamee;
	
	//@FindBy(xpath  ="//div[text()='*First Name must be 3 or more character long']") private WebElement Afirstnamee;
	@FindBy(xpath  ="//div[starts-with(@aria-label,'First')]") private WebElement Afirstnamee;
	@FindBy(xpath  ="//div[starts-with(@aria-label,'last')]") private WebElement ALastnamee;
	@FindBy(xpath  ="//div[text()='*Email is required']") private WebElement Eemail1;
	@FindBy(xpath  ="//div[text()='*Enter Valid Email']") private WebElement Eemail2;
	@FindBy(xpath  ="//div[text()='*Phone Number is required']") private WebElement Ephoneno1;
	@FindBy(xpath  ="//div[text()='*only numbers is allowed']") private WebElement Ephoneno2;
	@FindBy(xpath  ="//div[text()='*Phone Number must be 10 digit']") private WebElement Ephoneno3;
	@FindBy(xpath  ="//div[text()='*Password is required']") private WebElement EPassword;
	@FindBy(xpath  ="//div[text()='Confirm Password is required']") private WebElement Econfirmpassword;
	@FindBy(xpath  ="//div[text()='Password and Confirm Password must match with each other.']") private WebElement EPasswordmatch;
	
	@FindBy(xpath  ="//div[starts-with(@aria-label,'Password must be')]") private WebElement Apasslength;
	@FindBy(xpath  ="//div[starts-with(@aria-label,'Please enter 1')]") private WebElement Apassformat;
	@FindBy(xpath  ="//div[starts-with(@aria-label,'User already exisits')]") private WebElement DuplicateUser;
	
	@Test(testName = "pulltext")
	
	public void TestScenarios ( String firstname,String lastname,String email, String mobileno, String password, String confirmpassword,String text) {
		
		
		firstnamee.sendKeys(firstname);
		lastnamee.sendKeys(lastname);
		emaile.sendKeys(email);
		mobilenoe.sendKeys(mobileno);
		Select dropdown = new Select(occupation);
		dropdown.selectByIndex(1);
		gender.click();
		passworde.sendKeys(password);
		confirmPassword.sendKeys(confirmpassword);
		checkbox.click();
		Registerbtn.click();
	
		if (text.toLowerCase().contains("first")) {System.out.println(Efirstnamee.getText());
			Assert.assertTrue(Efirstnamee.getText().toLowerCase().contains("*First Name is required"));
		} else if (text.toLowerCase().contains("second")) {System.out.println(Afirstnamee.getText());
			Assert.assertTrue(Afirstnamee.getText().toLowerCase().contains("*First Name must be 3 or more character long"));
		} else if (text.toLowerCase().contains("third")) {System.out.println(ALastnamee.getText());
			Assert.assertTrue(ALastnamee.getText().toLowerCase().contains("Last Name is required!"));
		} else if (text.toLowerCase().contains("fourth")) {System.out.println(ALastnamee.getText());
			Assert.assertTrue(ALastnamee.getText().toLowerCase().contains("last Name must be 3 to 20 characters long!"));
		} else if (text.toLowerCase().contains("fifth")) {System.out.println(Eemail1.getText());
			Assert.assertTrue(Eemail1.getText().toLowerCase().contains("*Email is required"));
		} else if (text.toLowerCase().contains("sixth")) {System.out.println(Eemail2.getText());
			Assert.assertTrue(Eemail2.getText().toLowerCase().contains("*Enter Valid Email"));
		} else if (text.toLowerCase().contains("seventh")) {System.out.println(Ephoneno1.getText());
			Assert.assertTrue(Ephoneno1.getText().toLowerCase().contains("*Phone Number is required"));
		} else if (text.toLowerCase().contains("eighth")) {System.out.println(Ephoneno2.getText());
			Assert.assertTrue(Ephoneno2.getText().toLowerCase().contains("*only numbers is allowed"));
		} else if (text.toLowerCase().contains("ninth")) {System.out.println(Ephoneno2.getText());
			Assert.assertTrue(Ephoneno2.getText().toLowerCase().contains("*only numbers is allowed"));
		} else if (text.toLowerCase().contains("tenth")) {System.out.println(Ephoneno3.getText());
			Assert.assertTrue(Ephoneno3.getText().toLowerCase().contains("*Phone Number must be 10 digit"));
		} else if (text.toLowerCase().contains("eleventh")) {System.out.println(Ephoneno3.getText());
			Assert.assertTrue(Ephoneno3.getText().toLowerCase().contains("*Phone Number must be 10 digit"));
		} else if (text.toLowerCase().contains("twelfth")) {System.out.println(EPassword.getText());
			Assert.assertTrue(EPassword.getText().toLowerCase().contains("*Password is required"));
		} else if (text.toLowerCase().contains("thirteen")) {System.out.println(Econfirmpassword.getText());
			Assert.assertTrue(Econfirmpassword.getText().toLowerCase().contains("*Confirm Password is required"));
		} else if (text.toLowerCase().contains("fourteen")) {System.out.println(EPasswordmatch.getText());
			Assert.assertTrue(EPasswordmatch.getText().toLowerCase().contains("Password and Confirm Password must match with each other."));
		} else if (text.toLowerCase().contains("fifteen")) {System.out.println(EPasswordmatch.getText());
			Assert.assertTrue(EPasswordmatch.getText().toLowerCase().contains("Password and Confirm Password must match with each other."));
		} else if (text.toLowerCase().contains("sixteen")) {System.out.println(Apasslength.getText());
			Assert.assertTrue(Apasslength.getText().toLowerCase().contains("Password must be 8 Character Long!"));
		} else if (text.toLowerCase().contains("seventeen")) {System.out.println(Apassformat.getText());
			Assert.assertTrue(Apassformat.getText().toLowerCase().contains("Please enter 1 Special Character, 1 Capital 1, Numeric 1 Small"));
		} else if (text.toLowerCase().contains("eighteen")) {System.out.println(DuplicateUser.getText());
			Assert.assertTrue(DuplicateUser.getText().toLowerCase().contains("User already exisits with this Email Id!"));
		}  else if (text.toLowerCase().contains("nnteenth")) {System.out.println(Successful.getText());
			Assert.assertTrue(Successful.getText().toLowerCase().contains("successfully"));
		}
			

	}
			

	
	
}
