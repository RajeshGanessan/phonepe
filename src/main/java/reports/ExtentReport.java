package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReport {

    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy MMM dd HH-mm-ss");
    static String time = dtf.format(now);

    private static String REPORT_DIRECTORY = System.getProperty("user.dir") + File.separator + "reports";

    private static String FILE_NAME = "TestRunReport " + time + " .html";
    private static String FILE_PATH = REPORT_DIRECTORY + File.separator + FILE_NAME;

    private static ExtentReports extentReports;


    public static ExtentReports initialise(){

        Path path = Paths.get(REPORT_DIRECTORY);
        if(!Files.exists(path)){
            try{
                Files.createDirectory(path);
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        extentReports = new ExtentReports();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(FILE_PATH);
        extentSparkReporter.config().setReportName("ECommerce test report");
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("System","Windows");
        extentReports.setSystemInfo("ENV NAME", System.getProperty("env"));


        return extentReports;
    }

    public static void flushReport(){
        extentReports.flush();
        ExtentManager.unload();
    }

    public static void createTest(String testCaseName) {
        ExtentManager.setExtent(extentReports.createTest(testCaseName));
    }

}
