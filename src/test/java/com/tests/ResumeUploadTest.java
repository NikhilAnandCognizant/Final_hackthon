package com.tests;

import org.example.pages.DashBoard;
import org.example.pages.HomePage;

import org.example.pages.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CredsUtil;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ResumeUploadTest extends BaseTest{

    private String user;
    private String pass;

    @BeforeMethod
    public void setupBrowser() throws IOException{
        driver.get("https://www.naukri.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Object[][] data = CredsUtil.getxl();
        user = data[0][0].toString();
        pass = data[0][1].toString();
    }

    @Test(dataProvider = "creds", priority = 1)
    public void verifyCorrectResumeUpload(String userName, String password){
        HomePage lp = new HomePage(driver);
        DashBoard db = lp.login(user, pass);

        ProfilePage pp = db.navigateToSetting();
        File file = new File("./src/test/resources/CompressedResume.pdf");
        if (!file.exists()) {
            Assert.fail("Test File not found at: " + file.getAbsolutePath());
        }
        String absolutePath = file.getAbsolutePath();
        pp.uploadResume(absolutePath);
        Assert.assertTrue(pp.isResumeUploadSuccessful(), "Resume upload failed or success message not displayed!");
    }

    @Test(dataProvider = "creds", priority = 2)
    public void verifyInvalidFormatResumeUpload(String userName, String password){
        HomePage lp = new HomePage(driver);
        DashBoard db = lp.login(user, pass);
        ProfilePage pp = db.navigateToSetting();

        File file = new File("./src/test/resources/InvalidFormatResume.png");
        if(!file.exists()){
            Assert.fail("Test File not found at: " + file.getAbsolutePath());
        }
        String absolutePath = file.getAbsolutePath();
        pp.uploadResume(absolutePath);
        String errorText = pp.getUploadErrorText();
        Assert.assertTrue(errorText.contains("file type is doc, docx, rtf or pdf"),
                "Expected format error not shown! Actual: " + errorText);
    }

    @Test(dataProvider = "creds", priority = 3)
    public void verifyLoginSizeResumeUpload(String userName, String password){
        HomePage lp = new HomePage(driver);
        DashBoard db = lp.login(user, pass);
        ProfilePage pp = db.navigateToSetting();

        File file = new File("./src/test/resources/MaxSizeResume.pdf");
        if(!file.exists()){
            Assert.fail("Test File not found at: " + file.getAbsolutePath());
        }
        String absolutePath = file.getAbsolutePath();
        pp.uploadResume(absolutePath);
        String errorText = pp.getUploadErrorText();
        Assert.assertTrue(errorText.contains("file size is less than 2MB"),
                "Expected size error not shown! Actual: " + errorText);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.manage().deleteAllCookies();
    }
}
