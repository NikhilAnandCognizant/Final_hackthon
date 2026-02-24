
package org.example.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchFilter {


    WebDriver driver;
    public SearchFilter(WebDriver driver) {
        this.driver = driver;
    }

    By searchButton = By.xpath("//*[@id=\"root\"]/div[7]/div/div[1]/div[6]");
    By locationFilter = By.xpath("//label[@for='chk-Bengaluru-cityTypeGid-']//i[@class='ni-icon-unchecked']");
    By jobHeader = By.xpath("");
    By salaryFilter = By.xpath("//*[@id=\"search-result-container\"]/div[1]/div[1]/div/div/div[2]/div[5]/div[2]/div[3]/label/i");
    By errorMessage = By.xpath("//*[@id=\"search-result-container\"]/div[1]/div[1]/div[1]/div[1]");





    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public void clickLocationFilter() {
        driver.findElement(locationFilter).click();
    }

    public void clickSalaryFilter() {
        driver.findElement(salaryFilter).click();
    }

    // Getter Methods for Assertions
//    public String getJobHeaderText() {
//        return driver.findElement(jobHeader).getText();
//    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}








