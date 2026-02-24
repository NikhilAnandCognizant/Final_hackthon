package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/** Footer component – LinkedIn link handling. */
public class FooterSection extends BasePage {



    // Public so test can scroll to these before clicking
    public final By linkedInExact = By.cssSelector("footer a[href*='linkedin.com/company/naukri.com']");
    public final By linkedInAny   = By.cssSelector("footer a[href*='linkedin.com']");

    public FooterSection(WebDriver dr) {
        super(dr);
    }


    /** Wait for visibility (not just clickable) and click the LinkedIn link. */
    public void clickLinkedInVisible() {
        try {

            waitAndClick(linkedInExact);
        } catch (TimeoutException te) {

            waitAndClick(linkedInAny);
        }
    }
}