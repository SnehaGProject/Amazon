package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;

public class CartPage {

    public WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By cartIcon = By.className("shopping_cart_link");
    By checkoutButton = By.id("checkout");

    public void proceedToCheckout() {
        driver.findElement(cartIcon).click();
        driver.findElement(checkoutButton).click();
    }

    public void removeAllItems() {
        driver.findElements(By.xpath("//button[text()='Remove']")).forEach(WebElement ::click);
    }

}
