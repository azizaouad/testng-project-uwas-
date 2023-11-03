Feature: test add event functionality
 @addevent
  Scenario: photographer can login and add event

    And photographer should fill the title of event
    And photographer should fill the location of event
    And photographer should fill the date of event
    And photographer put an image for the event
    And photographer should click on the button ok
    Then the event is created
  @addevent
  Scenario: photographer can login and can't add event without filling the title of event

    And photographer should fill the location of event
    And photographer should fill the date of event
    And photographer should click on the button ok
    Then an error message appear under the title field

    @addevent
  Scenario: photographer can login and can't add event without filling the title of event and location of event

    And photographer should fill the date of event
    And photographer should click on the button ok
    Then an error message appear under the title field

    @addevent
  Scenario: photographer can login and can't add event without filling the title of event and date of event

    And photographer should fill the location of event
    And photographer should click on the button ok
    Then an error message appear under the title field


@addevent
 Scenario: photographer can login and add event without filling the location of event

   And photographer should fill the title of event
   And photographer should fill the date of event
   And photographer should click on the button ok


   Then the event is created in location of event as "Not defined"


@addevent
 Scenario: photographer can login and add event without filling the date of event

   And photographer should fill the title of event
   And photographer should fill the location of event
   And photographer put an image for the event
   And photographer should click on the button ok
   Then the event is created with the date added
@addevent
 Scenario: photographer can login and can't add event with filling a file not image type in the image field

   And photographer should fill the title of event
   And photographer should fill the location of event
   And photographer should fill the date of event
   And photographer put a file in the image field for the event
   And photographer should click on the button ok
   Then an error message appear and the event is created without image