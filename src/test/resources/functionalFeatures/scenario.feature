Feature:  : sceanrio feature 

  
  
  @scenario
  Scenario: photographer should login, then he should create an event and logout

    And photographer click on the button of add event
    And photographer fill the title of event
    And photographer fill the location of event
    And photographer fill the date of event
    And photographer put an image
    And photographer should click on the ok button
    And this event is created
    Then photographer should logout

  @scenario
  Scenario: photographer should login, then he should upload some photos and logout

    And user upload some photos
    And the photos are uploaded
    Then photographer should logout
