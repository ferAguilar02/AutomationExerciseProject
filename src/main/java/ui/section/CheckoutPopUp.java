package ui.section;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;
import ui.pages.CartPage;
import ui.pages.LoginPage;

/**
 *  This class represents the pop-up that appears after trying to check out without being logged in
 *  @author  Fernanda Aguilar
 *  @version 1.0
 */
public class CheckoutPopUp extends BasePageObject {

    @FindBy(xpath = "//div[@class = 'modal-dialog modal-confirm']")
    WebElement popUp;

    @FindBy(xpath = "//p[text() = 'Register / Login account to proceed on checkout.']")
    WebElement checkoutText;

    @FindBy(xpath = "//a[@href='/login']//u")
    WebElement loginLink;

    @FindBy(xpath = "//button[@class='btn btn-success close-checkout-modal btn-block']")
    WebElement continueOnCart;

    @Override
    public void waitUntilPageObjectIsLoaded() {
        popUp = wait.until(ExpectedConditions.visibilityOf(popUp));
    }

    /**
     * Click on Continue on Cart Button
     */
     public CartPage continueOnCart() {
        continueOnCart.click();
        return new CartPage();
     }

    /**
     * Clicks on Login/register link provided in pop-up
     */
     public LoginPage goToLogin() {
        loginLink.click();
        return new LoginPage();
     }

    /**
     * Verifies Pop-up displays correct message
     */
     public boolean verifyUserMustLogIn() {
        return checkoutText.isDisplayed();
     }

}
