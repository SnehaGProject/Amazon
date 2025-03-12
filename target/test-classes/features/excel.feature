Feature: Product Search and Price Recording

  Scenario: Search product from Excel and record the price
    When user search a product from excel file
    And clicks on search
    And user validates description
    Then write product price in excel sheet
