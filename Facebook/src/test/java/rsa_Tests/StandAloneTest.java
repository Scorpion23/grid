package rsa_Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import testComponents.BaseTest;

public class StandAloneTest extends BaseTest{

	@Test
	public  void standalone() throws InterruptedException, IOException {
		
		HashMap<String, String> testCase1Data = (HashMap<String, String>) testData[0][0];
        String email = testCase1Data.get("email");
        String password = testCase1Data.get("password");
        String product = testCase1Data.get("product");
        String country = testCase1Data.get("country");
        WebDriver driver = getDriver();
		



		// System.setProperty("webdriver.chrome.driver","D:\\Grid\\chromedriver.exe");
		// WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();
		System.out.println(driver.manage().window().getSize());
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys(email);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(ExpectedConditions
					.visibilityOf(driver.findElement(By.cssSelector("[aria-label='Login Successfully']"))));
		} catch (Exception e) {
			// Handle the exception here
			System.out.println("Exception caught: " + e.getMessage());
		}
		System.out.println("waiting");

		// String LoginSuccess = driver.findElement(By.cssSelector("[aria-label='Login
		// Successfully']")).getText();
		// System.out.println(LoginSuccess);
		// wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@id=\"toast-container\"]/div/div"))));

		// Assert.assertEquals(LoginSuccess, "Login Successfully");

		/*
		 * Actions actions = new Actions(driver);
		 * 
		 * for (int i = 0; i < pics.size(); i++) { if
		 * (pics.get(i).getText().toLowerCase().contains(product)) { WebElement pic =
		 * pics.get(i);
		 * 
		 * actions.contextClick(pic) .moveByOffset(60, 20)
		 * 
		 * .sendKeys(Keys.ENTER) .build() .perform();\
		 * 
		 * break; } else { System.out.println("Incorrect code"); } }
		 */
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
		System.out.println(link);
		driver.switchTo().window(originalWindow);

		List<WebElement> cartbodies = driver.findElements(By.cssSelector(".mb-3"));
		for (int i = 0; i < cartbodies.size(); i++) {
			if (cartbodies.get(i).getText().toLowerCase().contains(product)) {
				WebElement dproduct = cartbodies.get(i);
				dproduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				break;
			}
		}

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//ngx-spinner"))));
		
		WebElement productaddedtocartsuccessfully = driver
				.findElement(By.xpath("//div[@aria-label='Product Added To Cart']"));
		String prdtaddedtcart = productaddedtocartsuccessfully.getText();
		
		System.out.println(prdtaddedtcart);
		
		
		wait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()=' Product Added To Cart ']")));
		Assert.assertEquals(prdtaddedtcart, "Product Added To Cart");

		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		
        try {
            // Check the document.readyState property using JavascriptExecutor
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            boolean isPageLoaded = false;
            int timeout = 10; // Timeout in seconds
            int counter = 0;

            while (!isPageLoaded && counter < timeout) {
                // Execute JavaScript to check the document.readyState property
                isPageLoaded = (boolean) jsExecutor.executeScript("return document.readyState === 'complete';");
                if (!isPageLoaded) {
                    // Sleep for 1 second if page is not loaded yet
                    Thread.sleep(1000);
                    counter++;
                }
            }

            if (isPageLoaded) {
                System.out.println("Page has loaded fully.");
            } else {
                System.out.println("Page has not loaded fully within timeout.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
		List<WebElement> cartlist = driver.findElements(By.cssSelector(".cartWrap"));

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cartWrap")));

		for (int i = 0; i < cartlist.size(); i++) {
			if (cartlist.get(i).getText().toLowerCase().contains(product)) {
				System.out.println(product);
				driver.findElement(By.xpath("//button[text()='Checkout']")).click();

				break;

			} else {
				System.out.println("product not found");
			}

		}

		// Select objSelect = new
		// Select(driver.findElement(By.xpath("//div[1]/select[1]")));
		/*
		 * Select objSelect = new
		 * Select(driver.findElement(By.xpath("//div[1]/select[1]")));
		 * objSelect.selectByIndex(9);
		 * 
		 * Select objSelect2 = new
		 * Select(driver.findElement(By.xpath("//div[1]/select[2]")));
		 * objSelect2.selectByIndex(22);
		 * 
		 * //Actions mouse2 = new Actions(driver); //WebElement creditcardno
		 * =driver.findElement(By.cssSelector("[value='4542 9931 9292 2293']"));
		 * //mouse2.clickAndHold(creditcardno).sendKeys(Keys.chord(Keys.CONTROL, "a"),
		 * "1234 1234 1234 1234").build().perform();
		 * 
		 * driver.findElement(By.cssSelector("[value='4542 9931 9292 2293']")).sendKeys(
		 * Keys.chord(Keys.CONTROL, "a", Keys.DELETE), "1111 2222 3333 4444");
		 * //driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(
		 * "9856856985214563");
		 * driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("254");
		 * driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("Dhoni");
		 * 
		 * 
		 */
		
		/*
		 * driver.findElement(By.cssSelector("[name='coupon']")).sendKeys("welcome");
		 * 
		 * driver.findElement(By.xpath("//button[text()='Apply Coupon']")).click();
		 * WebElement incorrectC =
		 * driver.findElement(By.xpath("//div[@aria-label='Please Enter Coupon']"));
		 * 
		 * wait.until(ExpectedConditions.visibilityOf(incorrectC)); String coupon =
		 * incorrectC.getText(); System.out.println(coupon);
		 * wait.until(ExpectedConditions.invisibilityOf(incorrectC));
		 */

		

		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		List<WebElement> countryList = driver.findElements(By.cssSelector("button[class*='list-group-item']"));

		List<WebElement> countrySelected = countryList.stream().filter(countryl -> countryl.getText().equalsIgnoreCase("India")).collect(Collectors.toList());

		countrySelected.get(0).click();
		
		/*
		 * Actions action = new Actions(driver); action.moveByOffset(1555,
		 * 182).click().build().perform(); action.moveByOffset(1530,
		 * 400).click().build().perform();
		 */

		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' India']")));
		//driver.findElement(By.xpath("//span[text()=' India']")).click();


		
		driver.findElement(By.className("action__submit")).click();
		

		String thankyou = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(thankyou);
		Assert.assertTrue(thankyou.toLowerCase().contains("thankyou"));
		
		
		

		driver.findElement(By.cssSelector(".fa-sign-out")).click();
		
		
		

	         WebElement logout = driver.findElement(By.xpath("//div[text()=' Logout Successfully ']"));
				wait.until(ExpectedConditions.visibilityOf(logout));
				String out = logout.getText();
				System.out.println(out);
		
		

		driver.quit();

	}
	
	
	public static Object[][] testData = new Object[][] {
        // Test Case 1
        {
            // Key-Value pairs for Test Case 1
            new HashMap<String, String>() {{
                put("email", "dhoni@yopmail.com");
                put("password", "Sachin@123");
                put("product", "zara");
                put("country", "India");
            }}
        },
        // Test Case 2
        {
            // Key-Value pairs for Test Case 2
            new HashMap<String, String>() {{
                put("username", "user2");
                put("password", "pass2");
                put("expectedResult", "failure");
            }}
        },
        // Add more test cases with their respective key-value pairs as needed
    };

}
