@Ignore
Feature: Complete Todo
  The user wants to mark a task as complete

  Scenario: Complete task - Success
    Given the user is on the home page
    When the user enters a task
    And press enter
    When the user select a task to mark as complete
    Then the selected task is marked completed
    And is listed as completed task

