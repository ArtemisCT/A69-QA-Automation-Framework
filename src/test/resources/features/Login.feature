Feature: Login feature

  Scenario:
    Given I open browser
    And I open Login page
    When I enter email "artemisia.chalkiopoulou@testpro.io"
    And I enter password "22002244"
    And I submit
    Then I am logged in
