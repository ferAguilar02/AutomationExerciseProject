package ui.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.CartCheckoutPageObject;
import utils.LoggerManager;

/**
 *  This class represents the main elements  of the Cart page
 *  @author  Fernanda Aguilar
 *  @version 1.0
 */
public class CartPage extends CartCheckoutPageObject {
    private static final LoggerManager log = LoggerManager.getInstance();

    @FindBy(xpath = "//div[@id='cart_info'][@class='table-responsive cart_info']")
    WebElement cartInfo;

    @FindBy(xpath = "//a[@class='btn btn-default check_out']")
    WebElement proceedToCheckout;

    @FindBy(id = "empty_cart")
    WebElement isEmpty;

    @FindBy(xpath = "//p/a[@href='/products']")
    WebElement productLink;

    @Override
    public void waitUntilPageObjectIsLoaded() {
        cartInfo = wait.until(ExpectedConditions.visibilityOf(cartInfo));
    }

    public CartPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    /**
     * Removes a product from cart
     * @param productName to be removed
     */
    public void removeFromCart(String productName) {
        String prodXpath = String.format("//a[text()='%s']//ancestor::tr/descendant::a[@class=\"cart_quantity_delete\"]",productName);
        WebElement removeProduct = driver.findElement(By.xpath(prodXpath));
        removeProduct.click();
        log.info("Removing products from cart");
    }

    /**
     * Clicks on the proceed to checkout button
     */
    public CheckoutPage proceedToCheckout() {
        proceedToCheckout.click();
        log.info("Checking out");
        return new CheckoutPage();
    }

    /**
     * Verifies whether the cart is empty or not
     */
    public boolean cartIsEmpty() {
        isEmpty = wait.until(ExpectedConditions.visibilityOf(isEmpty));
        return isEmpty.getAttribute("style").contains("display: block;");
    }

    /**
     * Clicks on the product link provided when the cart is empty
     */
    public ProductPage selectProductLink() {
        productLink = wait.until(ExpectedConditions.elementToBeClickable(productLink));
        productLink.click();
        log.info("Clicking link provided by empty cart");
        return new ProductPage();
    }

    /**
     * Verifies whether the checkout button is visible or not
     */
    public boolean checkoutButtonVisible() {
        boolean exists = driver.findElements(By.xpath("//a[@class='btn btn-default check_out']") ).size() != 0;
        return exists;
    }

    /**
     * Obtains the price of a product added to cart
     * @param product
     */
    public int getPrice(String product) {
        String priceXpath = String.format("//a[text()='%s']//ancestor::tr/descendant::td[@class='cart_price']/p", product);
        WebElement getPrice = driver.findElement(By.xpath(priceXpath));
        return  Integer.parseInt(getPrice.getText().replace("Rs. ", ""));
    }

    /**
     * Obtains the Quantity of a product added to cart
     * @param product
     */
    public int getQuantity(String product) {
        String quantityXpath = String.format("//a[text()='%s']//ancestor::tr/descendant::button[@class='disabled']", product);
        WebElement getQuantity = driver.findElement(By.xpath(quantityXpath));
        return Integer.parseInt(getQuantity.getText());
    }

    /**
     * Obtains the Total of a product added to cart
     * @param product
     */
    public int getTotal(String product) {
        String total = String.format("//a[text()='%s']//ancestor::tr/descendant::td[@class='cart_total']/p", product);
        WebElement totalCost = driver.findElement(By.xpath(total));
        return Integer.parseInt(totalCost.getText().replace("Rs. ", ""));
    }

    /**
     * Verifies whether a product's description is displayed
     * @param product
     */
    public boolean isDescriptionDisplayed(String product) {
        String description = String.format("//a[text()='%s']", product);
        WebElement descriptionText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(description)));
        return  descriptionText.isDisplayed();
    }

    /**
     * Verifies whether a product's price is displayed
     * @param product
     */
    public boolean isPriceDisplayed(String product) {
        String price = String.format("//a[text()='%s']//ancestor::tr/descendant::td[@class='cart_price']/p", product);
        WebElement priceText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(price)));
        return  priceText.isDisplayed();
    }

    /**
     * Verifies whether a product's quantity is displayed
     * @param product
     */
    public boolean isQuantityDisplayed(String product) {
        String quantity = String.format("//a[text()='%s']//ancestor::tr/descendant::button[@class='disabled']", product);
        WebElement quantityText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(quantity)));
        return quantityText.isDisplayed();
    }

    /**
     * Verifies whether a product's total cost is displayed
     * @param product
     */
    public boolean isTotalDisplayed(String product) {
        String total = String.format("//a[text()='%s']//ancestor::tr/descendant::td[@class='cart_total']/p", product);
        WebElement totalText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(total)));
        return totalText.isDisplayed();
    }

    /**
     * Verifies whether a product's image is displayed
     * @param product
     */
    public boolean isImageDisplayed(String product) {
        String image = String.format("//a[text()='%s']//ancestor::tr/descendant::td[@class='cart_product']", product);
        WebElement productImage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(image)));
        return productImage.isDisplayed();
    }
}
