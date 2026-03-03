package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import utils.CredsUtil;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {

        protected WebDriver driver;

    @BeforeMethod(alwaysRun = true) // Use Method, not Test
    public void prepareModule() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Browser launched for test method.");
    }

    @AfterMethod(alwaysRun = true)
    public void cleanupModule() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed.");
        }
    }
     @DataProvider(name = "creds")
     public Object [][] dataPr() throws IOException {
        return CredsUtil.getxl();
     }


}
