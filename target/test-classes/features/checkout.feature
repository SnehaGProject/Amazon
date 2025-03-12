Feature: Checkout functionality on SwagLabs

Background:
Given User is on SwagLabs login page
When User logs in with valid credentials



  Scenario: Add item to the cart and checkout successfully
    Given User adds "Sauce Labs Backpack" to the cart
    And User proceeds to checkout
    When User enters "FirstName" as "John", "LastName" as "Doe", and "PostalCode" as "12345"
    And user clicks on continue
    And User completes the checkout
    Then User should see the confirmation message "Thank you for your order!"


  Scenario: Empty cart on checkout
    Given User adds "Sauce Labs Backpack" to the cart
    And User removes all items from the cart
    When User proceeds to checkout
    And user clicks on continue
    Then User should see an error message "Error: First Name is required"



  Scenario: Add multiple items to the cart and checkout
    Given User adds "Sauce Labs Backpack" to the cart
    Given User adds "Sauce Labs Bike Light" to the cart
    And User proceeds to checkout
    When User enters "FirstName" as "John", "LastName" as "Doe", and "PostalCode" as "12345"
    And user clicks on continue
    And User completes the checkout
    Then User should see the confirmation message "Thank you for your order!"

  Scenario: Proceed to checkout without first name
    Given User adds "Sauce Labs Backpack" to the cart
    And User proceeds to checkout
    When User enters last name as "Doe" and postal code as "12345"
    And user clicks on continue
    Then User should see an error message "Error: First Name is required"

  Scenario: Proceed to checkout without last name
    Given User adds "Sauce Labs Backpack" to the cart
    And User proceeds to checkout
    When User enters first name as "John" and postal code as "12345"
    Then User should see an error message "Error: Last Name is required"

  Scenario: Proceed to checkout without postal code
    Given User adds "Sauce Labs Backpack" to the cart
    And User proceeds to checkout
    When User enters first name as "John" and last name as "Doe"
    Then User should see an error message "Error: Postal Code is required"

  Scenario: Checkout with invalid postal code
    Given User adds "Sauce Labs Backpack" to the cart
    And User proceeds to checkout
    When User enters "FirstName" as "John", "LastName" as "Doe", and "PostalCode" as "ABCDEF"
    Then User should see an error message "Error: Postal code is invalid"


  Scenario: Cancel checkout
    Given User adds "Sauce Labs Backpack" to the cart
    And User proceeds to checkout
    When User cancels the checkout
    Then User should return to the products page

  @sneha
  Scenario: Verify total price at checkout
    Given User adds "Sauce Labs Backpack" to the cart
    Given User adds "Sauce Labs Bike Light" to the cart
    And User proceeds to checkout
    When User enters "FirstName" as "John", "LastName" as "Doe", and "PostalCode" as "12345"
    And user clicks on continue
    Then User should see the correct total price including taxes