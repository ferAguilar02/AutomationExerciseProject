package ui.pages;

import framework.CredentialsManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;
import utils.LoggerManager;

/**
 *  This class represents the main elements  of the Login Page
 *  @author  Fernanda Aguilar
 *  @version 1.0
 */
public class LoginPage extends BasePageObject{

        private static final LoggerManager log = LoggerManager.getInstance();

        @FindBy(xpath ="//input[@data-qa= 'login-email']")
        WebElement emailTextBox;

        @FindBy(xpath = "//input[@data-qa= 'login-password']")
        WebElement passwordTextBox;

        @FindBy(xpath = "//button[@data-qa= 'login-button']")
        WebElement loginSubmitButton;

        @FindBy(xpath = "//p[text()='Your email or password is incorrect!']")
        WebElement invalidCredentials;

        public LoginPage() {
            PageFactory.initElements(driver, this);
            waitUntilPageObjectIsLoaded();
        }

     @Override
     public void waitUntilPageObjectIsLoaded() {
        emailTextBox = wait.until(ExpectedConditions.elementToBeClickable(emailTextBox));
        passwordTextBox = wait.until(ExpectedConditions.elementToBeClickable(passwordTextBox));
        loginSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton));
     }

    /**
     * Sets the email textbox with valid email
     * @param email
     */
     public void setEmailTextBox(String email) {
         log.info("Email set");
         emailTextBox.click();
         emailTextBox.clear();
         emailTextBox.sendKeys(email);
        }

    /**
     * Sets the password textbox with valid password
     * @param password
     */
     public void setPasswordTextBox(String password) {
        log.info("Password set");
        passwordTextBox.click();
        passwordTextBox.clear();
        passwordTextBox.sendKeys(password);
     }

    /**
     * Clicks on the Login Button in the login page
     */
     public void clickLoginButton() {
            loginSubmitButton.submit();
        }

    /**
     * Logs the user into their account
     */
     public HomePage login() {
         log.info("Logging in with valid credentials");
         setEmailTextBox(CredentialsManager.getInstance().getEmail());
         setPasswordTextBox(CredentialsManager.getInstance().getPassword());
         clickLoginButton();
         return new HomePage();
     }
    /**
     * Logs the user into their account with invalid credentials
     * @param invalidEmail
     * @param invalidPassword
     */
     public LoginPage loginInvalid(String invalidEmail, String invalidPassword) {
         log.info("Logging in with invalid credentials");
         setEmailTextBox(invalidEmail);
         setPasswordTextBox(invalidPassword);
         clickLoginButton();
         return new LoginPage();
        }

    /**
     * Verifies whether login error message is present
     */
     public boolean errorMessagePresent() {
            return invalidCredentials.isDisplayed();
        }

}
