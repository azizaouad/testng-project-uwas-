  Feature: test the login functionality
  @login
    Scenario: user to login with valid email and valid password
    
  
      Then user to login with valid email and valid password
    @login
    Scenario Outline: login with invalid credentials



      Then login with invalid credentials "<email>" and "<password>"
Examples:
    | email      | password   |
    | p@gmail.com| Admin123!  |


