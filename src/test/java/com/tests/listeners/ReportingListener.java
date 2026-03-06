package com.tests.listeners;

import org.apache.commons.io.FileUtils;
import org.example.utils.DriverSetup;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentReportUtil;

import java.io.File;
import java.text.SimpleDateFormat;

public class ReportingListener implements ITestListener {

    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        if (driver == null) {
            System.out.println("Driver is null, skipping screenshot.");
            return null;
        }
        // 1. Create a timestamp so every screenshot has a unique name
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());

        // 2. Define the destination path
        // Using System.getProperty("user.dir") ensures it works on any computer in Delhi NCR or elsewhere
        String path = System.getProperty("user.dir") + "/reports/screenshots/" + screenshotName + "_" + timestamp + ".png";

        try {
            // 3. Cast driver to TakesScreenshot and get the file
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(path);

            // 4. Copy the file to the destination
            FileUtils.copyFile(source, destination);

            System.out.println("Screenshot captured: " + path);

            // 5. Return the path so Extent Reports knows where to look
            return path;
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            return null;
        }
    }
    @Override
    public void onStart(ITestContext context) {
        // Ensure the report engine is started once for the suite
        ExtentReportUtil.initReport();
    }
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportUtil.createTest(result.getName()); // Create a row for this test
    }

    @Override
    public void onTestFailure( ITestResult result) {

        WebDriver driver = DriverSetup.getDriver();

        // 1. Capture the screenshot and get its path
        String screenshotPath = captureScreenshot(driver, result.getName());

        // 2. Log the failure in the report
        ExtentReportUtil.getTest().fail("Test Failed: " + result.getThrowable());

        // 3. Attach the captured screenshot to the report row
        if (screenshotPath != null) {
            ExtentReportUtil.getTest().addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportUtil.flushReport(); // Close and save the file
    }


}
