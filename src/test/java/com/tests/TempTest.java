package com.tests;

import org.example.pages.DashBoard;
import org.example.pages.FaqPage;
import org.example.pages.HomePage;

import org.testng.Assert;
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
        HomePage lp = new HomePage(driver);
        DashBoard db = lp.login(user,pass);




    }
    @Test
    public void ex(){
        DashBoard db = new DashBoard(driver);
        FaqPage faqpage = db.navigateToFaq();

        // Capture the text from the suggestion dropdown
        String suggestedText = faqpage.search("jo");

        // Capture the headline text from the results page
        String resultHeadline = faqpage.getSearchResultHeadlineText();

        // Use ignoreCase for a resilient check
        Assert.assertEquals(resultHeadline.toLowerCase().trim(),'"'+suggestedText.toLowerCase().trim().substring(0,suggestedText.length()-1)+'"',
                "Semantic mismatch! Expected: " + suggestedText + " but got: " + resultHeadline);
        System.out.println("Suggestion: " + suggestedText);
        System.out.println("Headline: " + resultHeadline);
    }
}
