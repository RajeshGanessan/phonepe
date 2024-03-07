package pages;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FrameworkUtils;

import java.util.HashMap;
import java.util.Map;

public class FlipkpartPage implements SearchPage {

    WebDriver driver;

    WebDriverWait webDriverWait;
    FrameworkUtils frameworkUtils;
    public FlipkpartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.webDriverWait = wait;
        frameworkUtils = new FrameworkUtils(driver);
    }

    private By searchBar = By.xpath("//input[@name='q']");
    private By lowPriceFilter = By.xpath("//div[contains(text(),'Low to High')]");
    private By productPrice = By.xpath("//div[@id='price-info-icon']//parent::div//preceding-sibling::div/div/div[1]");

    String chairXpath = "(//a[contains(@href,'%s')])[position()=1]";

    private By productLink = By.xpath("(//a[@rel='noopener noreferrer'])[1]/parent::div");



    @Override
    public void clickOnProduct(String productName) {
        filterLowPrice();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(chairXpath,productName.replace(" ","-")))));
        driver.findElement(By.xpath(String.format(chairXpath,productName.replace(" ","-")))).click();
    }

    private void filterLowPrice(){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(lowPriceFilter)).click();
    }

    @Override
    public Product getProductDetails(){
        Product product = new Product();
        if(frameworkUtils.switchToNextWindow()) {
            product.setUrl(driver.getCurrentUrl());
            String price = driver.findElement(productPrice).getText().replaceAll("[^0-9]+","");
            product.setPrice(Integer.parseInt(price));
        }
        return product;
    }


    @Override
    public void SearchProduct(String searchTerm) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(searchBar));
        driver.findElement(searchBar).sendKeys(searchTerm);
        driver.findElement(searchBar).sendKeys(Keys.ENTER);
    }



}
