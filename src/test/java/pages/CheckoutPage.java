package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import tech.grasshopper.excel.report.sheets.Sheet;

public class CheckoutPage {
    public WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By postalCodeField = By.id("postal-code");
    By continueButton = By.id("continue");
    By finishButton = By.id("finish");
    By confirmationMessage = By.className("complete-header");
    By errorMessage = By.className("error-message-container");
    By price=By.xpath("//div[@class='summary_subtotal_label']");
    By tax=By.xpath("//div[@class='summary_tax_label']");
    By totalPrice=By.xpath("//div[@class='summary_total_label']");

    public void enterPersonalDetails(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);

    }

    public  void clickContinue(){
        driver.findElement(continueButton).click();
    }

    public void finishCheckout() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(finishButton).click();
    }

    public String getConfirmationMessage() {
        return driver.findElement(confirmationMessage).getText();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }



    public void cancelCheckout() {
        driver.findElement(By.id("cancel")).click();
    }

//    WebElement tax=driver.findElement(By.xpath("//div[@class='summary_tax_label']"));


    public double getTotalPrice() {
        String taxText= driver.findElement(totalPrice).getText();
        String number = taxText.replaceAll("[^0-9]", "");
        double value = Double.parseDouble(number);
        return value;
    }

    public String getPriceText(){
        //price.getText();

        return driver.findElement(price).getText();
    }

    public double returnPrice(){

        String taxText= driver.findElement(tax).getText();
        String number = taxText.replaceAll("[^0-9]", "");
        double value = Double.parseDouble(number);
        return value;
    }

    public double returnTax(){
        String priceText= driver.findElement(price).getText();
        String number = priceText.replaceAll("[^0-9]", "");
        double value = Double.parseDouble(number);
        return value;
    }


    public double getExpectedTotalPrice() {
        double sum=returnPrice()+returnTax();
        return sum;
    }

}

