package org.example.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashBoard extends BasePage {
    private By burgerButton = By.cssSelector(".nI-gNb-drawer");
    private By accountSetting = By.xpath("//a[contains(text(),'Settings')]");
    private By logoutB = By.xpath("//a[contains(text(),'Logout')]");
   private By profileSetting = By.className("nI-gNb-info__sub-link");

    public DashBoard(WebDriver dr) {
        super(dr);
    }


    public AccountSettingPage navigateToAccountSetting(){
        WebDriverWait wait = new WebDriverWait(this.wd, Duration.ofSeconds(2));

        WebElement br = wait.until(ExpectedConditions.visibilityOfElementLocated(this.burgerButton));
        br.click();
        WebElement toAccountSetting =  wait.until(ExpectedConditions.visibilityOfElementLocated(accountSetting));
        toAccountSetting.click();
        return new AccountSettingPage(this.wd);
    }

    public SettingPage navigateToSetting(){
        WebDriverWait wait = new WebDriverWait(this.wd, Duration.ofSeconds(2));

        WebElement br = wait.until(ExpectedConditions.visibilityOfElementLocated(this.burgerButton));
        br.click();
        WebElement linkTOsetting =  wait.until(ExpectedConditions.visibilityOfElementLocated(profileSetting));
        linkTOsetting.click();
        return new SettingPage(this.wd);


    }
    public HomePage logout(){
        WebDriverWait wt = new WebDriverWait(wd,Duration.ofSeconds(5));
        WebElement burger = wt.until(ExpectedConditions.elementToBeClickable(burgerButton));

        burger.click();



        wt.until(ExpectedConditions.elementToBeClickable(logoutB)).click();

        return new HomePage(wd);

    }

}
