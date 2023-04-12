package rsa_PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rsa_AbstractComponents.AbstractComponents;

public class MyCartPage extends AbstractComponents{
	WebDriver driver;
	
	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartWrap") private List<WebElement> allcartitems;
	
	
	public void checkpageload() {
		super.readyState();
	}
	
	
	public void checkout(String product) {
		
		for (int i = 0; i < allcartitems.size(); i++) {
			if (allcartitems.get(i).getText().toLowerCase().contains(product)) {
				System.out.println("Product added to cart is " +product);
				driver.findElement(By.xpath("//button[text()='Checkout']")).click();

				break;

			} else {
				System.out.println("product not found");
			}

		}
	}

}
