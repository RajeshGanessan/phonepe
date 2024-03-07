package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {


    public static WebDriver driver;

    public static WebDriver getDriver(BrowserType browser) throws Exception {

        switch (browser) {

            case CHROME:
                driver = new ChromeDriver();
                break;

            case FIREFOX:
                driver = new FirefoxDriver();
                break;


            default:
                throw new Exception("Browser not supported");
        }


        return driver;
    }
}
