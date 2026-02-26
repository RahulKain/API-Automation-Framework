Feature: User Management API
  As a tester
  I want to test the User Management API
  So that I can validate user CRUD operations

  Background:
    Given the user is on the User API page

  @smoke @regression @user
  Scenario: USR_001 - Retrieve all users
    When the user retrieves all users
    Then the response status code should be 200
    And the response should contain a list of users
    And the response should not be empty

  @regression @user
  Scenario: USR_002 - Retrieve user by ID
    When the user retrieves user with id 1
    Then the response status code should be 200
    And the response should contain user with id 1
    And the response should contain user with name
    And the response should contain user with email

  @regression @user
  Scenario: USR_003 - Create new user
    When the user creates a new user with details:
      | name  | John Doe           |
      | email | john.doe@test.com  |
      | age   | 30                 |
    Then the response status code should be 201
    And the response should contain user id
    And the response should contain user with name "John Doe"
    And the response should contain user with email "john.doe@test.com"

  @regression @user
  Scenario: USR_004 - Update user details
    When the user creates a new user with details:
      | name  | Jane Doe           |
      | email | jane.doe@test.com  |
      | age   | 28                 |
    And the user updates the created user with details:
      | name  | Jane Updated       |
      | email | jane.updated@test.com |
      | age   | 29                 |
    Then the response status code should be 200
    And the response should contain user with name "Jane Updated"
    And the response should contain user with email "jane.updated@test.com"

  @regression @user
  Scenario: USR_005 - Delete user
    When the user creates a new user with details:
      | name  | John To Delete     |
      | email | delete.test@test.com |
      | age   | 35                 |
    And the user deletes the created user
    Then the response status code should be 204

  @regression @user
  Scenario: USR_006 - Search users by name
    When the user searches for users by name "John"
    Then the response status code should be 200
    And the response should contain a list of users
    And all users in response should have "John" in name

  @api-validation @user
  Scenario: USR_007 - Validate email format
    When the user creates a new user with invalid email:
      | name  | Invalid User       |
      | email | invalid-email      |
      | age   | 25                 |
    Then the response status code should be 400
    And the response should contain error message
    And the error should mention "email"

  @regression @user
  Scenario: USR_008 - Verify user response time
    When the user retrieves all users
    Then the response status code should be 200
    And the response time should be less than 5000 milliseconds

  @regression @user
  Scenario: USR_009 - Create user with all details
    When the user creates a new user with all details:
      | name    | John Complete        |
      | email   | john.complete@test.com |
      | age     | 32                 |
      | phone   | 1234567890         |
      | address | 123 Main St        |
    Then the response status code should be 201
    And the response should contain user id
    And the response should contain user with phone "1234567890"
    And the response should contain user with address "123 Main St"

  @smoke @regression @user
  Scenario: USR_010 - Verify user response is JSON
    When the user retrieves all users
    Then the response status code should be 200
    And the response should be in JSON format
    And the response should not be empty
