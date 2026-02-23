package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FaqPage {
    private WebDriver wd ;
    private By searchInp  = By.id("keyword_search");
    private By sugList = By.xpath("//div[@class='eac-item']");
    private By result = By.xpath("//div[@class='registration_area']//div[@class='heading']/h1");
    public FaqPage(WebDriver wd){

        this.wd = wd;


    }

    public String search(String key){
        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.elementToBeClickable(searchInp)).sendKeys(key);

       WebElement sugel= wait.until(ExpectedConditions.elementToBeClickable(sugList));

        sugel.click();
        return sugel.getText();

    }

    public String getSearchResultHeadlineText(){

        WebDriverWait wait = new WebDriverWait(wd, Duration.ofSeconds(7));
       return wait.until(ExpectedConditions.elementToBeClickable(result)).getText();

    }

}
