package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        WebDriver dr = new ChromeDriver();
        dr.get("https://onecognizant.cognizant.com/Welcome");
        WebElement element = dr.findElement(By.xpath("//p[@class=\"activityTxt\" and text() =\"Ask HR\"]"));
//        JavascriptExecutor js = (JavascriptExecutor) dr;
//        js.executeScript("arguments[0].click();", element);
        Actions actions = new Actions(dr);
        actions.moveToElement(element).perform(); // This scrolls the element into view
        element.click();
       WebDriverWait wt = new WebDriverWait(dr,Duration.ofSeconds(20));
       wt.until(ExpectedConditions.numberOfWindowsToBe(2));
        String curr = dr.getWindowHandle();

        Set<String> lst= dr.getWindowHandles();
        for(String it : lst){
     if (!it.equals(curr)){
         dr.switchTo().window(it);
     }
        }
        System.out.println(dr.getTitle());
    wt.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("aiAssistantChatIframe")));

   wt.until(ExpectedConditions.elementToBeClickable(By.id("chatchef_input_field"))).sendKeys("hello");

//        dr.get("https://www.heeralwahindiaspices.com/recipes");
//
//        WebDriverWait wt = new WebDriverWait(dr, Duration.ofSeconds(4));
//        wt.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
//
//
//
//
//        dr.findElement(By.xpath("//button[@class=\"ytp-large-play-button ytp-button ytp-large-play-button-red-bg\"]")).click();
//
//dr.switchTo().defaultContent();

    }
}