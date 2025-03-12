package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.cucumber.java.en.*;
import org.junit.Assert;


import java.util.List;


public class FlipkartSteps {


    WebDriver driver=hooks.driver;
    @Given("user navigates to the Flipkart homepage")
    public void user_navigates_to_the_Flipkart_homepage() {


//        WebElement closePopup = driver.findElement(By.xpath("//button[contains(text(),'✕')]"));
//        closePopup.click();
    }

    @When("user searches for a product {string}")
    public void user_searches_for_a_product(String productName) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(productName);
        searchBox.submit();
    }

    @When("user adds the first item to the cart")
    public void user_adds_the_first_item_to_the_cart() throws InterruptedException {
        WebElement item = driver.findElement(By.xpath("//div[@data-id][1]"));
//        Thread.sleep(3000);
//        firstProduct.click();

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",item);
        item.click();



        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        WebElement addToCartButton = driver.findElement(By.xpath("//button[text()='Add to cart']"));
       addToCartButton.click();
        Thread.sleep(3000);
    }

    @When("user proceeds to the cart")
    public void user_proceeds_to_the_cart() throws InterruptedException {
//        WebElement cartIcon = driver.findElement(By.xpath("//a[@href='/viewcart?otracker=PP_GoToCart']"));
//        cartIcon.click();
//        Thread.sleep(3000);
    }

    @Then("user should see the product in the cart")
    public void user_should_see_the_product_in_the_cart() {
        WebElement cartItem = driver.findElement(By.xpath("//img[@class='DByuf4']"));
        Assert.assertTrue(cartItem.isDisplayed());
    }

    @When("user proceeds to checkout")
    public void user_proceeds_to_checkout() {
        WebElement checkoutButton = driver.findElement(By.xpath("//button[contains(text(),'Place Order')]"));
        checkoutButton.click();
    }

    @Then("user verifies the total price")
    public void user_verifies_the_total_price() {
        WebElement totalPrice = driver.findElement(By.xpath("//span[@class='LAlF6k re6bBo']")); // Update the correct XPath
        String price = totalPrice.getText();
        Assert.assertTrue(price != null && !price.isEmpty());
    }

    @When("user removes the item from the cart")
    public void user_removes_the_item_from_the_cart() {
        WebElement removeButton = driver.findElement(By.xpath("//div[text()='Remove']"));
        removeButton.click();
        WebElement confirmRemove = driver.findElement(By.xpath("//div[text()='Remove']"));
        confirmRemove.click();
    }

    @Then("user should see the cart is empty")
    public void user_should_see_the_cart_is_empty() {
        WebElement emptyCartMessage = driver.findElement(By.xpath("//div[contains(text(),'Your cart is empty')]"));
        Assert.assertTrue(emptyCartMessage.isDisplayed());
    }

    @Then("user verifies the correct product is in the cart")
    public void user_verifies_the_correct_product_is_in_the_cart() {
        WebElement cartItem = driver.findElement(By.xpath("//a[contains(text(),'MacBook Pro')]"));
        Assert.assertTrue(cartItem.isDisplayed());
    }

    @When("user types {string} in the search bar")
    public void user_types_in_the_search_bar(String searchText) {
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys(searchText);
    }

    @Then("user should see search suggestions containing {string}")
    public void user_should_see_search_suggestions_containing(String suggestionText) {
        List<WebElement> suggestions = driver.findElements(By.xpath("//ul[@class='_1va75j']//li"));
        boolean suggestionFound = suggestions.stream().anyMatch(s -> s.getText().contains(suggestionText));
        Assert.assertTrue(suggestionFound);
    }


}
