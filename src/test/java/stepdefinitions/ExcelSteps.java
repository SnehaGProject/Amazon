package stepdefinitions;

import io.cucumber.java.en.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.AmazonPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelSteps {

    WebDriver driver;
    Workbook workbook;
    Sheet sheet;
    Row currentRow;
    String productName;
    String price;


    public AmazonPage ap=new AmazonPage(driver);

    @When("user search a product from excel file")
    public void user_search_a_product_from_excel_file() throws IOException {
        File file = new File("C:\\CucumberProject\\AmazonFramework\\src\\test\\resources\\testData\\Amazon.xlsx");
        FileInputStream fis = new FileInputStream(file);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet("Sheet1");

        currentRow = sheet.getRow(1);
        productName = currentRow.getCell(0).getStringCellValue();


//        WebElement searchBox = driver.findElement(By.xpath("//input[@id='search-input']"));
//        searchBox.sendKeys(productName);
        ap.searchItem(productName);

    }
    @When("clicks on search")
    public void clicks_on_search() {

        ap.clickSearchLogo();
    }
    @When("user validates description")
    public void user_validates_description() {



//        WebElement descriptionElement = driver.findElement(By.xpath("//div[@id='product-description']"));
//        String actualDescription = descriptionElement.getText();
        boolean productFound = false;


        while (!productFound) {
            List<WebElement> products = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
            for (WebElement product : products) {
                WebElement productDesc = product.findElement(By.xpath(".//span[@class='a-size-base-plus a-color-base a-text-normal']"));
                String productDescText = productDesc.getText();
                String expectedDescription = currentRow.getCell(1).getStringCellValue();
                if (productDescText.equals(expectedDescription)) {
                    WebElement atc = product.findElement(By.xpath(".//button[@class='a-button-text']"));
                    atc.click();
                    WebElement priceElement=product.findElement(By.xpath(".//span[@class='a-price-whole']"));
                     price = priceElement.getText();

                    System.out.println("Product found and added to cart: " + productDescText);
                    productFound = true;
                    break;
                }
                if (!expectedDescription.equals(expectedDescription)) {
                    throw new AssertionError("Description does not match");
                }

            }
            if (!productFound) {
                try {
                    WebElement nextButton = driver.findElement(By.xpath("//a[contains(text(),'Next')]"));
                    nextButton.click();

                    Thread.sleep(2000);
                } catch (NoSuchElementException e) {

                    System.out.println("Reached the last page. Product not found.");
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }




    }
    @Then("write product price in excel sheet")
    public void write_product_price_in_excel_sheet() throws IOException {

//        WebElement priceElement=products.findElement(By.xpath(".//span[@class='a-price-whole']"));
//        String price = priceElement.getText();


        Cell priceCell = currentRow.createCell(2);
        priceCell.setCellValue(price);


        FileOutputStream fos = new FileOutputStream(new File("src/test/resources/testdata/ProductData.xlsx"));
        workbook.write(fos);
        fos.close();


        workbook.close();
        driver.quit();
    }


    }

