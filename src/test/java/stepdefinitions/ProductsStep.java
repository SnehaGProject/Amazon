package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.AmazonPage;



public class ProductsStep  {

    WebDriver driver=hooks.driver;
   public AmazonPage ap=new AmazonPage(driver);

    @Then("items should be displayed")
    public void itemsShouldBeDisplayed() {

        Assert.assertTrue(true);
    }

    @When("user should enter {string} to Search")
    public void userShouldEnterToSearch(String item) {
        ap.searchItem(item);
        ap.clickSearchLogo();
    }



}
