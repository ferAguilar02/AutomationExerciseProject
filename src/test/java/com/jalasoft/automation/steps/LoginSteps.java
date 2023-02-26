package com.jalasoft.automation.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import ui.PageTransporter;
import ui.pages.HomePage;
import ui.pages.LoginPage;

public class LoginSteps {
    private final PageTransporter pageTransporter;
    private LoginPage loginPage;
    private HomePage homePage;

    public LoginSteps() {
        this.pageTransporter = PageTransporter.getInstance();
    }

    @Given("^I navigate to Admin Login page$")
    public void navigateToLoginPage() {
        loginPage = pageTransporter.navigateToLoginPage();
    }

    @Given("^I login to AutomationExercise page with valid credentials$")
    public void loginToAccount() {
        homePage = loginPage.login();
    }

    @Given("^I login to AutomationExercise page with invalid credentials \"(.*?)\" \"(.*?)\"$")
    public void loginWithInvalidCred(String email, String password) {
        loginPage = loginPage.loginInvalid(email, password);
    }
    @Given("^I try to log in with empty fields$")
    public void loginEmpty() {
        loginPage.clickLoginButton();
    }

    @Then("^I should not be logged in$")

    @Then("^I should be logged into the page successfully$")
    public void verifyLoginToPage() {
        boolean loggedIn = homePage.isLoggedIn();
        Assert.assertTrue(loggedIn, "I am not logged in");
    }

    @Then("I should be able to see message that my credentials are incorrect")
    public void verifyErrorMessage() {
        Assert.assertTrue(loginPage.errorMessagePresent());
    }
}
