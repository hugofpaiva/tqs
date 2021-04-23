
Feature: Travel Agency Web App

  Scenario: Go to the Simple Travel Agency site
    When I navigate to "https://blazedemo.com/"
    Then The title of the page should be "BlazeDemo"

  Scenario: Find Flights From Paris to Buenos Aires
    When I navigate to "https://blazedemo.com/"
    And I select departure city "Paris"
    And I select destination city "Buenos Aires"
    And I click "Find Flights"
    Then I should be shown results of "Flights from Paris to Buenos Aires"

  Scenario: Find Flights From Mexico City to New York
    When I navigate to "https://blazedemo.com/"
    And I select departure city "Mexico City"
    And I select destination city "New York"
    And I click "Find Flights"
    Then I should be shown results of "Flights from Mexico City to New York"

  Scenario: Buy one Flight From Portland to Berlin
    When I navigate to "https://blazedemo.com/"
    And I select departure city "Portland"
    And I select destination city "Berlin"
    And I click "Find Flights"
    Then I should be shown results of "Flights from Portland to Berlin"
    When I click "Choose This Flight"
    And I enter my Name "Hugo Almeida"
    And I enter my Address "UA"
    And I enter my City "Aveiro"
    And I enter my State "Aveiro"
    And I enter my Zip Code 12345
    And I enter my Credit Card Number 12345
    And I enter my Name on Card "Hugo Almeida"
    And I click "Purchase Flight"
    Then The purchase should be confirmed
    And The title of the page should be "BlazeDemo Confirmation"