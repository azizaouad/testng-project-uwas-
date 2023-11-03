Feature: edit event
@edit
  Scenario: photographer can change the name of any event 

    And photographer click on the three buttouns for updating
    And photographer choose edit
    And photographer should change the title of event
    And photographer click on the button of ok 

    Then title of event is updated
@edit
    Scenario: photographer can change the location of any event 


    And photographer click on the three buttouns for updating
    And photographer choose edit
    And photographer should change the location of event
    And photographer click on the button of ok 

    Then location of event is updated 
@edit
        Scenario: photographer can change the date of any event 

    And photographer click on the three buttouns for updating
    And photographer choose edit
    And photographer should change the date of event
    And photographer click on the button of ok 
   
    Then date of event is updated 

    @edit
        Scenario: photographer can change the date, the name and the location of any event 

    And photographer click on the three buttouns for updating
    And photographer choose edit
    And photographer should change the title of event
    And photographer should change the date of event
    And photographer should change the location of event
    And photographer click on the button of ok 
    Then details of event is updated
