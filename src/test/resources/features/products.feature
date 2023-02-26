@Products @UI
Feature: Posts

  @AddProductToCart
  Scenario Outline: A user should be able to directly add a product to cart
    Given the user navigates to the AutomationExercise HomePage
    When the user goes to Products Page
    And the user adds "<Product>" to cart
    Then the product should have been added to cart successfully

    Examples:
     | Product          |
     | Blue Top         |
     | Men Tshirt       |

  @FilterByBrand
  Scenario Outline: A user should be able to filter products by brand
    Given the user navigates to the AutomationExercise HomePage
    When the user goes to Products Page
    And the user selects the brand "<Brand>"
     Then the products page should only display the products from the brand like "<Product>"

    Examples:
      | Brand       | Product          |
      | Madame      | Sleeveless Dress |


  @FilterByCategory
  Scenario Outline: A user should be able to filter products by the different Categories that exist
    Given the user navigates to the AutomationExercise HomePage
    When the user goes to Products Page
    And the user selects the category "<Category>" and the subcategory "<SubCategory>"
    Then the products page should only display the products that belong to "<Category>" and "<SubCategory>"

    Examples:
      |Category  | SubCategory    |
      |Women     | Dress          |
      |Women     | Tops           |
      |Women     | Saree          |
      |Men       | Tshirts        |
      |Men       | Jeans          |
      |Kids      | Dress          |


  @SearchForProduct
  Scenario Outline: A user should be able to search for a specific product
    Given the user navigates to the AutomationExercise HomePage
    When the user goes to Products Page
    And the user searches for the product "<Product>"
    Then the products page should only display the Product I searched for
    Examples:
      | Product     |
      |Stylish Dress|
      | Blue Top    |

  @SearchWithSpecialCharacters @Negative
  Scenario Outline: A user should not receive any results when searching for special characters
    Given the user navigates to the AutomationExercise HomePage
    When the user goes to Products Page
    And the user searches for the product "<Product>"
    Then the products page should not display any results
    Examples:
      | Product     |
      |    &%*%     |


  @ContinueShopping
  Scenario Outline: A user should be able to continue shopping after adding a product to cart
    Given the user navigates to the AutomationExercise HomePage
    When the user goes to Products Page
    And the user adds "<Product>" to cart
    Then the user decides to continue shopping

    Examples:
      | Product          |
      | Blue Top         |

  @ViewCart
  Scenario Outline: A user should be able to View Cart after adding a product to cart
    Given the user navigates to the AutomationExercise HomePage
    When the user goes to Products Page
    And the user adds "<Product>" to cart
    Then the user decides to view Cart

    Examples:
      | Product          |
      | Blue Top         |