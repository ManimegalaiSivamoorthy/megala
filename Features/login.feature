Feature: Login

  @sanity
  Scenario: Successful login with valid credentials
    Given User launch the chrome browser
    When user opens URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User enter Email as "admin@yourstore.com" and password as "admin"
    And click on login
    Then Page title should be "Dashboard / nopCommerce administration"
    When User clicks on log out
    Then Page title should be "Your store. Login"
    And close browser

  @regression
  Scenario Outline: Login Data Driven
    Given User launch the chrome browser
    When user opens URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User enter Email as "<email>" and password as "<password>"
    And click on login
    Then Page title should be "Dashboard / nopCommerce administration"
    When User clicks on log out
    Then Page title should be "Your store. Login"
    And close browser

    Examples:
    |email| password|
    | admin@yourstore.com | admin|
    | admin1@yourstore.com | admin123|