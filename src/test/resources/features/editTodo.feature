Feature: Edit Todo
  The user wants to edit a task

  Scenario: Edit task - Success
    Given the user is on the home page
    And the user enters a task
    And press enter
    When the user select a task to edit
    And press enter
    Then a line with the edited task is created

