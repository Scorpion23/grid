package rsa_PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rsa_AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	
	WebDriver driver;
	
	public ConfirmationPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css  = "[class='hero-primary']") private WebElement thankyouelement;
	@FindBy(xpath  ="//div[text()=' Logout Successfully ']") private WebElement logoutelement;
	
	public String assertconfirm () {	
		String thankyou = thankyouelement.getText();
		System.out.println(thankyou);
		return thankyou;
	}
	
	
	public void signout() {
		super.signout();
	}
	
	
	public String assertLogout() {
		
		super.waituntilvisibility(logoutelement);
		String logoutmsg = logoutelement.getText();
		System.out.println(logoutmsg);
		return logoutmsg;
	}
	
	
}
