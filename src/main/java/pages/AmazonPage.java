package pages;

import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.FrameworkUtils;

public class AmazonPage implements SearchPage{

    WebDriver driver;

    WebDriverWait webDriverWait;
    FrameworkUtils frameworkUtils;
    public AmazonPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.webDriverWait = wait;
        frameworkUtils = new FrameworkUtils(driver);
    }

    private By searchBar = By.xpath("//input[@id='twotabsearchtextbox']");
    private By filterOption = By.xpath("//label[text()='Sort by:']/parent::span/select");
    private By productLink = By.xpath("//div[@data-component-type='s-search-result'][1]//span[contains(@class,'text-normal')]//parent::a");
    private By productPrice = By.xpath("//div[contains(@id,'corePriceDisplay')]//span[@class='a-price-whole']");

    @Override
    public void SearchProduct(String searchTerm) {
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(searchBar));
        driver.findElement(searchBar).sendKeys(searchTerm);
        driver.findElement(searchBar).sendKeys(Keys.ENTER);
    }

    private void filterByLowPrice(){
        Select select = new Select(driver.findElement(filterOption));
        select.selectByValue("price-asc-rank");
    }

    @Override
    public void clickOnProduct(String productName) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(productLink));
        driver.findElement(productLink).click();
    }

    @Override
    public Product getProductDetails() {
        Product product = new Product();
        if(frameworkUtils.switchToNextWindow()){
            product.setUrl(driver.getCurrentUrl());
            product.setPrice(Integer.parseInt(driver.findElement(productPrice).getText().replaceAll("[^0-9]+","")));
        }
        return product;
    }


}
