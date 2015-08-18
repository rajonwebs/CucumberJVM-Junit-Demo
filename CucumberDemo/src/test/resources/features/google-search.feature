@google
Feature: Test the Google Search Functionality
	
@navigate
  Scenario: I make sure that I am landed on Google Home page
    Given I navigate to google.com
    And I should be landed to google home page
    
@search
  Scenario: I can search in Google
    Given I navigate to google.com
    And I should be landed to google home page
    Then I search for the keyword 'Shawkath Khan'
    And I click on one first search result
