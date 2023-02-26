package ui.pages;

import framework.selenium.UIMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.MainPageObject;

/**
 *  This class represents the main elements  of the Home Page
 *  @author  Fernanda Aguilar
 *  @version 1.0
 */
public class HomePage extends MainPageObject {

    @FindBy(xpath = "//div[@class ='carousel-inner']")
    WebElement carousel;

    @FindBy(xpath = "//i[@class='fa fa-user']")
    WebElement loggedInAs;

    @FindBy(xpath = "//h2[@class = 'title text-center']//ancestor::div[@class='features_items']")
    WebElement pageTitle;

    @FindBy(xpath = "//a[@href='/logout']")
    WebElement logoutButton;


    public HomePage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        carousel = wait.until(ExpectedConditions.visibilityOf(carousel));
        pageTitle = wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }

    /**
     * Verifies whether the user is logged in or not
     */
    public boolean isLoggedIn() {
        if(loggedInAs.isDisplayed() && logoutButton.isDisplayed()) {
            return true;
        }
        return false;
    }

    /**
     * Adds a product to cart
     * @param product
     */
    public void addToCart(String product) {
        String addToCartXpath = String.format("//p[text()='%s']/parent::div[@class='productinfo text-center']/descendant::a[text()='Add to cart']", product);
        WebElement addToCartButton = driver.findElement(By.xpath(addToCartXpath));
        UIMethods.scrollDownUntilElement(addToCartButton);
        addToCartButton.click();
    }

    /**
     * Selects a product to view its information
     * @param product
     */
    public ProductInfoPage viewProduct(String product) {
        String viewProductButtonXpath = String.format("//p[text() ='%s']//ancestor::div[@class='product-image-wrapper']//a[text() = 'View Product']", product);
        WebElement viewProductButton = driver.findElement(By.xpath(viewProductButtonXpath));
        viewProductButton.click();
        return new ProductInfoPage();
    }
}
