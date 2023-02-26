@Login @UI
Feature: Login

  @LoginValid
  Scenario: A registered user should be able to login with valid credentials
    Given I navigate to Admin Login page
    When I login to AutomationExercise page with valid credentials
    Then I should be logged into the page successfully

  @LoginInvalid
  Scenario Outline: A user should not be able to login with invalid credentials
    Given I navigate to Admin Login page
    When I login to AutomationExercise page with invalid credentials "<Email>" "<Password>"
    And I should be able to see message that my credentials are incorrect
    Examples:
      |Email                 |  Password |
      |ferAguilar@gmail.com  |  invalid |
      |invalid@gmail.com     |  invalid |
