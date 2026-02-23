package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/** Job detail page – verify the company logo image renders for real. */
public class JobDetailPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By logoPrimary  = By.cssSelector("img[class*='logo']");
    private final By logoAltText  = By.xpath("//img[contains(translate(@alt,'LOGO','logo'),'logo')]");
    private final By headerScoped = By.xpath("(//header|//div[contains(@class,'job') or contains(@id,'job')])[1]//img");

    public JobDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isCompanyLogoRendered() {
        WebElement img;
        try {
            img = wait.until(ExpectedConditions.visibilityOfElementLocated(logoPrimary));
        } catch (TimeoutException te1) {
            try {
                img = wait.until(ExpectedConditions.visibilityOfElementLocated(logoAltText));
            } catch (TimeoutException te2) {
                img = wait.until(ExpectedConditions.visibilityOfElementLocated(headerScoped));
            }
        }

        if (!img.isDisplayed()) return false;

        Object result = ((JavascriptExecutor) driver).executeScript(
                "var i=arguments[0]; if(!i) return false;" +
                        "var w=(i.naturalWidth||0), h=(i.naturalHeight||0);" +
                        "var r=i.getBoundingClientRect();" +
                        "return (w>0 && h>0 && r.width>0 && r.height>0);", img);

        return (result instanceof Boolean) && ((Boolean) result);
    }
}