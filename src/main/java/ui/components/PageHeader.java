package ui.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;
import ui.pages.CartPage;
import ui.pages.HomePage;
import ui.pages.LoginPage;
import ui.pages.ProductPage;

/**
 *  This class represents the main elements  of the header
 *  @author  Fernanda Aguilar
 *  @version 1.0
 */

public class PageHeader extends BasePageObject {
    @FindBy(xpath = "//a[@href='/products']")
    WebElement productsButton;

    @FindBy(xpath = "//a[@href='/view_cart']")
    WebElement cartButton;

    @FindBy(xpath = "//a[@href='/login']")
    WebElement loginButton;

    @FindBy(xpath = "//i[@class='fa fa-home']")
    WebElement homeButton;

    @FindBy(xpath = "//a[@href='/logout']")
    WebElement logoutButton;

    public PageHeader() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {

    }

    /**
     * Clicks on the Cart option from the header
     */
    public CartPage goToCartPage() {
        cartButton = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cartButton.click();
        return new CartPage();
    }

    /**
     * Clicks on the Product option from the header
     */
    public ProductPage goToProductsPage() {
        productsButton = wait.until(ExpectedConditions.elementToBeClickable(productsButton));
        productsButton.click();
        return new ProductPage();
    }

    /**
     * Clicks on the Home option from the header
     */
    public HomePage goToHomePage() {
        homeButton = wait.until(ExpectedConditions.elementToBeClickable(homeButton));
        homeButton.click();
        return new HomePage();
    }

    /**
     * Clicks on the Login option from the header
     */
    public LoginPage goToLoginPage() {
        loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
        return new LoginPage();
    }

    /**
     * Clicks on the Logout option from the header
     */
    public void logout() {
        logoutButton.click();
    }
}
