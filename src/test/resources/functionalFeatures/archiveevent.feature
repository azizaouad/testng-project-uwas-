Feature: test archive and restore event functionality
 @archive
  Scenario: photographer can archive any event
    And photographer add event
    When photographer click on the three buttouns
    And Choose archive the event
    Then the event is archived
 @archive
  Scenario: photographer can restore event

    When photographer should go to the archive event
    And Choose restore the event
    Then the event is restored
