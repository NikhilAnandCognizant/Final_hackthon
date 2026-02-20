package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class SettingPage {
    private By editButton = By.xpath("//div[@class=\"widgetHead\"]/span[@class=\"edit icon\"]");
    private By textar = By.id("resumeHeadlineTxt");
    private WebDriver wb ;
    private By save = By.xpath("//div[@class=\"action s12\"]/button");
    private By displayOfHeader = By.xpath("//textarea[@id ='resumeHeadlineTxt']");
    private By skillChips = By.xpath("//div[@class='prefill']/span[@class='chip typ-14Medium']");
    private By editButtonForSkill = By.xpath("//div[@id='lazyKeySkills']//span[@class='edit icon']");
    private By skillDropdown = By.xpath("//div[@class='suggest keySkillSuggCont']/div[@class='sugCont slideDown']/ul/li");
    private By skillSugInput = By.id("keySkillSugg");
    private By saveButtonOfSkill = By.id("saveKeySkills");

    public SettingPage(WebDriver wd){
        this.wb = wd;

    }
    public void editHeadLine(String header){
        WebDriverWait wt = new WebDriverWait(this.wb , Duration.ofSeconds(3));
        WebElement editel = wt.until(ExpectedConditions.visibilityOfElementLocated(this.editButton));
        editel.click();
        WebElement textArea = wt.until(ExpectedConditions.visibilityOfElementLocated(this.textar));
        textArea.clear();
        textArea.sendKeys( header);

        WebElement save = wt.until(ExpectedConditions.visibilityOfElementLocated(this.save));
        save.click();



    }
    public String getHeadLine(){
        WebDriverWait wt = new WebDriverWait(this.wb , Duration.ofSeconds(3));
        WebElement displayOfHeader = wt.until(ExpectedConditions.elementToBeClickable(this.displayOfHeader));


        return displayOfHeader.getText();

    }
    public void insertSkill(String inp){
        WebDriverWait wt = new WebDriverWait(this.wb , Duration.ofSeconds(7));
        WebElement editel = wt.until(ExpectedConditions.visibilityOfElementLocated(this.editButtonForSkill));
        editel.click();

        WebElement sugArea = wt.until(ExpectedConditions.elementToBeClickable(this.skillSugInput));
        sugArea.sendKeys(inp);

        WebElement dropdown = wt.until(ExpectedConditions.elementToBeClickable(this.skillDropdown));

        dropdown.click();

        WebElement save = wt.until(ExpectedConditions.elementToBeClickable(this.saveButtonOfSkill));
        save.click();

    }

    public List<String> getListOfSkillChip(){

        List<WebElement> chips = wb.findElements(this.skillChips);

        return chips.stream().map(WebElement::getText).collect(Collectors.toList());


    }

}
