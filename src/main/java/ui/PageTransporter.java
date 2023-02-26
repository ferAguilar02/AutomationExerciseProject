package ui;

import framework.CredentialsManager;
import framework.selenium.DriverManager;
import org.openqa.selenium.WebDriver;
import ui.pages.CartPage;
import ui.pages.HomePage;
import ui.pages.LoginPage;
import ui.pages.ProductPage;
import utils.LoggerManager;

import java.time.Duration;

public class PageTransporter {
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final CredentialsManager credentialsManager = CredentialsManager.getInstance();

    private WebDriver driver;
    private String loginURL;
    private String homeURL;
    private String productsURL;
    private String cartURL;
    private static PageTransporter instance;

    protected PageTransporter() {
        initialize();
    }

    public static PageTransporter getInstance() {
        if (instance == null) {
            instance = new PageTransporter();
        }
        return instance;
    }

    private void initialize() {
        log.info("Initializing Page Transporter");
        loginURL = credentialsManager.getLoginURL();
        homeURL = credentialsManager.getHomeURL();
        productsURL = credentialsManager.getProductsURL();
        cartURL = credentialsManager.getCartURL();
        driver = DriverManager.getInstance().getDriver();
    }

    private void goToURL(String url) {
        driver.navigate().to(url);
    }

    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains(loginURL);
    }

    public boolean isOnHomePage() {
        return driver.getCurrentUrl().contains(homeURL);
    }

    public boolean isOnProductPage() {
        return driver.getCurrentUrl().contains(productsURL);
    }

    public boolean isOnCartPage() {
        return driver.getCurrentUrl().contains(cartURL);
    }

    public HomePage navigateToHomePage() {

            goToURL(homeURL);

        return new HomePage();
    }

    public LoginPage navigateToLoginPage() {
        if (!isOnLoginPage()) {
            goToURL(loginURL);
        }
        return new LoginPage();
    }

    public ProductPage navigateToProductPage() {
        if (!isOnLoginPage()) {
            goToURL(productsURL);
        }
        return new ProductPage();
    }
    public CartPage navigateToCartPage() {
        if (!isOnLoginPage()) {
            goToURL(cartURL);
        }
        return new CartPage();
    }

}
