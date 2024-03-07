package utils;

import org.openqa.selenium.WebDriver;

import java.util.Set;

public class FrameworkUtils {
    WebDriver driver;

    public FrameworkUtils(WebDriver driver){
        this.driver = driver;
    }


    public boolean switchToNextWindow(){

        String mainWindow = driver.getWindowHandle();

        Set<String> windows = driver.getWindowHandles();

        for(String window : windows) {
            if(!window.equals(mainWindow)){
                driver.switchTo().window(window);
                return true;
            }
        }
        return false;
    }

    public void CloseWindows(){
        String mainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for(String window : windows){
            if(!window.equals(mainWindow)){
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
        driver.navigate().refresh();
    }

}
