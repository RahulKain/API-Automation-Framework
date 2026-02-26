package com.demoapi.api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.demoapi.api.models.User;
import com.demoapi.api.utils.LoggerUtil;

/**
 * UserEndpoint class for handling User API endpoints
 */
public class UserEndpoint extends BaseEndpoint {

    private static final String USERS_ENDPOINT = "/users";
    private static final String USERS_BY_ID_ENDPOINT = "/users/{userId}";
    private static final String USERS_SEARCH_ENDPOINT = "/users/search";

    /**
     * Get all users
     */
    public Response getAllUsers() {
        LoggerUtil.info("Fetching all users from endpoint: " + USERS_ENDPOINT);
        resetRequestSpec();
        return getRequestSpec()
                .when()
                .get(USERS_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * Get user by ID
     */
    public Response getUserById(Integer userId) {
        LoggerUtil.info("Fetching user by ID: " + userId);
        resetRequestSpec();
        addPathParameter("userId", userId);
        return getRequestSpec()
                .when()
                .get(USERS_BY_ID_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * Create a new user
     */
    public Response createUser(User user) {
        LoggerUtil.info("Creating new user: " + user.toString());
        resetRequestSpec();
        return getRequestSpec()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(USERS_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * Update existing user
     */
    public Response updateUser(Integer userId, User user) {
        LoggerUtil.info("Updating user with ID: " + userId);
        resetRequestSpec();
        addPathParameter("userId", userId);
        return getRequestSpec()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .put(USERS_BY_ID_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * Delete user by ID
     */
    public Response deleteUser(Integer userId) {
        LoggerUtil.info("Deleting user with ID: " + userId);
        resetRequestSpec();
        addPathParameter("userId", userId);
        return getRequestSpec()
                .when()
                .delete(USERS_BY_ID_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * Search users by name
     */
    public Response searchUserByName(String name) {
        LoggerUtil.info("Searching users by name: " + name);
        resetRequestSpec();
        addQueryParameter("name", name);
        return getRequestSpec()
                .when()
                .get(USERS_SEARCH_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * Get users with pagination
     */
    public Response getUsersWithPagination(int page, int pageSize) {
        LoggerUtil.info("Fetching users with pagination - Page: " + page + ", PageSize: " + pageSize);
        resetRequestSpec();
        addQueryParameter("page", String.valueOf(page));
        addQueryParameter("pageSize", String.valueOf(pageSize));
        return getRequestSpec()
                .when()
                .get(USERS_ENDPOINT)
                .then()
                .extract()
                .response();
    }
}
