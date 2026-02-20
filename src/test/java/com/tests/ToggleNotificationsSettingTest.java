package com.tests;

import org.example.pages.LoginPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import utils.CredsUtil;

import java.io.IOException;

public class ToggleNotificationsSettingTest extends BaseTest{

    @BeforeTest
    public void setupBrowser() throws IOException {
        driver.get("https://www.naukri.com/");
        Object[][] data = CredsUtil.getxl();
        String user = data[0][0].toString();
        String pass = data[0][1].toString();
        LoginPage lp = new LoginPage(driver);
        lp.login(user,pass);

    }

}
