package com.jalasoft.automation.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import ui.PageTransporter;
import ui.controller.UIController;
import ui.pages.*;

public class CartSteps {
    private final PageTransporter pageTransporter;
    private final UIController controller;
    private ProductPage productPage;
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private ProductInfoPage productInfoPage;


    public CartSteps(UIController controller) {
        this.pageTransporter = PageTransporter.getInstance();
        this.controller = controller;
    }

    @Given("^the user navigates to the Automation Exercise HomePage$")
    public void navigateToHomePage() {
        homePage = pageTransporter.navigateToHomePage();
    }

    @Given("^the user goes to cart page$")
    public void goToCart() {
        cartPage = homePage.pageHeader.goToCartPage();
    }

    @Given("^the user views product \"(.*?)\"$")
    public void viewProduct(String product) {
        controller.setProduct(product);
        productInfoPage = homePage.viewProduct(product);
    }

    @Given("^the user removes a product from the cart$")
    public void removeProduct() {
        cartPage.removeFromCart(controller.getProduct());
    }

    @Given("^the user proceeds to checkout$")
    public void proceedToCheckout() {
        checkoutPage = cartPage.proceedToCheckout();
    }

    @Given("^the user adds the product \"(.*?)\" to cart$")
    public void addToCart(String product) {
        homePage.addToCart(product);
        controller.setProduct(product);
        homePage.addedPopUp.continueShopping();
    }

    @Then("^the user should see an empty cart$")
    public void seeEmptyCart() {
        Assert.assertTrue(cartPage.cartIsEmpty());
    }

    @Then("^the user begins shopping with the provided link$")
    public void verifyProductLink() {
        productPage = cartPage.selectProductLink();
        String actualPage = productPage.getProductTitle();
        String expected = "All Products";
        Assert.assertEquals(actualPage, expected);
    }

    @Then("^the user sets a quantity of \"(.*?)\"$")
    public void setQuantity(String quantity) {
        controller.setQuantity(quantity);
        productInfoPage.setProductQuantity(quantity);
    }

    @Then("^the user adds the product to cart$")
    public void addProdToCart() {
        productInfoPage.addProductToCart();
    }

    @Then("^the product should not appear in cart$")
    public void verifyProductNotInCart() {
        cartPage = productInfoPage.addedPopUp.viewCart();
        Assert.assertTrue(cartPage.cartIsEmpty());
    }
    @Then("^the product should have been removed from cart$")
    public void verifyProductWasRemoved() {
        Assert.assertTrue(cartPage.cartIsEmpty());
    }

    @Then("^the user should be on Checkout Page$")
    public void verifyCheckout() {
        String actualBreadcrumb = checkoutPage.breadcrumbs.getBreadcrumb();
        String expectedBreadcrumb = "Checkout";
        Assert.assertEquals(actualBreadcrumb, expectedBreadcrumb);
    }

    @Then("^a pop up should be displayed indicating user can't checkout unless they login or register$")
    public void verifyCheckoutPopUp() {
        Assert.assertTrue(checkoutPage.checkoutPopUp.verifyUserMustLogIn());
    }

    @Then("^the user can't checkout because cart is empty$")
    public void cannotCheckout() {
        Assert.assertFalse(cartPage.checkoutButtonVisible());
    }

    @Then("^the total should be \"(.*?)\"$")
    public void verifyTotal(String total) {
        cartPage = productInfoPage.addedPopUp.viewCart();
        int actualTotal = cartPage.getTotal(controller.getProduct());
        int expectedTotal = Integer.parseInt(total);
        Assert.assertEquals(actualTotal, expectedTotal);
    }
    @Then("^the user should see the information of the product that was added to cart$")
    public void verifyProductInCart() {
        Assert.assertTrue(cartPage.isDescriptionDisplayed(controller.getProduct()));
        Assert.assertTrue(cartPage.isImageDisplayed(controller.getProduct()));
        Assert.assertTrue(cartPage.isPriceDisplayed(controller.getProduct()));
        Assert.assertTrue(cartPage.isTotalDisplayed(controller.getProduct()));
        Assert.assertTrue(cartPage.isQuantityDisplayed(controller.getProduct()));
    }

}
