package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) 
	{
		// child sending driver object to parent class - and it's catching by constructor
		this.driver=driver;
	}
	
	
	public void waitForElementToAppear(By findby) 
	{
		
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	     wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
		
		
	}
	
	//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	public void waitForElementToDisappear(WebElement ele) 
	{
		
		
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 wait.until(ExpectedConditions.invisibilityOf(ele));
		
		
	}
	
	
	

}
