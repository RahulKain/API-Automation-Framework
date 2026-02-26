# Test Cases Documentation

## Application Configuration
- **Base URL**: `http://localhost:8080`
- **API Version**: `v1`
- **Environment**: Local

---

## Module 1: User Management API

### Test Case 1.1: Retrieve All Users
**Test ID**: USR_001  
**Description**: Verify that the system retrieves all users successfully  
**Endpoint**: `GET /api/v1/users`  
**Headers**: 
- Content-Type: application/json
- Authorization: Bearer {token}

**Expected Response**:
- Status Code: 200
- Response Body: List of user objects
- Response Format: JSON array

**Assertions**:
```
- Response status code equals 200
- Response body is not empty
- Response contains array of users
- Each user has id, name, email, age fields
```

**Test Data**: None (GET request)

---

### Test Case 1.2: Retrieve User by ID
**Test ID**: USR_002  
**Description**: Verify that the system retrieves a specific user by ID  
**Endpoint**: `GET /api/v1/users/{userId}`  
**Path Parameters**:
- userId: 1

**Expected Response**:
- Status Code: 200
- Response contains single user object with matching ID

**Assertions**:
```
- Response status code equals 200
- Response body is not empty
- Response id matches requested userId
- Response contains name, email, age fields
- Response format is JSON object
```

**Test Data**:
```json
{
  "userId": 1
}
```

---

### Test Case 1.3: Create New User
**Test ID**: USR_003  
**Description**: Verify that a new user can be created successfully  
**Endpoint**: `POST /api/v1/users`  
**Request Body**:
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "age": 30,
  "phone": "1234567890",
  "address": "123 Main St, City"
}
```

**Expected Response**:
- Status Code: 201
- Response contains created user with newly generated ID

**Assertions**:
```
- Response status code equals 201
- Response body contains user id (auto-generated)
- Response name equals "John Doe"
- Response email equals "john.doe@example.com"
- Response age equals 30
- Response contains createdAt timestamp
```

**Test Data**:
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "age": 30,
  "phone": "1234567890",
  "address": "123 Main St, City"
}
```

---

### Test Case 1.4: Update User Details
**Test ID**: USR_004  
**Description**: Verify that user details can be updated successfully  
**Endpoint**: `PUT /api/v1/users/{userId}`  
**Path Parameters**:
- userId: 1

**Request Body**:
```json
{
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "age": 28,
  "phone": "9876543210"
}
```

**Expected Response**:
- Status Code: 200
- Response contains updated user object

**Assertions**:
```
- Response status code equals 200
- Response id equals userId
- Response name equals "Jane Doe"
- Response email equals "jane.doe@example.com"
- Response age equals 28
- Response contains updatedAt timestamp
```

**Test Data**:
```json
{
  "userId": 1,
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "age": 28,
  "phone": "9876543210"
}
```

---

### Test Case 1.5: Delete User
**Test ID**: USR_005  
**Description**: Verify that a user can be deleted successfully  
**Endpoint**: `DELETE /api/v1/users/{userId}`  
**Path Parameters**:
- userId: 1

**Expected Response**:
- Status Code: 204 (No Content) or 200 with success message

**Assertions**:
```
- Response status code equals 204 or 200
- Response body is empty (for 204)
- Deleted user cannot be retrieved (verify in USR_002)
```

**Test Data**:
```json
{
  "userId": 1
}
```

---

### Test Case 1.6: Search Users by Name
**Test ID**: USR_006  
**Description**: Verify that users can be searched by name  
**Endpoint**: `GET /api/v1/users/search?name=John`  
**Query Parameters**:
- name: John

**Expected Response**:
- Status Code: 200
- Response contains filtered list of users

**Assertions**:
```
- Response status code equals 200
- Response body is array
- All users in response have "John" in name field
- Response size matches expected count
```

**Test Data**:
```json
{
  "searchTerm": "John"
}
```

---

### Test Case 1.7: Validate Email Format
**Test ID**: USR_007  
**Description**: Verify that invalid email format is rejected  
**Endpoint**: `POST /api/v1/users`  
**Request Body**:
```json
{
  "name": "Invalid User",
  "email": "invalid-email",
  "age": 25
}
```

**Expected Response**:
- Status Code: 400
- Error message about invalid email format

**Assertions**:
```
- Response status code equals 400
- Response contains error message
- Response error field mentions "email"
- User is not created
```

**Test Data**:
```json
{
  "name": "Invalid User",
  "email": "invalid-email",
  "age": 25
}
```

---

## Module 2: Post Management API

### Test Case 2.1: Retrieve All Posts
**Test ID**: POST_001  
**Description**: Verify that all posts can be retrieved successfully  
**Endpoint**: `GET /api/v1/posts`  
**Headers**:
- Content-Type: application/json

**Expected Response**:
- Status Code: 200
- Response contains array of post objects

**Assertions**:
```
- Response status code equals 200
- Response body is array
- Each post has id, title, content, userId, createdAt fields
- Response is not empty
```

**Test Data**: None

---

### Test Case 2.2: Retrieve Posts by User ID
**Test ID**: POST_002  
**Description**: Verify posts can be filtered by user ID  
**Endpoint**: `GET /api/v1/posts?userId={userId}`  
**Query Parameters**:
- userId: 1

**Expected Response**:
- Status Code: 200
- Response contains posts created by specified user

**Assertions**:
```
- Response status code equals 200
- All posts in response have userId = 1
- Response is array
- Response size matches expected count
```

**Test Data**:
```json
{
  "userId": 1
}
```

---

### Test Case 2.3: Create New Post
**Test ID**: POST_003  
**Description**: Verify that a new post can be created successfully  
**Endpoint**: `POST /api/v1/posts`  
**Request Body**:
```json
{
  "userId": 1,
  "title": "My First Post",
  "content": "This is the content of my first post",
  "tags": ["api", "testing"]
}
```

**Expected Response**:
- Status Code: 201
- Response contains created post with generated ID

**Assertions**:
```
- Response status code equals 201
- Response contains post id (auto-generated)
- Response title equals "My First Post"
- Response userId equals 1
- Response contains createdAt timestamp
- Response tags array contains ["api", "testing"]
```

**Test Data**:
```json
{
  "userId": 1,
  "title": "My First Post",
  "content": "This is the content of my first post",
  "tags": ["api", "testing"]
}
```

---

### Test Case 2.4: Update Post
**Test ID**: POST_004  
**Description**: Verify that a post can be updated successfully  
**Endpoint**: `PUT /api/v1/posts/{postId}`  
**Path Parameters**:
- postId: 1

**Request Body**:
```json
{
  "title": "Updated Post Title",
  "content": "This is updated content"
}
```

**Expected Response**:
- Status Code: 200
- Response contains updated post

**Assertions**:
```
- Response status code equals 200
- Response id equals postId
- Response title equals "Updated Post Title"
- Response content equals "This is updated content"
- Response contains updatedAt timestamp
- Response updatedAt is after createdAt
```

**Test Data**:
```json
{
  "postId": 1,
  "title": "Updated Post Title",
  "content": "This is updated content"
}
```

---

### Test Case 2.5: Delete Post
**Test ID**: POST_005  
**Description**: Verify that a post can be deleted successfully  
**Endpoint**: `DELETE /api/v1/posts/{postId}`  
**Path Parameters**:
- postId: 1

**Expected Response**:
- Status Code: 204 or 200

**Assertions**:
```
- Response status code equals 204 or 200
- Deleted post cannot be retrieved
- Post count decreases by 1
```

**Test Data**:
```json
{
  "postId": 1
}
```

---

### Test Case 2.6: Get Post Comments
**Test ID**: POST_006  
**Description**: Verify that comments for a post can be retrieved  
**Endpoint**: `GET /api/v1/posts/{postId}/comments`  
**Path Parameters**:
- postId: 1

**Expected Response**:
- Status Code: 200
- Response contains array of comments

**Assertions**:
```
- Response status code equals 200
- Response is array of comment objects
- Each comment has id, postId, userId, content, createdAt
- All comments have postId = 1
```

**Test Data**:
```json
{
  "postId": 1
}
```

---

## Module 3: Authentication API

### Test Case 3.1: User Login
**Test ID**: AUTH_001  
**Description**: Verify user login functionality  
**Endpoint**: `POST /api/v1/auth/login`  
**Request Body**:
```json
{
  "email": "john.doe@example.com",
  "password": "SecurePassword123"
}
```

**Expected Response**:
- Status Code: 200
- Response contains JWT token

**Assertions**:
```
- Response status code equals 200
- Response contains accessToken
- Response contains tokenType equals "Bearer"
- Response contains expiresIn (in seconds)
- Token is not empty
- Token follows JWT format
```

**Test Data**:
```json
{
  "email": "john.doe@example.com",
  "password": "SecurePassword123"
}
```

---

### Test Case 3.2: User Registration
**Test ID**: AUTH_002  
**Description**: Verify user registration functionality  
**Endpoint**: `POST /api/v1/auth/register`  
**Request Body**:
```json
{
  "name": "New User",
  "email": "newuser@example.com",
  "password": "SecurePassword123",
  "confirmPassword": "SecurePassword123"
}
```

**Expected Response**:
- Status Code: 201
- Response contains success message and user details

**Assertions**:
```
- Response status code equals 201
- Response contains user id
- Response contains email
- Response contains message indicating registration success
- User can now login
```

**Test Data**:
```json
{
  "name": "New User",
  "email": "newuser@example.com",
  "password": "SecurePassword123",
  "confirmPassword": "SecurePassword123"
}
```

---

### Test Case 3.3: Login with Invalid Credentials
**Test ID**: AUTH_003  
**Description**: Verify login fails with invalid credentials  
**Endpoint**: `POST /api/v1/auth/login`  
**Request Body**:
```json
{
  "email": "john.doe@example.com",
  "password": "WrongPassword"
}
```

**Expected Response**:
- Status Code: 401
- Response contains error message

**Assertions**:
```
- Response status code equals 401
- Response does not contain accessToken
- Response contains error message
- Error message indicates invalid credentials
```

**Test Data**:
```json
{
  "email": "john.doe@example.com",
  "password": "WrongPassword"
}
```

---

### Test Case 3.4: Refresh Token
**Test ID**: AUTH_004  
**Description**: Verify token refresh functionality  
**Endpoint**: `POST /api/v1/auth/refresh`  
**Request Body**:
```json
{
  "refreshToken": "{refreshTokenValue}"
}
```

**Expected Response**:
- Status Code: 200
- Response contains new accessToken

**Assertions**:
```
- Response status code equals 200
- Response contains new accessToken
- New token is different from old token
- Token is valid for subsequent requests
```

**Test Data**:
```json
{
  "refreshToken": "{refreshTokenValue}"
}
```

---

### Test Case 3.5: Logout
**Test ID**: AUTH_005  
**Description**: Verify user logout functionality  
**Endpoint**: `POST /api/v1/auth/logout`  
**Headers**:
- Authorization: Bearer {token}

**Expected Response**:
- Status Code: 200
- Response contains success message

**Assertions**:
```
- Response status code equals 200
- Token becomes invalid after logout
- Subsequent requests with expired token fail
```

**Test Data**: None

---

## Module 4: Comments API

### Test Case 4.1: Get All Comments
**Test ID**: CMT_001  
**Description**: Verify that all comments can be retrieved  
**Endpoint**: `GET /api/v1/comments`  
**Expected Response**:
- Status Code: 200
- Response contains array of comments

**Assertions**:
```
- Response status code equals 200
- Response is array
- Each comment has id, postId, userId, content, createdAt
```

**Test Data**: None

---

### Test Case 4.2: Create Comment on Post
**Test ID**: CMT_002  
**Description**: Verify that a comment can be created on a post  
**Endpoint**: `POST /api/v1/posts/{postId}/comments`  
**Path Parameters**:
- postId: 1

**Request Body**:
```json
{
  "userId": 2,
  "content": "Great post! Very informative."
}
```

**Expected Response**:
- Status Code: 201
- Response contains created comment

**Assertions**:
```
- Response status code equals 201
- Response contains comment id
- Response postId equals 1
- Response userId equals 2
- Response content equals provided content
- Response contains createdAt timestamp
```

**Test Data**:
```json
{
  "postId": 1,
  "userId": 2,
  "content": "Great post! Very informative."
}
```

---

### Test Case 4.3: Update Comment
**Test ID**: CMT_003  
**Description**: Verify that a comment can be updated  
**Endpoint**: `PUT /api/v1/comments/{commentId}`  
**Path Parameters**:
- commentId: 1

**Request Body**:
```json
{
  "content": "Updated comment content"
}
```

**Expected Response**:
- Status Code: 200
- Response contains updated comment

**Assertions**:
```
- Response status code equals 200
- Response id equals commentId
- Response content equals new content
- Response contains updatedAt
```

**Test Data**:
```json
{
  "commentId": 1,
  "content": "Updated comment content"
}
```

---

### Test Case 4.4: Delete Comment
**Test ID**: CMT_004  
**Description**: Verify that a comment can be deleted  
**Endpoint**: `DELETE /api/v1/comments/{commentId}`  
**Path Parameters**:
- commentId: 1

**Expected Response**:
- Status Code: 204

**Assertions**:
```
- Response status code equals 204
- Deleted comment cannot be retrieved
```

**Test Data**:
```json
{
  "commentId": 1
}
```

---

## Module 5: Notification API

### Test Case 5.1: Get User Notifications
**Test ID**: NOTIF_001  
**Description**: Verify that user notifications can be retrieved  
**Endpoint**: `GET /api/v1/notifications`  
**Headers**:
- Authorization: Bearer {token}

**Expected Response**:
- Status Code: 200
- Response contains array of notifications

**Assertions**:
```
- Response status code equals 200
- Response is array
- Each notification has id, userId, type, message, read, createdAt
```

**Test Data**: None

---

### Test Case 5.2: Get Unread Notifications
**Test ID**: NOTIF_002  
**Description**: Verify that unread notifications can be filtered  
**Endpoint**: `GET /api/v1/notifications?read=false`  
**Query Parameters**:
- read: false

**Expected Response**:
- Status Code: 200
- Response contains only unread notifications

**Assertions**:
```
- Response status code equals 200
- All notifications have read = false
- Response array contains unread messages
```

**Test Data**:
```json
{
  "read": false
}
```

---

### Test Case 5.3: Mark Notification as Read
**Test ID**: NOTIF_003  
**Description**: Verify that notification can be marked as read  
**Endpoint**: `PUT /api/v1/notifications/{notificationId}/read`  
**Path Parameters**:
- notificationId: 1

**Expected Response**:
- Status Code: 200
- Response contains updated notification

**Assertions**:
```
- Response status code equals 200
- Response read equals true
- Response contains updatedAt timestamp
```

**Test Data**:
```json
{
  "notificationId": 1
}
```

---

### Test Case 5.4: Delete Notification
**Test ID**: NOTIF_004  
**Description**: Verify that notification can be deleted  
**Endpoint**: `DELETE /api/v1/notifications/{notificationId}`  
**Path Parameters**:
- notificationId: 1

**Expected Response**:
- Status Code: 204

**Assertions**:
```
- Response status code equals 204
- Notification is removed from user's list
```

**Test Data**:
```json
{
  "notificationId": 1
}
```

---

## Test Execution Tags

### Smoke Tests
```gherkin
@smoke
Scenarios: USR_001, POST_001, AUTH_001, CMT_001, NOTIF_001
```

### Regression Tests
```gherkin
@regression
Scenarios: All test cases
```

### API Validation Tests
```gherkin
@api-validation
Scenarios: USR_007, AUTH_003
```

### Integration Tests
```gherkin
@integration
Scenarios: Scenarios involving multiple modules
```

---

## Test Data Management

### Test Data Source: `src/test/resources/testdata/`
- `user-testdata.json`
- `post-testdata.json`
- `auth-testdata.json`
- `comment-testdata.json`
- `notification-testdata.json`

### Database Setup
- Create test database before test execution
- Seed initial test data
- Reset database after each test run

---

## Performance Baselines

| Module | Expected Response Time | Threshold |
|--------|------------------------|-----------|
| User API | < 500ms | 1000ms |
| Post API | < 600ms | 1200ms |
| Auth API | < 800ms | 1500ms |
| Comment API | < 400ms | 800ms |
| Notification API | < 300ms | 600ms |

---

## Test Execution Summary

- **Total Test Cases**: 38
- **Module Breakdown**:
  - User Management: 7 test cases
  - Post Management: 6 test cases
  - Authentication: 5 test cases
  - Comments: 4 test cases
  - Notifications: 4 test cases
  - Error Handling & Validation: 12 test cases

---

**Version**: 1.0.0  
**Last Updated**: February 26, 2026
