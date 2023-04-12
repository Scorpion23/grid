package rsa_AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
	WebDriver driver;
	
	WebDriverWait wait;// declare but do not initialize because the time may vary for different webelements
	
	public AbstractComponents(WebDriver driver) {
		
		this.driver=driver;
	}
	
	public void waituntilvisibility(WebElement webelement) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(webelement));
	}
	
	
	public void waituntilinvisibility(WebElement webelement) {

		wait.until(ExpectedConditions.invisibilityOf(webelement));

	}
	
	
	public void waituntillvisibilitytrycatch(WebElement webelement) {
		
		 wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(ExpectedConditions
					.visibilityOf(webelement));
		} catch (Exception e) {
			// Handle the exception here
			System.out.println("Exception caught: " + e.getMessage());
		}
		
	}
	
	
	
	
	

	
	
	public void checkreadyState() {
		
		
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
	}
	
	
	public void clickcartbutton(WebElement webelement) {
		
		waituntilinvisibility(webelement);
		webelement.click();
		
	}
	




	public void readyState() {
        wait.until(driver -> {
            JavascriptExecutor j = (JavascriptExecutor) driver;
            return j.executeScript("return document.readyState").toString().equals("complete");
        });
        System.out.println("Page has loaded");
    }
	    
	

	
	public void signout() {
		
		driver.findElement(By.cssSelector(".fa-sign-out")).click();
	}

}
