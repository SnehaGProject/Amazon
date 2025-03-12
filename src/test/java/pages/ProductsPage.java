package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

    public WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By addToCartButton = By.xpath("//button[text()='Add to cart']");

    public void addItemToCart(String itemName) {

        driver.findElement(By.xpath("//div[contains(text(),'" + itemName + "')]/ancestor::div[@class='inventory_item_label']/following-sibling::div[1]/div[1]/following-sibling::button"));
    }

    public boolean isOnProductsPage() {
        return driver.findElement(By.className("product_label")).isDisplayed();
    }

}
