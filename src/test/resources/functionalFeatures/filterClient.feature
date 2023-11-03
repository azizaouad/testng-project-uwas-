Feature: Filter Client
@filterC
  Scenario: client can filter the events by the consultation status ( viewed events )

    And user click on all filters and should click on viewed events
    Then the user must find the events he has consulted
    
@filterC
  Scenario: client can filter the events by the consultation status ( not viewed events )

    And user click on all filters and should click on not viewed events
    Then the user must find the events that he has not yet consulted

@filterC
  Scenario: photographer should choose two date the first is the start date and the second is the finish date
    And user click on all filters and should choose the start date and the end date
    Then the user must find the events in the period from start date to finish date
  @filterC
  Scenario: photographer can filter by the name of event
    
    And user click on all filters and should write the name of event as "koura"
    Then The user must find the events whose name of event as "koura"
@filterC
  Scenario: photographer can filter by the location of event
    
    And user click on all filters and should write the location of event as "tunis"
    Then The user must find the events whose location of event as "tunis"
@filterC
  Scenario: photographer can filter the events of today
    
    And user click on all filters and should click on today
    Then The user must find the events of today
@filterC
  Scenario: photographer can filter the events of this week
    
    And user click on all filters and should click on this week
    Then The user must find the events of this week
@filterC
  Scenario: photographer can filter the events of this month
    
    And user click on all filters and should click on this month
    Then The user must find the events of this month
@filterC
  Scenario: photographer can filter the events of this year
    
    And user click on all filters and should click on this year
    Then The user must find the events of this year
@filterC
  Scenario: photographer can filter the events according to their names, locations, status and dates simultaneously
    
    And user click on all filters and fill the name of event as "koura" , the location of event as "tunis", and the status of event as "In progress" and the date of event today
    Then The user must find the events whoose name of event as "koura" , location of event as "tunis", status of event as "In progress" and the date of event today