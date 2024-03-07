package reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {


    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();


    public static ExtentTest getExtent(){
        return extentTest.get();
    }

    public static void setExtent(ExtentTest test){
        extentTest.set(test);
    }


    public static void unload(){
        extentTest.remove();
    }
}
