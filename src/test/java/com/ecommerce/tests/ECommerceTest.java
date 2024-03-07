package com.ecommerce.tests;

import constants.Constants;
import driver.BrowserType;
import driver.Driver;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.AmazonPage;
import pages.FlipkpartPage;

import java.time.Duration;
import java.util.Objects;

import utils.FrameworkUtils;

import static org.assertj.core.api.Assertions.assertThat;


public class ECommerceTest  {

    private static String  PRODUCT_NAME = "green soul monster";
    WebDriver driver;

    FrameworkUtils frameworkUtils;
    FlipkpartPage flipkpartPage;

    Product flipKartProduct;

    AmazonPage amazonPage;
    Product amazonProduct;

    WebDriverWait wait;

    @BeforeTest
    public void setup() throws Exception {

        driver = Driver.getDriver(BrowserType.CHROME);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        flipkpartPage = new FlipkpartPage(driver,wait);
        frameworkUtils = new FrameworkUtils(driver);
        amazonPage = new AmazonPage(driver,wait);


    }

    @Test(description = "Search product in flipkart", priority = 1)
    public void searchProductFlipkartTest(){
        driver.get(Constants.FLIPKART_URL);

        flipkpartPage.SearchProduct(PRODUCT_NAME);
        flipkpartPage.clickOnProduct(PRODUCT_NAME);
        flipKartProduct = flipkpartPage.getProductDetails();


        assertThat(flipKartProduct.getUrl()).isNotEmpty();
        assertThat(flipKartProduct.getPrice()).isGreaterThan(1);

    }

    @Test(description = "Search product in Amazon ",priority = 2)
    public void searchProductAmazonTest() throws Exception {
        driver.switchTo().newWindow(WindowType.WINDOW).
                manage().window().maximize();
        driver.get(Constants.AMAZON_URL);
        frameworkUtils.CloseWindows();

        amazonPage.SearchProduct(PRODUCT_NAME);
        amazonPage.clickOnProduct(PRODUCT_NAME);
        amazonProduct = amazonPage.getProductDetails();

        assertThat(amazonProduct.getUrl()).isNotEmpty();
        assertThat(amazonProduct.getPrice()).isGreaterThan(1);
    }

    @Test(description = "Compare price and get Right product",priority = 3)
    public void getResultProduct(){

        if(flipKartProduct.getPrice() < amazonProduct.getPrice()){
            System.out.println(flipKartProduct.getUrl());
            System.out.println(flipKartProduct.getPrice());
        } else {
            System.out.println(amazonProduct.getUrl());
            System.out.println(amazonProduct.getPrice());
        }

    }

    @AfterSuite
    public void tearDown(){
        if(!Objects.isNull(driver))
            driver.quit();
    }


}
