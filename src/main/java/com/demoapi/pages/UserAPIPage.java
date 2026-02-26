package com.demoapi.pages;

import com.demoapi.api.endpoints.UserEndpoint;
import com.demoapi.api.models.User;
import com.demoapi.api.utils.LoggerUtil;

/**
 * UserAPIPage class - Page Object for User API endpoints
 * Extends BasePage and uses UserEndpoint for API calls
 */
public class UserAPIPage extends BasePage {

    private UserEndpoint userEndpoint;

    public UserAPIPage() {
        this.userEndpoint = new UserEndpoint();
        LoggerUtil.info("UserAPIPage initialized");
    }

    /**
     * Get all users
     */
    public void getAllUsers() {
        LoggerUtil.info("Getting all users");
        setLastResponse(userEndpoint.getAllUsers());
    }

    /**
     * Get user by ID
     */
    public void getUserById(Integer userId) {
        LoggerUtil.info("Getting user by ID: " + userId);
        setLastResponse(userEndpoint.getUserById(userId));
    }

    /**
     * Create a new user
     */
    public void createUser(String name, String email, Integer age) {
        LoggerUtil.info("Creating user - Name: " + name + ", Email: " + email + ", Age: " + age);
        User user = new User(name, email, age);
        setLastResponse(userEndpoint.createUser(user));
    }

    /**
     * Create a new user with all details
     */
    public void createUserWithAllDetails(String name, String email, Integer age, String phone, String address) {
        LoggerUtil.info("Creating user with all details");
        User user = new User(name, email, age, phone, address);
        setLastResponse(userEndpoint.createUser(user));
    }

    /**
     * Update user
     */
    public void updateUser(Integer userId, String name, String email, Integer age) {
        LoggerUtil.info("Updating user - ID: " + userId);
        User user = new User(name, email, age);
        setLastResponse(userEndpoint.updateUser(userId, user));
    }

    /**
     * Update user with all details
     */
    public void updateUserWithAllDetails(Integer userId, String name, String email, Integer age, String phone) {
        LoggerUtil.info("Updating user with all details - ID: " + userId);
        User user = new User(name, email, age, phone, null);
        setLastResponse(userEndpoint.updateUser(userId, user));
    }

    /**
     * Delete user
     */
    public void deleteUser(Integer userId) {
        LoggerUtil.info("Deleting user - ID: " + userId);
        setLastResponse(userEndpoint.deleteUser(userId));
    }

    /**
     * Search users by name
     */
    public void searchUserByName(String name) {
        LoggerUtil.info("Searching users by name: " + name);
        setLastResponse(userEndpoint.searchUserByName(name));
    }

    /**
     * Get users with pagination
     */
    public void getUsersWithPagination(int page, int pageSize) {
        LoggerUtil.info("Getting users with pagination - Page: " + page + ", PageSize: " + pageSize);
        setLastResponse(userEndpoint.getUsersWithPagination(page, pageSize));
    }

    /**
     * Verify user list is not empty
     */
    public void verifyUserListNotEmpty() {
        LoggerUtil.info("Verifying user list is not empty");
        verifyResponseNotEmpty();
        verifyResponseIsJson();
    }

    /**
     * Verify user has required fields
     */
    public void verifyUserHasRequiredFields(String userPath) {
        LoggerUtil.info("Verifying user has required fields");
        verifyResponseContainsKey("id");
        verifyResponseContainsKey("name");
        verifyResponseContainsKey("email");
    }

    /**
     * Verify user details
     */
    public void verifyUserDetails(String userPath, String expectedName, String expectedEmail, Integer expectedAge) {
        LoggerUtil.info("Verifying user details");
        verifyResponsePath(userPath + ".name", expectedName);
        verifyResponsePath(userPath + ".email", expectedEmail);
        verifyResponsePath(userPath + ".age", expectedAge);
    }

    /**
     * Verify user created with id
     */
    public void verifyUserCreatedWithId() {
        LoggerUtil.info("Verifying user created with ID");
        Object userId = getResponseValue("id");
        if (userId == null || (Integer) userId <= 0) {
            throw new AssertionError("User ID is not generated or invalid");
        }
        LoggerUtil.info("User created with ID: " + userId);
    }

    /**
     * Verify user updated
     */
    public void verifyUserUpdated(Integer userId) {
        LoggerUtil.info("Verifying user updated");
        verifyResponsePath("id", userId);
    }

    /**
     * Get created user ID
     */
    public Integer getCreatedUserId() {
        Object userId = getResponseValue("id");
        if (userId instanceof Integer) {
            return (Integer) userId;
        } else if (userId instanceof Long) {
            return ((Long) userId).intValue();
        } else {
            return Integer.parseInt(userId.toString());
        }
    }

    /**
     * Get user from response by index
     */
    public User getUserFromResponse(int index) {
        LoggerUtil.info("Getting user from response at index: " + index);
        return getLastResponse().jsonPath().getObject("get(" + index + ")", User.class);
    }

    /**
     * Get all users from response
     */
    public java.util.List<User> getAllUsersFromResponse() {
        LoggerUtil.info("Getting all users from response");
        return getLastResponse().jsonPath().getList("$", User.class);
    }
}
