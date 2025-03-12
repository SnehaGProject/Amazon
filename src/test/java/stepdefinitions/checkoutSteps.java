package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class checkoutSteps {
    WebDriver driver=hooks.driver;
    LoginPage loginPage = new LoginPage(driver);
    ProductsPage productsPage = new ProductsPage(driver);
    CartPage cartPage = new CartPage(driver);
    CheckoutPage checkoutPage = new CheckoutPage(driver);

    @Given("^User is on SwagLabs login page$")
    public void user_is_on_swaglabs_login_page() throws InterruptedException {
//        driver.get("https://www.saucedemo.com/");
//        Thread.sleep(3000);
    }

    @When("^User logs in with valid credentials$")
    public void user_logs_in_with_valid_credentials() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @Given("^User adds \"([^\"]*)\" to the cart$")
    public void user_adds_item_to_cart(String itemName) throws InterruptedException {
        productsPage.addItemToCart(itemName);
        Thread.sleep(3000);
    }

    @Given("^User removes all items from the cart$")
    public void user_removes_all_items_from_the_cart() {
        cartPage.removeAllItems();
    }

    @Given("^User proceeds to checkout$")
    public void user_proceeds_to_checkout() {
        cartPage.proceedToCheckout();
    }

    @When("^User enters \"([^\"]*)\" as \"([^\"]*)\", \"([^\"]*)\" as \"([^\"]*)\", and \"([^\"]*)\" as \"([^\"]*)\"$")
    public void user_enters_personal_details(String firstName, String fValue, String lastName, String lValue, String postalCode, String pValue) {
        checkoutPage.enterPersonalDetails(fValue, lValue, pValue);
    }

    @When("^User completes the checkout$")
    public void user_completes_the_checkout() throws InterruptedException {
        checkoutPage.finishCheckout();
    }

    @Then("^User should see the confirmation message \"([^\"]*)\"$")
    public void user_should_see_confirmation_message(String message) {
        Assert.assertTrue(checkoutPage.getConfirmationMessage().contains(message));
    }

    @Then("^User should see an error message \"([^\"]*)\"$")
    public void user_should_see_error_message(String message) {
        Assert.assertTrue(checkoutPage.getErrorMessage().contains(message));
    }

    @Then("^User should see the correct total price including taxes$")
    public void user_should_see_correct_total_price_including_taxes() {
        double totalPrice = checkoutPage.getTotalPrice();
        Assert.assertEquals(totalPrice, checkoutPage.getExpectedTotalPrice(),0.00);
        System.out.println(checkoutPage.getPriceText());
    }

    @When("^User cancels the checkout$")
    public void user_cancels_the_checkout() {
        checkoutPage.cancelCheckout();
    }

    @Then("^User should return to the products page$")
    public void user_should_return_to_products_page() {
        Assert.assertTrue(productsPage.isOnProductsPage());
    }

    @And("user clicks on continue")
    public void userClicksOnContinue() {
        checkoutPage.clickContinue();
    }

    @When("User enters last name as {string} and postal code as {string}")
    public void userEntersLastNameAsAndPostalCodeAs(String ln, String pc) {
        driver.findElement(By.id("last-name")).sendKeys(ln);
        driver.findElement(By.id("postal-code")).sendKeys(pc);

    }

    @When("User enters first name as {string} and postal code as {string}")
    public void userEntersFirstNameAsAndPostalCodeAs(String fn, String pc) {
        driver.findElement(By.id("first-name")).sendKeys(fn);
        driver.findElement(By.id("postal-code")).sendKeys(pc);
    }

    @When("User enters first name as {string} and last name as {string}")
    public void userEntersFirstNameAsAndLastNameAs(String fn, String ln) {
        driver.findElement(By.id("first-name")).sendKeys(fn);
        driver.findElement(By.id("last-name")).sendKeys(ln);
    }
}
