Feature: Products
  @sneha
  Scenario Outline: User Login
    When user should enter "<item>" to Search
    Then items should be displayed

    Examples:
      | item                 |
      | Nike Shoes For Women |