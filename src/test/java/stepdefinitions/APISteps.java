package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.AmazonPage;

import java.util.ArrayList;
import java.util.List;

public class APISteps {

    WebDriver driver=hooks.driver;


    List<String> productNames = new ArrayList<>();
    public AmazonPage ap=new AmazonPage(driver);

    @Given("I send a GET request to retrieve product data")
    public void iSendAGETRequestToRetrieveProductData() {
        Response response = RestAssured.get("https://fakestoreapi.com/products");
        List<String> products = response.jsonPath().getList("title");
        productNames.addAll(products);
    }

    @When("I extract product names from the response")
    public void iExtractProductNamesFromTheResponse() {
        System.out.println("Product names extracted: " + productNames);

        assert !productNames.isEmpty();
    }

    @Then("I dynamically click on each product on the webpage")
    public void iDynamicallyClickOnEachProductOnTheWebpage() {



        for (String productName : productNames) {

            ap.searchItem(productName);
            ap.clickSearchLogo();
            String dynamicXpath = "//div[@data-component-type='s-search-result']";
            WebElement productElement = driver.findElement(By.xpath(dynamicXpath));
            productElement.click();

            driver.navigate().back();
        }

    }

}
