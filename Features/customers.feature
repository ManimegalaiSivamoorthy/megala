Feature: Customers
Background: Below are common for all scenario
  Given User launch the chrome browser
  When user opens URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
  And User enter Email as "admin@yourstore.com" and password as "admin"
  And click on login
  Then User can view the Dashboard

@sanity
  Scenario: Add a new customer
    When User clicks on the customer menu
    And Click on the customer menu item
    And click on add new button
    Then User can view add new customer page
    When User enters customer information
    And click on save button
    Then User can view confirmation message "The new customer has been added successfully"
    And close browser


 @regression
  Scenario: Search customer by emailId
    When User clicks on the customer menu
    And Click on the customer menu item
    And Enter customer email
    When Click on search button
    Then User should find email on the search table
    And  close browser

  @regression
  Scenario: Search customer by name
    When User clicks on the customer menu
    And Click on the customer menu item
    And Create customer page object
    And Enter customer FirstName
    And Enter customer LastName
    When Click on search button
    Then User should find name on the search table
    And  close browser