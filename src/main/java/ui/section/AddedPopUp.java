package ui.section;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;
import ui.pages.CartPage;

/**
 *  This class represents the pop-up that appears after adding a product to cart
 *  @author  Fernanda Aguilar
 *  @version 1.0
 */
public class AddedPopUp extends BasePageObject {

    @FindBy(xpath = "//div[@class = 'modal-dialog modal-confirm']")
    WebElement popUp;

    @FindBy(xpath = "//h4[contains(text(),'Added!')]")
    WebElement addedText;

    @FindBy(xpath = "//a[@href='/view_cart']//u")
    WebElement viewCartLink;

    @FindBy(xpath = "//button[@class='btn btn-success close-modal btn-block']")
    WebElement continueShoppingButton;

    @FindBy(xpath = "//h4[@class='modal-title w-100']")
    WebElement popUpText;

    @Override
    public void waitUntilPageObjectIsLoaded() {
        popUp = wait.until(ExpectedConditions.visibilityOf(popUp));
        popUp = wait.until(ExpectedConditions.visibilityOf(popUpText));
    }

    /**
     * Clicks on Continue Shopping button
     */
     public void continueShopping() {
        continueShoppingButton.click();
     }

    /**
     * Clicks on View Cart Link provided
     */
     public CartPage viewCart() {
        viewCartLink.click();
        return new CartPage();
     }

    /**
     * Verifies that message on the Pop-up says product was added
     */
     public boolean verifyProductWasAdded() {
        popUpText = wait.until(ExpectedConditions.visibilityOf(popUpText));
        return addedText.isDisplayed();
     }

}
