package com.jalasoft.automation.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import ui.PageTransporter;
import ui.controller.UIController;
import ui.methods.CommonMethods;
import ui.pages.CartPage;
import ui.pages.HomePage;
import ui.pages.LoginPage;


public class ProductHooks {
    private final PageTransporter pageTransporter;
    private final UIController controller;
    private CartPage cartPage;
    private HomePage homePage;
    private LoginPage loginPage;

    public ProductHooks(UIController controller) {
        this.pageTransporter = PageTransporter.getInstance();
        this.controller = controller;
    }

    @Before(order = 0, value ="@RemoveProduct or @RedirectCheckout or @CheckoutEmptyCart or @AddInvalidQuantity or @CorrectTotal" )
    public void login() {
        loginPage = pageTransporter.navigateToLoginPage();
        homePage = loginPage.login();
    }

   @Before("@RemoveProduct or @RedirectCheckout or @CheckoutWithoutLogin")
    public void addProductToCart() {
     homePage = pageTransporter.navigateToHomePage();
     controller.setProduct("Stylish Dress");
     homePage.addToCart("Stylish Dress");
     homePage.addedPopUp.continueShopping();
   }

   @After("@RedirectCheckout or @CheckoutWithoutLogin or @AddInvalidQuantity or @CorrectTotal or @AddProductToCart or @ContinueShopping or @ViewCart")
    public void removeFromCart() {
        cartPage = pageTransporter.navigateToCartPage();
        cartPage.removeFromCart(controller.getProduct());
   }

   @After(order = 0, value = "@RemoveProduct or @LoginValid or @RedirectCheckout or @CheckoutEmptyCart or @AddInvalidQuantity or @CorrectTotal")
    public void logout() {
       CommonMethods.logout();
   }
}
