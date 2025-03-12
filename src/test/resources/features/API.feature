Feature: Product Search and Click

  Scenario: Search for products and click on them dynamically
    Given I send a GET request to retrieve product data
    When I extract product names from the response
    Then I dynamically click on each product on the webpage