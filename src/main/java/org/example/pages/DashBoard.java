package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashBoard {
    private By burgerButton = By.cssSelector(".nI-gNb-drawer");
    private WebDriver wd ;

    public DashBoard(WebDriver wd){
        this.wd = wd;
    }

    public ProfilePage navigateToSetting(){
        WebDriverWait wait = new WebDriverWait(this.wd, Duration.ofSeconds(2));

        WebElement br = wait.until(ExpectedConditions.visibilityOfElementLocated(this.burgerButton));
        br.click();
        WebElement linkTOsetting =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("nI-gNb-info__sub-link")));
        linkTOsetting.click();
        return new ProfilePage(this.wd);


    }
    public LoginPage logout(){
        WebDriverWait wt = new WebDriverWait(wd,Duration.ofSeconds(5));
        WebElement burger = wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='nI-gNb-drawer__icon']")));

        burger.click();



        wt.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@title,'Logout')]"))).click();

        return new LoginPage(wd);

    }

}
