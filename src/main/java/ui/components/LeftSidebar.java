package ui.components;

import framework.selenium.UIMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 *  This class represents the main elements  of the left sidebar
 *  @author  Fernanda Aguilar
 *  @version 1.0
 */
public class LeftSidebar extends BasePageObject {

    @FindBy(xpath = "//div[@class='panel-group category-products']")
    WebElement categorySection;

    @FindBy(xpath = "//div[@class='brands-name']")
    WebElement brandSection;

    @Override
    public void waitUntilPageObjectIsLoaded() {
        categorySection = wait.until(ExpectedConditions.visibilityOf(categorySection));
        brandSection = wait.until(ExpectedConditions.visibilityOf(brandSection));
    }

    public LeftSidebar() {
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();
    }

    /**
     * Clicks on the specified category
     * @param category is the selected category
     */
    public void selectACategory(String category) {
        String categoryXpath = String.format("//a[normalize-space()='%s']/parent::h4/descendant::span[@class=\"badge pull-right\"]", category);
        WebElement selectCategory = driver.findElement(By.xpath(categoryXpath));
        UIMethods.scrollDownUntilElement(selectCategory);
        selectCategory.click();

    }

    /**
     * Clicks on the selected subcategory
     * @param category is the selected category
     * @param subCategory is the selected subcategory
     */
    public void selectSubCategory(String category, String subCategory) {
        String subCategoryXpath =String.format("//div[@id='%1$s']//descendant::a[contains(text(),'%2$s')]", category, subCategory);
        WebElement selectSubCategory = driver.findElement(By.xpath(subCategoryXpath));
        selectSubCategory = wait.until(ExpectedConditions.visibilityOf(selectSubCategory));
        selectSubCategory.click();
    }

    /**
     * Clicks on the specified brand
     * @param brand is the selected brand
     */
    public void selectBrand(String brand) {
        String brandXpath = String.format("//div[@class='brands-name']/descendant::a[text()='%s']", brand);
        WebElement selectBrand = driver.findElement(By.xpath(brandXpath));
        UIMethods.scrollDownUntilElement(selectBrand);
        selectBrand.click();
    }
}
