package ui.methods;

import ui.PageTransporter;
import ui.pages.HomePage;
import utils.LoggerManager;

public class CommonMethods {
    private static final LoggerManager log = LoggerManager.getInstance();
    private static final PageTransporter pageTransporter = PageTransporter.getInstance();
    private static HomePage homePage;

    public static void logout() {
        log.info("Logging out");
        homePage = pageTransporter.navigateToHomePage();
        homePage.pageHeader.logout();
    }
}