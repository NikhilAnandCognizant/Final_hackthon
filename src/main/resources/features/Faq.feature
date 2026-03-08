Feature: Faq
  @Faq
  Scenario: Faq search
    Given User is on the FaqHomepage
    When User serches jo
    Then search result module should be appear and headline should be shown



#    Examples:
#      | username | password    | expected_result   |
#      | admin    | admin123    | Welcome Admin     |
#      | user1    | pass123     | Welcome User      |
#      | guest    | wrong_pass  | Invalid Login     | // this is like scneiro outline