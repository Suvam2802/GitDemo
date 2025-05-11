package rahulshettyacademy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

import java.time.Duration;
import java.util.List;

public class StandAloneTesting {

    public static void main(String[] args) {
        String productName = "ZARA COAT 3";
        String ConfirmationMessage = "THANKYOU FOR THE ORDER.";

        // WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Timeout - Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        
        LandingPage landingpage = new LandingPage(driver);  // Link to page object
        
        landingpage.go_to();
        
        landingpage.LoginApplication("leyvingsuvam7488@gmail.com", "Testing@2802");
        
        
        ProductCatalogue pc = new ProductCatalogue(driver);
        List<WebElement> products = pc.getProductList();
        
		
        
        // PageFactory will not work with in webelemenet.findelement // Interview question
        
        pc.addProductToCart(productName);
        

       // driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();   //My Edit

        // Validate product added to the cart
        List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartproducts.stream()
            .anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));

        Assert.assertTrue(match);

        // Click on Checkout
        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions act = new Actions(driver);
        act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();

       // wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));

        driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
        driver.findElement(By.cssSelector(".action__submit ")).click();

        String confirmationTxt = driver.findElement(By.cssSelector(".hero-primary")).getText();

        Assert.assertTrue(confirmationTxt.equalsIgnoreCase(ConfirmationMessage));

        // Close driver
        driver.quit();
    }
}
