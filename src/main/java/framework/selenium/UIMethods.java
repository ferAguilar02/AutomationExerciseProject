package framework.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import utils.LoggerManager;

public class UIMethods {
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final WebDriver driver = DriverManager.getInstance().getDriver();
    private static final Wait<WebDriver> wait = DriverManager.getInstance().getFluentWait();
    private static final JavascriptExecutor js = (JavascriptExecutor) driver;


    public static void clickWebElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    /**
     * Scrolls down until element is clearly visible on screen
     * @param element is the element the screen will scroll down to
     */
    public static void scrollDownUntilElement(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", element);
    }
}
