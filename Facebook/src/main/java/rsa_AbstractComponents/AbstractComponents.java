package rsa_AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AbstractComponents {
	
	WebDriver driver;
	
	
	public AbstractComponents(WebDriver driver) {
		
		this.driver=driver;
	}
	
	public void signout() {
		
		driver.findElement(By.cssSelector(".fa-sign-out")).click();
	}

}
