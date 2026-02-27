package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DashBoard extends BasePage {
    private By burgerButton = By.cssSelector(".nI-gNb-drawer");
    private By accountSetting = By.xpath("//a[contains(text(),'Settings')]");
    private By logoutB = By.xpath("//a[contains(text(),'Logout')]");
    private By faq = By.xpath("//a[contains(text(),'FAQs')]");
   private By profileSetting = By.className("nI-gNb-info__sub-link");

    public DashBoard(WebDriver dr) {
        super(dr);
    }


    public AccountSettingPage navigateToAccountSetting(){


        waitAndClick(this.burgerButton);
        waitAndClick(accountSetting);
        return new AccountSettingPage(this.wd);
    }

    public ProfilePage navigateToSetting(){

        waitAndClick(burgerButton);


        waitAndClick(profileSetting);
        return new ProfilePage(this.wd);


    }
    public HomePage logout(){

     waitAndClick(burgerButton);


      waitAndClick(logoutB);

        return new HomePage(wd);

    }

    public FaqPage navigateToFaq() {
        waitAndClick(burgerButton);
        waitAndClick(faq,9);

        return new FaqPage(wd);

    }
}
