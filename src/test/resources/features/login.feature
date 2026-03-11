Feature: Login functionality
  As a user of SauceDemo
  I want to test the login functionality
  So that I can verify it works correctly

  Scenario Outline: UC-1 - Login with empty credentials
    Given I am on the login page
    When I enter username "<username>"
    And I enter password "<password>"
    And I clear the username field
    And I clear the password field
    And I click the login button
    Then I should see the error message "<error_message>"

    Examples:
      | username      | password     | error_message                          |
      | standard_user | secret_sauce | Epic sadface: Username is required     |

  Scenario Outline: UC-2 - Login with username only and password cleared
    Given I am on the login page
    When I enter username "<username>"
    And I enter password "<password>"
    And I clear the password field
    And I click the login button
    Then I should see the error message "<error_message>"

    Examples:
      | username      | password     | error_message                          |
      | standard_user | secret_sauce | Epic sadface: Password is required     |

  Scenario Outline: UC-3 - Valid login
    Given I am on the login page
    When I enter username "<username>"
    And I enter password "<password>"
    And I click the login button
    Then I should see the dashboard title "<title>"

    Examples:
      | username      | password     | title     |
      | standard_user | secret_sauce | Swag Labs |
      | visual_user   | secret_sauce | Swag Labs |
