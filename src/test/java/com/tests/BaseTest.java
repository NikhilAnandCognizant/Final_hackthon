package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

        protected WebDriver driver;

        @BeforeTest
        public void prepareModule() {
            driver = new ChromeDriver();

            System.out.println("Module Setup: Config files loaded.");
        }





        @AfterTest
        public void cleanupModule() {

//            if (driver != null) {
//                driver.quit();
//            }
        }

}
