package ui.components;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;

/**
 *  This class represents the breadcrumbs that only appear in Cart and
 *   checkout page
 *  @author  Fernanda Aguilar
 *  @version 1.0
 */
public class Breadcrumbs extends BasePageObject {

    @FindBy(xpath = "//li[@class ='active']")
    WebElement breadcrumbs;

    @Override
    public void waitUntilPageObjectIsLoaded() throws WebDriverException {
        breadcrumbs = wait.until(ExpectedConditions.visibilityOf(breadcrumbs));
    }

    /**
     * Retrieves the value of the current breadcrumb
     */
    public String getBreadcrumb() {
        String value = breadcrumbs.getText();
        return value;
    }
}
