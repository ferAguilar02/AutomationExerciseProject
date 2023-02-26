@Cart @UI
Feature: Cart

  @AddedProductAppearsInCart
  Scenario Outline: A user should be able to see the information of the products added to cart
    Given the user navigates to the Automation Exercise HomePage
    When the user adds the product "<Product>" to cart
    Then the user goes to cart page
    Then the user should see the information of the product that was added to cart
    Examples:
      | Product   |
      |Blue Top   |

  @EmptyCartLink
  Scenario: A user should be provided with a link to products when cart is empty
    Given the user navigates to the Automation Exercise HomePage
    When the user goes to cart page
    Then the user should see an empty cart
    And the user begins shopping with the provided link

  @RemoveProduct
  Scenario: A user should be able to remove a product from cart
    Given the user navigates to the Automation Exercise HomePage
    When the user goes to cart page
    And the user removes a product from the cart
    Then the product should have been removed from cart

  @AddInvalidQuantity @Negative
  Scenario Outline: A user should not be able to add a product to cart with a quantity less than 1
    Given the user navigates to the Automation Exercise HomePage
    When the user views product "<Product>"
    Then the user sets a quantity of "<Quantity>"
    And the user adds the product to cart
    And the product should not appear in cart
   Examples:
    | Product   | Quantity  |
    |Blue Top   |    0      |
    |Winter Top |   -3      |


  @RedirectCheckout
  Scenario: A user should be redirected to checkout if they are logged in and have products in cart
    Given the user navigates to the Automation Exercise HomePage
    When the user goes to cart page
    And the user proceeds to checkout
    Then the user should be on Checkout Page

  @CheckoutWithoutLogin
  Scenario: A user should not be redirected to checkout if they're not logged in
    Given the user navigates to the Automation Exercise HomePage
    When the user goes to cart page
    And the user proceeds to checkout
    Then a pop up should be displayed indicating user can't checkout unless they login or register

  @CheckoutEmptyCart
  Scenario: The user should not be able to checkout if the cart is empty
    Given the user navigates to the Automation Exercise HomePage
    When the user goes to cart page
    Then the user can't checkout because cart is empty

  @CorrectTotal
  Scenario Outline: The total cost that appears in cart should be correct
    Given the user navigates to the Automation Exercise HomePage
    When the user views product "<Product>"
    Then the user sets a quantity of "<Quantity>"
    And the user adds the product to cart
    And the total should be "<Total>"
    Examples:
      | Product   | Quantity  | Total   |
      |Blue Top   |    3      | 1500    |