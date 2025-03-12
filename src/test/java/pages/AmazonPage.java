package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


import java.util.List;

public class AmazonPage {
    public WebDriver driver;

    public AmazonPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@id='nav-logo-sprites']")
    WebElement amazonLogo;

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    WebElement searchTab;


    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    WebElement searchLogo;

    //a[@id='s-result-sort-select_0']
    //@FindBy(xpath = "//span[@class='a-dropdown-prompt']")
    @FindBy(xpath = "//select[@id='s-result-sort-select']")
    WebElement Dropdown;
    @FindBy(xpath = "//button[@class='a-button-text a-text-center']")
    List<WebElement> shoeSizeList;

    @FindBy(xpath = "//span[@class='a-size-base a-color-base a-text-bold']")
    WebElement rightSliderText;

    //    @FindBy(xpath="//div[@class='a-section s-range-input-container s-lower-bound aok-relative']")
//    WebElement leftSlider;
    @FindBy(xpath = "//input[@id='p_36/range-slider_slider-item_lower-bound-slider']")
    WebElement leftSlider;
    //    @FindBy(xpath="//div[@class='a-section s-range-input-container s-upper-bound']")
//    WebElement rightSlider;
    @FindBy(xpath = "//input[@id='p_36/range-slider_slider-item_upper-bound-slider']")
    WebElement rightSlider;

    @FindBy(xpath = "//span[contains(text(),'Color')and@class='a-size-base a-color-base puis-bold-weight-text']")
    WebElement colorTab;

    @FindBy(xpath = "//div[@data-component-type='s-search-result']//span[@class='a-size-base-plus a-color-base a-text-normal' and contains(text(),'Hydrogen Water Bottle, Portable Hydrogen Water Bottle Generator, Ion Water Bottle Improve Water Quality in 3 Minutes, Water Ionizer Machine Suitable for Office, Travel and Daily Drinking(Sliver)')]")
    WebElement productDescription;

    public boolean amazonLogoIsDisplayed() {
        boolean logoPresent = amazonLogo.isDisplayed();
        return logoPresent;
    }

    public void searchItem(String item) {
        searchTab.sendKeys(item);
    }

    public void clickSearchLogo() {
        searchLogo.click();
    }

    public void clickDropdown() {
        Dropdown.click();
    }


    public void clickingOnEveryDropdown() throws InterruptedException {
        Select select = new Select(Dropdown);
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            String optionText = option.getText();
            System.out.println("Selecting sort option: " + optionText);
            select.selectByVisibleText(optionText);
            Thread.sleep(3000);
        }
    }

    public void clickALLDropdownItems() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Select select = new Select(Dropdown);
            List<WebElement> options = select.getOptions();
            System.out.println("Selecting sort option: " + options.get(i).getText());
            select.selectByIndex(i);
            Thread.sleep(3000);
        }

    }

    public void clickAllShoeSizes() throws InterruptedException {
        List<WebElement> shoesSizeList = driver.findElements(By.xpath("//button[@class='a-button-text a-text-center']"));
        for (WebElement i : shoesSizeList) {
            System.out.println(i.getText());
            if (i.isEnabled()) {
                System.out.println("Clicking shoe size: " + i.getText());
                i.click();

            } else {
                System.out.println("Shoe size " + i.getText() + " is not clickable, skipping.");
            }
            Thread.sleep(5000);
        }
    }

    public void moveSlider() {
        Actions actions = new Actions(driver);
        //actions.clickAndHold(leftSlider).moveByOffset(10, 0).release().perform();
        actions.clickAndHold(rightSlider).moveByOffset(-100, 0).release().perform();
    }

    public void findBrand(String name) throws InterruptedException {
        //WebElement brandName=driver.findElement(By.xpath("//span[contains(@class, 'a-size-base a-color-base') and contains(text(), '"+name+"')]/preceding-sibling::div/label/input"));
        WebElement brandName = driver.findElement(By.xpath("//span[contains(@class, 'a-size-base a-color-base') and contains(text(), '" + name + "')]/preceding-sibling::div/label/input/following-sibling::i"));
        Thread.sleep(3000);
        brandName.click();
    }

    public boolean ifColorTabPresent() {
        boolean colorTabsection = colorTab.isDisplayed();
        return colorTabsection;
    }

    @FindBy(xpath = "//span[@class=\"a-color-base a-text-bold\" and contains(text(),'Mon, Oct 21 ')]")
    WebElement DeliveryDate;

    public boolean ifDeliveryDatePresent() {
        boolean DeliveryDatePresence = DeliveryDate.isDisplayed();
        return DeliveryDatePresence;
    }

    @FindBy(xpath = "//div[@data-component-type='s-search-result']//button[@class='a-button-text']")
    WebElement addToCart;

    public boolean IfAddToCartIsPresent() {
        boolean addToCartPresence = addToCart.isDisplayed();
        return addToCartPresence;
    }

    public void checkDeliveryDate(String Date) throws InterruptedException {
        List<WebElement> products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
        for (WebElement product : products) {
            WebElement dd= product.findElement(By.xpath(""));
            if (ifDeliveryDatePresent()) {

                String productDelivery = DeliveryDate.getText();
                if (productDelivery.equals(Date)) {
                    WebElement atc = product.findElement(By.xpath(".//button[@class='a-button-text']"));
                    addToCart.click();
                    Thread.sleep(3000);
                }
            } else {
                System.out.println("Item cannot be added to the cart");
            }
        }
    }

    public void checkProductCustomerReviews(int rating) throws InterruptedException {
        List<WebElement> products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
        for (WebElement product : products) {
            WebElement customerReview = product.findElement(By.xpath(".//span[@class='a-size-base s-underline-text']"));
            Thread.sleep(3000);
            String customerReviewText = customerReview.getText();
            String result = customerReviewText.replace(",", "");
            int num = Integer.parseInt(result);

            if (num>rating) {
//                WebElement atc = product.findElement(By.xpath(".//button[@class='a-button-text']"));
//                atc.click();
//                Thread.sleep(3000);
                WebElement desc=product.findElement(By.xpath(".//span[@class='a-size-base-plus a-color-base a-text-normal']"));
                String descText=desc.getText();
                System.out.println("Product having customer review over 8000 to  is,"+descText );

            }
        }



    }

    public  void checkProductCustomerReviewLessThan(int rating) throws InterruptedException {
        List<WebElement> products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
        for (WebElement product : products) {
            WebElement customerReview = product.findElement(By.xpath(".//span[@class='a-size-base s-underline-text']"));
            Thread.sleep(3000);
            String customerReviewText = customerReview.getText();
            String result = customerReviewText.replace(",", "");
            int num = Integer.parseInt(result);

            if (num<rating) {
//                WebElement atc = product.findElement(By.xpath(".//button[@class='a-button-text']"));
//                atc.click();
//                Thread.sleep(3000);
                WebElement desc=product.findElement(By.xpath(".//span[@class='a-size-base-plus a-color-base a-text-normal']"));
                String descText=desc.getText();
                System.out.println("Product having customer review over 8000 to  is,"+descText );

            }
        }
    }
    public boolean checkIfBackpackCapacityIsPresent(){
        WebElement BC=driver.findElement(By.xpath("//span[@class='a-size-base a-color-base puis-bold-weight-text' and contains(text(),'Backpack Capacity')]"));
        return BC.isDisplayed();
    }
    public void BackPackCapacity(String name){
        if (checkIfBackpackCapacityIsPresent()){
            WebElement tag=driver.findElement(By.xpath("//span[contains(text(),'"+name+"')]//preceding-sibling::div/label/input/following-sibling::i"));
            tag.click();
        }
    }
}
