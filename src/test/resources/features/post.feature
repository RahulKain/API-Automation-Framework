Feature: Post Management API
  As a tester
  I want to test the Post Management API
  So that I can validate post CRUD operations

  Background:
    Given the user is on the Post API page

  @smoke @regression @post
  Scenario: POST_001 - Retrieve all posts
    When the user retrieves all posts
    Then the post response status code should be 200
    And the post response should contain a list of posts
    And the post response should not be empty

  @regression @post
  Scenario: POST_002 - Retrieve posts by user ID
    When the user retrieves posts for user id 1
    Then the post response status code should be 200
    And the post response should contain a list of posts
    And all posts in response should have userId 1

  @regression @post
  Scenario: POST_003 - Create new post
    When the user creates a new post with details:
      | userId  | 1                              |
      | title   | My First Post                  |
      | content | This is the content of my post |
    Then the post response status code should be 201
    And the response should contain post id
    And the response should contain post with title "My First Post"
    And the response should contain post with content "This is the content of my post"

  @regression @post
  Scenario: POST_004 - Update post
    When the user creates a new post with details:
      | userId  | 1                 |
      | title   | Original Title    |
      | content | Original Content  |
    And the user updates the created post with details:
      | title   | Updated Title     |
      | content | Updated Content   |
    Then the post response status code should be 200
    And the response should contain post with title "Updated Title"
    And the response should contain post with content "Updated Content"

  @regression @post
  Scenario: POST_005 - Delete post
    When the user creates a new post with details:
      | userId  | 1             |
      | title   | Post to Delete |
      | content | Delete me     |
    And the user deletes the created post
    Then the post response status code should be 204

  @regression @post
  Scenario: POST_006 - Get post comments
    When the user retrieves comments for post id 1
    Then the post response status code should be 200
    And the post response should be in JSON format
    And the post response should not be empty
