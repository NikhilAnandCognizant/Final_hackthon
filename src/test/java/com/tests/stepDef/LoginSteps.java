package com.tests.stepDef;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.example.pages.LoginPage;

public class LoginSteps {

    // 1. Remove the constructor
    // 2. Use Annotations (@Given) instead of method calls

    @Given("User is on the Naukri homepage")
    public void user_is_on_the_naukri_homepage() {
        // Your Selenium code here
        System.out.println("Navigating to Naukri...");
    }

    @When("User enters credentials")
    public void user_enters_credentials() {
        // Logic for entering username/password
    }

    @When("User enters invalid username {string} and password {string}")
    public void userEntersInvalidUsernameAndPassword(String arg0, String arg1) {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("An error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String arg0) {
        // Write code here that turns the phrase above into concrete actions

    }
}