Feature: test forget password functionality
  @FP
  Scenario: user forget his password and receive an email to reset it
    When user write email as "uwas02@qa.team"
    And user confirm the email
    And user visit the mail and click on the link
    And user write the new password 
    And user write confirm_password 
    Then the password is changed user can login with new password as "Passwords" and email as "uwas02@qa.team"




@FP
  Scenario: user try to reset his password without having an account
    When user write email as "no_user@gmail.com"
    And user confirm the email
    Then an error message is displayed
@FP
  Scenario Outline: user forget his password and receive an email to reset it but doesn't accept the password conditions
    When user write email as "uwas02@qa.team"
    And user confirm the email
    And user visit the mail and click on the link
    And user fill password as "<password>"
    And user fill confirm_password as "<confirm_password>"
    Then the password is not changed and an error message appear

      Examples:
      | password    | confirm_password  |
      | admin6542!  | admin6542!        |
      | Edmin65412  | Edmi65412         |
      | ADMIN6542!  | ADMIN6542!        |
      | ADMINadmi!  | ADMINadmi!        |
      | Admin1!     | Admin1!           |
      | Admin1!     | Admin123!         |
      | Admin123!   | Admin123456!      |
      |             | Admin123!         |
      | Admin123!   |                   |
      |             |                   |
