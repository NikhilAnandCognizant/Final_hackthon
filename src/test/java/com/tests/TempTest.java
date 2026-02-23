package com.tests;

import org.example.pages.DashBoard;
import org.example.pages.FaqPage;
import org.example.pages.LoginPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.CredsUtil;

import java.io.IOException;

public class TempTest extends  BaseTest {
    @BeforeTest
    public void setupBrowser() throws IOException {
        driver.get("https://www.naukri.com/");
        Object[][] data = CredsUtil.getxl();
        String user = data[0][0].toString();
        String pass = data[0][1].toString();
        LoginPage lp = new LoginPage(driver);
        DashBoard db = lp.login(user,pass);


    }
    @Test
    public void ex(){
        //div[@class='registration_area']//div[@class='heading']/h1
        DashBoard db = new DashBoard(driver);
      FaqPage faqpage  =db.navigateToFaq();
    String sub =  faqpage.search("jo");

      String res = faqpage.getSearchResultHeadlineText();



    }
}
