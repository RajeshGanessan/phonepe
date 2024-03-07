package reports;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;

public class ExtentLogger {

    public static void pass(String message) {
        ExtentManager.getExtent().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
    }

    public static void fail(String message) {
        ExtentManager.getExtent().fail(message);
    }

    public static void skip(String message) {
        ExtentManager.getExtent().skip(message);
    }

    public static void info(String message){
        ExtentManager.getExtent().info(message);
    }
}
