package ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.MainPageObject;

/**
 *  This class represents the main elements  of the Product Information Page
 *  @author  Fernanda Aguilar
 *  @version 1.0
 */
public class ProductInfoPage extends MainPageObject {

    @FindBy(xpath = "//div[@class = 'product-information']")
    WebElement productInfo;

    @FindBy(id = "quantity")
    WebElement productQuantity;

    @FindBy(xpath = "//button[@class = 'btn btn-default cart']")
    WebElement addToCart;

    public ProductInfoPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        productInfo = wait.until(ExpectedConditions.visibilityOf(productInfo));
    }

    /**
     * Logs the user into their account
     */
    public void setProductQuantity(String quantity) {
        productQuantity.click();
        productQuantity.clear();
        productQuantity.sendKeys(quantity);
    }

    /**
     * Add product to cart
     */
    public void addProductToCart() {
        addToCart.click();
    }
}
