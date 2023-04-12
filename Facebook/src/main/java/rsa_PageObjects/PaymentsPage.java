package rsa_PageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import rsa_AbstractComponents.AbstractComponents;

public class PaymentsPage extends AbstractComponents {
	
	WebDriver driver;
	
	public PaymentsPage (WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(className  = "ddl") private WebElement monthdrop;
	
	@FindBy(xpath  = "//div[1]/select[2]") private WebElement yeardrop;
	
	@FindBy(css="[value='4542 9931 9292 2293']") private WebElement creditcardfield;
	@FindBy(xpath  ="(//input[@type='text'])[2]") private WebElement cvv;
	@FindBy(xpath  ="(//input[@type='text'])[3]") private WebElement name;
	@FindBy(xpath  ="//button[text()='Apply Coupon']") private WebElement applycoupon;
	@FindBy(css="[name='coupon']") private WebElement coupontext;
	@FindBy(xpath="//div[@aria-label='Please Enter Coupon']") private WebElement couponerror;
	@FindBy(css="input[placeholder='Select Country']") private WebElement countryelement;
	@FindBy(css=".ta-results") private WebElement countrylistbox;
	@FindBy(css=".ta-results") private WebElement countrylist;
	@FindBy(css="button[class*='list-group-item']") private List<WebElement> country;
	@FindBy(className ="action__submit" ) private WebElement placeorder;
	
	public void fillpaymentdetails(int month,int year, String creditcardno) {
		
		Select objSelect = new Select(monthdrop);
		objSelect.selectByIndex(month);

		Select objSelect2 = new Select(yeardrop);
		objSelect2.selectByIndex(year);

		creditcardfield.sendKeys("9856856985214563");
		cvv.sendKeys("254");
		name.sendKeys("Dhoni");

		countryelement.sendKeys("ind");
		super.waituntilvisibility(countryelement);

		List<WebElement> countrySelected = country.stream()
				.filter(countryl -> countryl.getText().equalsIgnoreCase("India")).collect(Collectors.toList());

		countrySelected.get(0).click();
		super.waituntilinvisibility(couponerror);
		placeorder.click();
		
	}
	
	
	
			public String  assertcoupon() {
			
				coupontext.sendKeys("welcome");
			
			applycoupon.click();
			super.waituntilvisibility(couponerror);
			String coupon = couponerror.getText();
			
			System.out.println(coupon);
			return coupon;
			
			
			}
			


	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	

