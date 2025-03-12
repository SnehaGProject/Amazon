Feature: Flipkart Checkout Feature

  Background:
    Given user navigates to the Flipkart homepage

  @Checkout
  Scenario: Add an item to cart and proceed to checkout
    When user searches for a product "BAGS"
    And user adds the first item to the cart
    And user proceeds to the cart
    Then user should see the product in the cart
    And user proceeds to checkout
    And user verifies the total price

  Scenario: Validate cart is empty after removing the item
    When user searches for a product "BAGS"
    And user adds the first item to the cart
    And user proceeds to the cart
    Then user should see the product in the cart
    When user removes the item from the cart
    Then user should see the cart is empty

  Scenario: Validate correct product name is in the cart
    When user searches for a product "MacBook Pro"
    And user adds the first item to the cart
    Then user verifies the correct product is in the cart

  Scenario: Verify search suggestions for a product
    When user types "Samsung Galaxy" in the search bar
    Then user should see search suggestions containing "Samsung Galaxy"
