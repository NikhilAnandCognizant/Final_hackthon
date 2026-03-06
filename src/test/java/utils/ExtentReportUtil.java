package utils;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtil {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports initReport() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExecutionReport.html");
            spark.config().setTheme(Theme.DARK);
            spark.config().setReportName("Naukri Automation Results");
            spark.config().setDocumentTitle("Final Hackathon Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Hub", "Delhi NCR");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }

    public static void createTest(String testName) {
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test); // Save to the current thread
    }

    public static ExtentTest getTest() {
        return extentTest.get(); // Retrieve for the current thread
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}