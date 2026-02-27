package com.demoapi.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import com.demoapi.pages.UserAPIPage;
import com.demoapi.api.utils.LoggerUtil;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * UserSteps class - Step definitions for User API feature file
 */
public class UserSteps {

    private UserAPIPage userPage;
    private Integer lastCreatedUserId;

    @Given("the user is on the User API page")
    public void navigateToUserAPI() {
        LoggerUtil.info("Navigating to User API page");
        userPage = new UserAPIPage();
    }

    @When("the user retrieves all users")
    public void getAllUsers() {
        LoggerUtil.info("Retrieving all users");
        userPage.getAllUsers();
    }

    @When("the user retrieves user with id {int}")
    public void getUserById(Integer userId) {
        LoggerUtil.info("Retrieving user with ID: " + userId);
        userPage.getUserById(userId);
    }

    @When("the user creates a new user with details:")
    public void createUserWithDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String name = data.get("name");
        String email = data.get("email");
        Integer age = Integer.parseInt(data.get("age"));

        LoggerUtil.info("Creating user - Name: " + name + ", Email: " + email + ", Age: " + age);
        userPage.createUser(name, email, age);
        
        // Store the created user ID for later operations
        if (userPage.getResponseStatusCode() == 201) {
            lastCreatedUserId = userPage.getCreatedUserId();
            LoggerUtil.info("User created with ID: " + lastCreatedUserId);
        }
    }

    @When("the user creates a new user with all details:")
    public void createUserWithAllDetails(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String name = data.get("name");
        String email = data.get("email");
        Integer age = Integer.parseInt(data.get("age"));
        String phone = data.get("phone");
        String address = data.get("address");

        LoggerUtil.info("Creating user with all details");
        userPage.createUserWithAllDetails(name, email, age, phone, address);
        
        // Store the created user ID for later operations
        if (userPage.getResponseStatusCode() == 201) {
            lastCreatedUserId = userPage.getCreatedUserId();
            LoggerUtil.info("User created with ID: " + lastCreatedUserId);
        }
    }

    @When("the user creates a new user with invalid email:")
    public void createUserWithInvalidEmail(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String name = data.get("name");
        String email = data.get("email");
        Integer age = Integer.parseInt(data.get("age"));

        LoggerUtil.info("Creating user with invalid email");
        userPage.createUser(name, email, age);
    }

    @When("the user updates the created user with details:")
    public void updateCreatedUserWithDetails(DataTable dataTable) {
        if (lastCreatedUserId == null) {
            throw new RuntimeException("No user was created in previous step");
        }

        Map<String, String> data = dataTable.asMap(String.class, String.class);
        String name = data.get("name");
        String email = data.get("email");
        Integer age = Integer.parseInt(data.get("age"));

        LoggerUtil.info("Updating user with ID: " + lastCreatedUserId);
        userPage.updateUser(lastCreatedUserId, name, email, age);
    }

    @When("the user deletes the created user")
    public void deleteCreatedUser() {
        if (lastCreatedUserId == null) {
            throw new RuntimeException("No user was created in previous step");
        }

        LoggerUtil.info("Deleting user with ID: " + lastCreatedUserId);
        userPage.deleteUser(lastCreatedUserId);
    }

    @When("the user searches for users by name {string}")
    public void searchUserByName(String name) {
        LoggerUtil.info("Searching for users by name: " + name);
        userPage.searchUserByName(name);
    }

    @Then("the response should contain user with id {int}")
    public void verifyResponseContainsUserId(Integer userId) {
        LoggerUtil.info("Verifying response contains user with ID: " + userId);
        userPage.verifyResponsePath("id", userId);
    }

    @Then("the response should contain user with name")
    public void verifyResponseContainsUserName() {
        LoggerUtil.info("Verifying response contains user name");
        userPage.verifyResponseContainsKey("name");
    }

    @Then("the response should contain user with email")
    public void verifyResponseContainsUserEmail() {
        LoggerUtil.info("Verifying response contains user email");
        userPage.verifyResponseContainsKey("email");
    }

    @Then("the response should contain user id")
    public void verifyResponseContainsUserId() {
        LoggerUtil.info("Verifying response contains user ID");
        userPage.verifyUserCreatedWithId();
    }

    @Then("the response should contain user with name {string}")
    public void verifyResponseUserName(String expectedName) {
        LoggerUtil.info("Verifying user name: " + expectedName);
        userPage.verifyResponsePath("name", expectedName);
    }

    @Then("the response should contain user with email {string}")
    public void verifyResponseUserEmail(String expectedEmail) {
        LoggerUtil.info("Verifying user email: " + expectedEmail);
        userPage.verifyResponsePath("email", expectedEmail);
    }

    @Then("the response should contain user with phone {string}")
    public void verifyResponseUserPhone(String expectedPhone) {
        LoggerUtil.info("Verifying user phone: " + expectedPhone);
        userPage.verifyResponsePath("phone", expectedPhone);
    }

    @Then("the response should contain user with address {string}")
    public void verifyResponseUserAddress(String expectedAddress) {
        LoggerUtil.info("Verifying user address: " + expectedAddress);
        userPage.verifyResponsePath("address", expectedAddress);
    }

    @Then("the response should contain error message")
    public void verifyResponseContainsErrorMessage() {
        LoggerUtil.info("Verifying response contains error message");
        Object errorMsg = userPage.getResponseValue("message");
        assertThat("Response should contain error message", errorMsg, notNullValue());
    }

    @Then("the error should mention {string}")
    public void verifyErrorMessageMentions(String expectedText) {
        LoggerUtil.info("Verifying error message mentions: " + expectedText);
        String errorMsg = userPage.getResponseValue("message").toString();
        assertThat("Error message should mention '" + expectedText + "'", errorMsg, containsStringIgnoringCase(expectedText));
    }

    @Then("the response time should be less than {int} milliseconds")
    public void verifyResponseTime(long maxTimeInMs) {
        LoggerUtil.info("Verifying response time is less than: " + maxTimeInMs + "ms");
        userPage.verifyResponseTime(maxTimeInMs);
    }

    @Then("all users in response should have {string} in name")
    public void verifyAllUsersHaveNameInResponse(String nameFilter) {
        LoggerUtil.info("Verifying all users contain name: " + nameFilter);
        java.util.List<com.demoapi.api.models.User> users = userPage.getAllUsersFromResponse();
        
        for (com.demoapi.api.models.User user : users) {
            assertThat("User name should contain: " + nameFilter, 
                    user.getName(), containsStringIgnoringCase(nameFilter));
        }
    }

    // Shared verification steps
    @Then("the response status code should be {int}")
    public void verifyResponseStatusCode(Integer statusCode) {
        LoggerUtil.info("Verifying response status code: " + statusCode);
        userPage.verifyStatusCode(statusCode);
    }

    @Then("the response should not be empty")
    public void verifyResponseNotEmpty() {
        LoggerUtil.info("Verifying response is not empty");
        userPage.verifyResponseNotEmpty();
    }

    @Then("the response should be in JSON format")
    public void verifyResponseIsJSON() {
        LoggerUtil.info("Verifying response is in JSON format");
        userPage.verifyResponseIsJson();
    }

    @Then("the response should contain a list of users")
    public void verifyResponseContainsListOfUsers() {
        LoggerUtil.info("Verifying response contains list of users");
        userPage.verifyResponseIsArray();
    }
}
