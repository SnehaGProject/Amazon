package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class hooks {

    public static WebDriver driver;
    public AmazonPage ap;
    public LoginPage loginPage ;
    public  ProductsPage productsPage ;
    public  CartPage cartPage ;
    public  CheckoutPage checkoutPage;



    @Before
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\12Sep\\12Sep\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://amazon.com/");
        Thread.sleep(20000);
        //driver.get("https://www.saucedemo.com/");
        //driver.get("https://www.flipkart.com");
        //Thread.sleep(3000);
        ap=new AmazonPage(driver);
         loginPage = new LoginPage(driver);
         productsPage = new ProductsPage(driver);
         cartPage = new CartPage(driver);
         checkoutPage = new CheckoutPage(driver);
    }


    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenshot(scenario.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }


    public void takeScreenshot(String scenarioName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);  // Capture the screenshot
        try {

            Path destination = Path.of("C:\\CucumberProject\\AmazonFramework\\screenshots", scenarioName + ".png");
            Files.createDirectories(destination.getParent());
            Files.move(source.toPath(), destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
