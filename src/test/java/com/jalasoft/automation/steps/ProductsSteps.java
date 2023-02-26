package com.jalasoft.automation.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import ui.PageTransporter;
import ui.controller.UIController;
import ui.pages.HomePage;
import ui.pages.ProductPage;

public class ProductsSteps {
    private final PageTransporter pageTransporter;
    private final UIController controller;
    private HomePage homePage;
    private ProductPage productPage;

    public ProductsSteps(UIController controller) {
        this.pageTransporter = PageTransporter.getInstance();
        this.controller = controller;
    }

    @Given("^the user navigates to the AutomationExercise HomePage$")
    public void navigateToHomePage() {
        homePage = pageTransporter.navigateToHomePage();
    }

    @Given("^the user goes to Products Page$")
    public void goToProductsPage() {
        productPage = homePage.pageHeader.goToProductsPage();
    }

    @When("^the user adds \"(.*?)\" to cart$")
    public void addProductToCart(String product) {
        controller.setProduct(product);
        productPage.addToCart(product);
    }

    @When("^the user selects the brand \"(.*?)\"$")
    public void selectBrand(String brand) {
        productPage.leftSidebar.selectBrand(brand);
        controller.setBrand(brand);
    }

    @When("^the user selects the category \"(.*?)\" and the subcategory \"(.*?)\"$")
    public void selectCategories(String category, String subCategory) {
        productPage.leftSidebar.selectACategory(category);
        productPage.leftSidebar.selectSubCategory(category, subCategory);
    }

    @When("^the user searches for the product \"(.*?)\"$")
    public void searchForProduct(String product) {
        productPage.searchProduct(product);
        controller.setProduct(product);
        productPage.clickSearchButton();
    }

    @Then("^the product should have been added to cart successfully$")
    public void verifyProductAddedSuccessfully() {
        Assert.assertTrue(productPage.addedPopUp.verifyProductWasAdded());
    }

    @Then("^the products page should only display the products from the brand like \"(.*?)\"$")
    public void verifyOnlyBrandIsDisplayed(String product) {
        String currentTitle = productPage.getProductTitle();
        Assert.assertTrue(currentTitle.contains("Brand - " + controller.getBrand()));
        Assert.assertTrue(productPage.productIsDisplayed(product));
    }

    @Then("^the products page should only display the products that belong to \"(.*?)\" and \"(.*?)\"$")
    public void verifyOnlyCategoryIsDisplayed(String category, String subCategory) {
        String currentTitle = productPage.getProductTitle();
        Assert.assertTrue(currentTitle.contains(category+ " - " + subCategory));
    }

    @Then("^the products page should only display the Product I searched for$")
    public void verifySearchResult() {
        int actualResults = productPage.getNumberOfProductsOnPage();
        int expectedResults = 1;
        String actualResultName = productPage.getNameOfProduct();
        Assert.assertEquals(actualResults, expectedResults);
        Assert.assertEquals(actualResultName,controller.getProduct());
        Assert.assertEquals(productPage.getSearchBoxValue(), controller.getProduct());
    }

    @Then("^the products page should not display any results$")
    public void verifyNoResultsWereFound() {
        int actualResults = productPage.getNumberOfProductsOnPage();
        int expectedResults = 0;
        Assert.assertEquals(actualResults, expectedResults);
    }

    @Then("^the user decides to continue shopping$")
    public void continueShopping() {
        productPage.addedPopUp.continueShopping();
        Assert.assertTrue(pageTransporter.isOnProductPage());
    }

    @Then("^the user decides to view Cart")
    public void viewCart() {
        productPage.addedPopUp.viewCart();
        Assert.assertTrue(pageTransporter.isOnCartPage());
    }

}
