Feature: filter test

@filter
  Scenario: photographer should choose two date the first is the start date and the second is the finish date
    And user click on all filters and should choose the start date and the end date
    Then the user must find the events in the period from start date to finish date
  @filter
  Scenario: photographer can filter by the name of event
    
    And user click on all filters and should write the name of event as "koura"
    Then The user must find the events whose name of event as "koura"
@filter
  Scenario: photographer can filter by the location of event
    
    And user click on all filters and should write the location of event as "tunis"
    Then The user must find the events whose location of event as "tunis"
 @filter
  Scenario: photographer can filter by the status of event
    
    And user click on all filters and should write the status of event
    Then The user must find the events whose status of event as "In progress"
  @filter
  Scenario: photographer can filter by the status of event
    
    And user click on all filters and should write the status of the event
    Then The user must find the events whose have the status of event as "Completed"
@filter
  Scenario: photographer can filter the events of today
    
    And user click on all filters and should click on today
    Then The user must find the events of today
@filter
  Scenario: photographer can filter the events of this week
    
    And user click on all filters and should click on this week
    Then The user must find the events of this week
  @filter
  Scenario: photographer can filter the events of this month
    
    And user click on all filters and should click on this month
    Then The user must find the events of this month
@filter
  Scenario: photographer can filter the events of this year
    
    And user click on all filters and should click on this year
    Then The user must find the events of this year
@filter
  Scenario: photographer can filter the events according to their names, locations, status and dates simultaneously
    
    And user click on all filters and fill the name of event as "koura" , the location of event as "tunis", and the status of event as "In progress" and the date of event today
    Then The user must find the events whoose name of event as "koura" , location of event as "tunis", status of event as "In progress" and the date of event today

