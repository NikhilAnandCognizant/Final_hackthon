package org.example.pages;

import org.example.utils.DriverSetup;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class AccountSettingPage extends BasePage {

    private By blockCompany = By.xpath("//li[@id='BlockCompany']");
    private By blockingInput = By.xpath("//div[@class='sWrap']/div[@class='inpWrap']/input");
    private By saveButton = By.id("saveSettingBtn");
    private By chips = By.xpath("//span[@class='tagTxt']");
    private By removeals = By.xpath("//div[@class='waves-effect chip']/a");
    private By dropDown = By.xpath(" //div[@id='sugDrp_blockCompanySugg']/ul/li");

    public AccountSettingPage(WebDriver dr) {
        super(dr);
    }


    public void navigateToBlocking(){


      waitAndClick(blockCompany,3);








    }
    public void setBlockCompany(String comp) {


        // We use a loop to retry if the element goes stale immediately
        for (int i = 0; i < 3; i++) {
            try {
                // Re-identify the element inside the loop
                WebElement inp = waitAndGetElement(blockingInput,10);
                inp.clear();
                inp.sendKeys(comp);

                // If sendKeys succeeds, break the loop
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Element went stale, retrying... attempt " + (i + 1));
            }
        }



        waitAndClick(dropDown);

        waitAndClick(saveButton);
    }

    public List<String> getListOfBlockCompanies(){
        waitAndGetElement(chips);
        List<WebElement> chipss = wd.findElements(chips);
        return chipss.stream().map(WebElement::getText).collect(Collectors.toList());
    }

//    public void removeCompanyFromBlockList(String comp) {
//        WebDriverWait wait = new WebDriverWait(this.wd, Duration.ofSeconds(10));
//        JavascriptExecutor js = (JavascriptExecutor) wd;
//
//        // 1. Ensure the section is fully loaded
//        wait.until(ExpectedConditions.visibilityOfElementLocated(chips));
//
//        // 2. Find the correct index outside the Lambda to avoid logic loops
//        List<WebElement> chipss = wd.findElements(chips);
//        int targetIndex = -1;
//
//        for (int i = 0; i < chipss.size(); i++) {
//            try {
//                if (chipss.get(i).getText().trim().equalsIgnoreCase(comp)) {
//                    targetIndex = i;
//                    break;
//                }
//            } catch (StaleElementReferenceException e) {
//                // If it goes stale here, we have to re-fetch the list once
//                chipss = wd.findElements(chips);
//                i--; // Retry this index
//            }
//        }
//
//        if (targetIndex == -1) {
//            throw new RuntimeException("Company '" + comp + "' not found in blocklist.");
//        }
//
//        // 3. Perform the Action using JavaScript
//        // This bypasses "click interception" and ensures the removal event fires
//        List<WebElement> removalss = wd.findElements(removeals);
//        WebElement crossBtn = removalss.get(targetIndex);
//
//        // Scroll to it first so it's in the viewport
//        js.executeScript("arguments[0].scrollIntoView(true);", crossBtn);
//        // Force the click event
//        js.executeScript("arguments[0].click();", crossBtn);
//
//        // 4. Verification: Wait for the chip to actually disappear before saving
//        // This is the "missing link" that ensures the action registered
//        String companyXPath = "//div[contains(@class,'chip')][contains(.,'" + comp + "')]";
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(companyXPath)));
//
//        // 5. Final Save
//        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
//    }



}
