package ui.pages;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.CartCheckoutPageObject;

/**
 *  This class represents the main elements  of the Checkout Page
 *  @author  Fernanda Aguilar
 *  @version 1.0
 */
public class CheckoutPage extends CartCheckoutPageObject {

    @FindBy(xpath = "//a[@class = 'btn btn-default check_out']")
    WebElement checkoutButton;

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        checkoutButton = wait.until(ExpectedConditions.visibilityOf(checkoutButton));
    }
}
