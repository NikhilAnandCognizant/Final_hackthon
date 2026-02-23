package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Naukri Home page
 * - Search input + Search action
 * - Cookie banner dismiss (best-effort)
 * - Slow, smooth scroll until a target element is visible
 * - Empty search prevention check (disabled OR validation OR no navigation)
 */
public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    By loginButton = By.id("login_Layer");
    By usenameField = new By.ByXPath("//div[@class=\"form-row\"]/input[@type='text']");
    By passwordField = new By.ByXPath("//div[@class=\"form-row\"]/input[@type='password']");
    By loginSubmit = By.cssSelector(".btn-primary.loginButton");
    public DashBoard login(String userName, String password){
        System.out.println(userName+" "+password);
        WebDriverWait wt = new WebDriverWait(this.driver , Duration.ofSeconds(2));
        WebElement lb = wt.until(ExpectedConditions.visibilityOfElementLocated(this.loginButton));
        assert lb != null;
        lb.click();


        WebElement usernameField = wt.until(ExpectedConditions.visibilityOfElementLocated(this.usenameField));
        usernameField.clear();
        usernameField.sendKeys(userName);

        WebElement pass = wt.until(ExpectedConditions.presenceOfElementLocated(this.passwordField));
        pass.clear();
        pass.sendKeys(password);

        WebElement loginsb = wt.until(ExpectedConditions.presenceOfElementLocated(this.loginSubmit));
        loginsb.click();


        return new DashBoard(this.driver);
    }

    public String getPasswordFieldType(){
        WebDriverWait wt = new WebDriverWait(this.driver , Duration.ofSeconds(2));
        WebElement lb = wt.until(ExpectedConditions.visibilityOfElementLocated(this.loginButton));
        assert lb != null;
        lb.click();
        return wt.until(ExpectedConditions.presenceOfElementLocated(this.passwordField)).getAttribute("type");

    }
    // Locators (as per your inspections)
    private final By searchInput  = By.cssSelector("input.suggestor-input");
    private final By searchButton = By.cssSelector("div.qsbSubmit");

    // Generic validation containers (site can vary)
    private final By validationCandidates = By.cssSelector(
            "[role='tooltip'], .error, .err, .validation, [data-error], .snackbar, .toast");

    // Cookie banner patterns (best-effort)
    private final By cookieBar = By.xpath("//*[contains(translate(normalize-space(.),'COOKIE','cookie'),'cookie')][self::div or self::section]");
    private final By cookieAcceptBtn =
            By.xpath("//button[contains(translate(normalize-space(.),'GOTIT','got it'),'got it')]" +
                    " | //button[contains(translate(normalize-space(.),'ACCEPT','accept'),'accept')]" +
                    " | //a[contains(translate(normalize-space(.),'GOTIT','got it'),'got it')]" +
                    " | //a[contains(translate(normalize-space(.),'ACCEPT','accept'),'accept')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public HomePage waitForReady() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        dismissCookieBannerIfPresent();
        return this;
    }

    /** Try to close cookie banner (best-effort, safe if not present). */
    public void dismissCookieBannerIfPresent() {
        try {
            if (!driver.findElements(cookieBar).isEmpty() && !driver.findElements(cookieAcceptBtn).isEmpty()) {
                WebElement btn = driver.findElement(cookieAcceptBtn);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                new WebDriverWait(driver, Duration.ofSeconds(2))
                        .until(ExpectedConditions.invisibilityOf(btn));
            }
        } catch (Exception ignored) {}
    }

    public HomePage clearSearch() {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        input.sendKeys(Keys.DELETE);
        return this;
    }

    public HomePage typeSearch(String text) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        input.sendKeys(text);
        return this;
    }

    public void clickSearch() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        btn.click();
    }

    /**
     * 🚶‍♂️ Slow, smooth scrolling until the element becomes visible, then center it.
     * Visually shows the footer/LinkedIn icon coming into view.
     */
    public void scrollUntilVisible(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int step = 120;          // small step for slow movement
        int maxSteps = 40;       // up to ~40 steps
        long pauseMs = 300;      // delay between steps so user can see movement

        for (int i = 0; i < maxSteps; i++) {
            try {
                WebElement el = driver.findElement(locator);

                if (el.isDisplayed()) {
                    // Final smooth alignment into center
                    js.executeScript("arguments[0].scrollIntoView({block:'center', behavior:'smooth'});", el);
                    pause(700);
                    return;
                }

            } catch (NoSuchElementException ignored) {}

            // Gentle downward motion
            js.executeScript("window.scrollBy(0, arguments[0]);", step);
            pause(pauseMs);
        }

        // If still not visible, wait and then do a final smooth center
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        js.executeScript("arguments[0].scrollIntoView({block:'center', behavior:'smooth'});", el);
        pause(700);
    }

    /**
     * Returns true if empty search is prevented:
     *  - by disabled state OR validation UI OR no navigation after click.
     */
    public boolean isEmptySearchPrevented() {
        clearSearch();
        String urlBefore = driver.getCurrentUrl();
        String titleBefore = driver.getTitle();

        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        boolean disabledAttr = "true".equalsIgnoreCase(btn.getAttribute("aria-disabled"))
                || btn.getAttribute("disabled") != null;

        // Try click anyway (it's a DIV on Naukri)
        try { btn.click(); } catch (Exception ignored) {}

        boolean validationShown;
        try {
            validationShown = new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(d -> !d.findElements(validationCandidates).isEmpty());
        } catch (TimeoutException te) {
            validationShown = false;
        }

        boolean navigated = !(urlBefore.equals(driver.getCurrentUrl()) && titleBefore.equals(driver.getTitle()));
        return disabledAttr || validationShown || !navigated;
    }

    // ---------- small utility ----------

    /** Safe sleep wrapper that restores interrupt status if interrupted. */
    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}