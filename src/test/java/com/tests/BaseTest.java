package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import utils.CredsUtil;

import java.io.IOException;

public class BaseTest {

        protected WebDriver driver;

        @BeforeTest
        public void prepareModule() {
            driver = new ChromeDriver();
            driver.manage().window().maximize();

            System.out.println("Module Setup: Config files loaded.");
        }
    @DataProvider(name = "creds")
    public Object [][] dataPr() throws IOException {
        return CredsUtil.getxl();
    }

        @AfterTest
        public void cleanupModule() {

   //         if (driver != null) {
    //            driver.quit();
    //        }
        }

}
