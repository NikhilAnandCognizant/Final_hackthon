package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/** Footer component – LinkedIn link handling. */
public class FooterSection {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Public so test can scroll to these before clicking
    public final By linkedInExact = By.cssSelector("footer a[href*='linkedin.com/company/naukri.com']");
    public final By linkedInAny   = By.cssSelector("footer a[href*='linkedin.com']");

    public FooterSection(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /** Wait for visibility (not just clickable) and click the LinkedIn link. */
    public void clickLinkedInVisible() {
        try {
            WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(linkedInExact));
            link.click();
        } catch (TimeoutException te) {
            WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(linkedInAny));
            link.click();
        }
    }
}