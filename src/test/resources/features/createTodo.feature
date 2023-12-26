@Ignore
Feature: Create Todo
  The user wants to create a task

  Scenario: Create task - Success
    Given the user is on the home page
    When the user enters a task
    And press enter
    Then a new line with the new task is created