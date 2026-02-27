package org.example.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ProfilePage extends BasePage {
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
    private By modalWhileSaving = By.xpath("//div[@class='ltCont']/div/div[@class='crossLayer']/span[@class='icon' and text() = 'CrossLayer']");
    //locator for uploading resume
    // Inside ProfilePage class
    private By resumeUploadInput = By.id("attachCV"); // Check if this ID is correct for your Naukri version
    private By uploadSuccessMessage = By.xpath("//span[@id='attachCVMsgBox']//p[@class='msg']");
    //locator for getting error message
    private By uploadErrorMessage = By.xpath("//p[@class='error']");


    public void uploadResume(String filePath) {
        WebDriverWait wait = new WebDriverWait(this.wb, Duration.ofSeconds(10));
        // Find the hidden input element and send the file path
        WebElement uploadElement = wb.findElement(resumeUploadInput);
        uploadElement.sendKeys(filePath);
    }

    public boolean isResumeUploadSuccessful() {
        try {
            WebDriverWait wait = new WebDriverWait(this.wb, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(uploadSuccessMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    //Method to get upload error text ()
    public String getUploadErrorText(){
        try{
            WebDriverWait wait = new WebDriverWait(this.wb, Duration.ofSeconds(10));
            WebElement errorElement = wait.until(ExpectedConditions.presenceOfElementLocated(uploadErrorMessage));
            return errorElement.getText();

        }   catch(Exception e){
            return ""; //empty string if no text is located
        }
    }
    public ProfilePage(WebDriver wd){
        super(wd);

    }
    public void editHeadLine(String header){



        waitAndClick(this.editButton,3);


        waitAndSendKeys(this.textar,3,header);
        waitAndClick(this.save);





    }
    public String getHeadLine(){


        return waitAndGetText(this.displayOfHeader);

    }
    public void insertSkill(String inp){
        waitAndEscape(modalWhileSaving);
        waitAndClick(this.editButtonForSkill,7);
        waitAndSendKeys(this.skillSugInput,7,inp);
        waitAndClick(this.skillDropdown);

        waitAndClick(this.saveButtonOfSkill);





    }

    public List<String> getListOfSkillChip(){

        List<WebElement> chips = wd.findElements(this.skillChips);

        return chips.stream().map(WebElement::getText).collect(Collectors.toList());


    }

}
