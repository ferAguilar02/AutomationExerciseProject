package ui.pages;

import framework.selenium.UIMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.MainPageObject;
import java.time.Duration;
import java.util.List;

/**
 *  This class represents the main elements  of the Product Page
 *  @author  Fernanda Aguilar
 *  @version 1.0
 */
public class ProductPage extends MainPageObject {

    @FindBy(id = "submit_search")
    WebElement searchProduct;

    @FindBy(id = "search_product")
    WebElement searchProductTextBox;

    @FindBy(xpath = "//div[@class='features_items']/h2[@class='title text-center']")
    WebElement productPageTitle;

    @FindBy(xpath = "//div[@class= 'col-sm-4']//descendant::div[@class='productinfo text-center']//descendant::p")
    WebElement singleProductName;


    public ProductPage() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
    }

    /**
     * Enters product name for search
     * @param product
     */
    public void searchProduct(String product) {
        searchProductTextBox.click();
        searchProductTextBox.sendKeys(product);
    }

    /**
     * Clicks search button
     */
    public void clickSearchButton() {
        searchProduct.click();
    }

    /**
     * Retrieves the title of the product page being displayed
     */
    public String getProductTitle() {
        return productPageTitle.getAttribute("innerHTML");
    }

    /**
     * Adds product to cart
     */
    public void addToCart(String product) {
        String addToCartXpath = String.format("//p[text()='%s']/parent::div[@class='productinfo text-center']/descendant::a[text()='Add to cart']", product);
        WebElement addToCartButton = driver.findElement(By.xpath(addToCartXpath));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(11));
        UIMethods.scrollDownUntilElement(addToCartButton);
        addToCartButton.click();
    }

    /**
     * Verifies if the product is displayed
     */
    public boolean productIsDisplayed(String product) {
        String productXpath = String.format("//div[@class = 'productinfo text-center']//descendant::p[text() = '%s']", product);
        WebElement productName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(productXpath)));
        return productName.isDisplayed();
    }

    /**
     * Returns the number of products on the page
     */
    public int getNumberOfProductsOnPage() {
        List<WebElement> listResult = driver.findElements(By.xpath( "//div[@class='features_items']//ancestor::div[@class= 'col-sm-4']"));
        return listResult.size();
    }

    /**
     * Returns the name of the product displayed on page
     */
    public String getNameOfProduct() {
        String productName = singleProductName.getText();
        return productName;
    }

    /**
     * Returns the text that is currently in the search box
     */
    public String getSearchBoxValue() {
        String value = searchProductTextBox.getAttribute("value");
        return value;
    }

}
