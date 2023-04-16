package rsa_PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rsa_AbstractComponents.AbstractComponents;

public class DashboardPage extends AbstractComponents {

	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail") private WebElement email;
	@FindBy(id = "userPassword") private WebElement passsword;
	@FindBy(id = "login") private WebElement loginbutton;
	@FindBy(css = "[aria-label='Login Successfully']") private WebElement loginsuccess;
	@FindBy(xpath = "//ngx-spinner")private WebElement spinner;
	@FindBy(css = ".mb-3")private List<WebElement> productlists;
	@FindBy(xpath = "//div[@aria-label='Product Added To Cart']") private WebElement productaddedtocart;
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']") private WebElement cartbutton;

	public void waituntillpageload() {

		super.waituntillvisibilitytrycatch(loginsuccess);
	}

	
	public void waituntillpageloadChromiumEngine() {
		super.waituntillvisibilitytrycatch(loginsuccess);

	}

	public String loginassert() {

		String loginmsg = loginsuccess.getText();
		return loginmsg;
	}

	public void Loginsuccess() {

		super.waituntilinvisibility(loginsuccess);
	}

	public String getproductimglink(String product) {

		String originalWindow = driver.getWindowHandle();
		assert driver.getWindowHandles().size() == 1;

		driver.switchTo().newWindow(WindowType.TAB);
		if (product.toLowerCase().contains("product")) {
			driver.get("https://rahulshettyacademy.com/api/ecom/uploads/productImage_1650649434146.jpeg");
		} else if (product.toLowerCase().contains("adidas")) {
			driver.get("https://rahulshettyacademy.com/api/ecom/uploads/productImage_1650649488046.jpg");
		} else {
			driver.get("https://rahulshettyacademy.com/api/ecom/uploads/productImage_1650649561326.jpg");
		}
		String link = driver.findElement(By.xpath("//img")).getAttribute("src");
		driver.switchTo().window(originalWindow);
		return link;
	}

	public void addproducttocart(String product) {

		for (int i = 0; i < productlists.size(); i++) {
			if (productlists.get(i).getText().toLowerCase().contains(product)) {
				WebElement dproduct = productlists.get(i);
				dproduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				break;
			}
		}
	}

	public void waituntillinvisibilityspinner() {

		super.waituntilinvisibility(spinner);

	}
	
	
	public String assertproductadded2cart() {
		String prdtaddedtcart = productaddedtocart.getText();
		return prdtaddedtcart;
	} 
	
	
	
	public void clickoncart() {
	
	super.clickcartbutton(cartbutton);
	}

	
	
}
