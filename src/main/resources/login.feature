Feature: User Login

  Scenario: Invalid login shows error message
    Given User is on the Naukri homepage
    When User enters invalid username "wrong@test.com" and password "12345"
    Then An error message "Invalid details" should be displayed