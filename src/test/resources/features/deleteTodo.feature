Feature: Delete Todo
  The user wants to delete a task

  Scenario: Delete task - Success
    Given the user is on the home page
    And the user enters a task
    And press enter
    When the user delete a selected task
    Then the task is not visible on the list
