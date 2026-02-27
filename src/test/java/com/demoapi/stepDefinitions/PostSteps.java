package com.demoapi.stepDefinitions;

import com.demoapi.pages.PostAPIPage;
import com.demoapi.api.utils.LoggerUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import java.util.Map;

/**
 * PostSteps - Step definitions for Post Management API BDD tests
 */
public class PostSteps {

    private PostAPIPage postAPIPage;
    private Map<String, String> postData;

    @Given("the user is on the Post API page")
    public void setupPostAPIPage() {
        postAPIPage = new PostAPIPage();
        LoggerUtil.info("Post API page initialized");
    }

    @When("the user retrieves all posts")
    public void retrieveAllPosts() {
        LoggerUtil.info("========== Retrieving all posts ==========");
        postAPIPage.getAllPosts();
    }

    @When("the user retrieves posts for user id {int}")
    public void retrievePostsByUserId(int userId) {
        LoggerUtil.info("========== Retrieving posts for user ID: " + userId + " ==========");
        postAPIPage.getPostsByUserId(userId);
    }

    @When("the user creates a new post with details:")
    public void createNewPostWithDetails(DataTable dataTable) {
        LoggerUtil.info("========== Creating new post ==========");
        Map<String, String> postDetails = dataTable.asMap(String.class, String.class);
        
        int userId = Integer.parseInt(postDetails.get("userId"));
        String title = postDetails.get("title");
        String content = postDetails.get("content");
        
        LoggerUtil.info("Post Details - UserId: " + userId + ", Title: " + title + ", Content: " + content);
        postAPIPage.createNewPost(userId, title, content);
        
        this.postData = postDetails;
    }

    @When("the user updates the created post with details:")
    public void updateCreatedPostWithDetails(DataTable dataTable) {
        LoggerUtil.info("========== Updating post ==========");
        Map<String, String> updateDetails = dataTable.asMap(String.class, String.class);
        
        int postId = postAPIPage.getCreatedPostId();
        String title = updateDetails.get("title");
        String content = updateDetails.get("content");
        
        LoggerUtil.info("Updating Post ID: " + postId + " with Title: " + title + ", Content: " + content);
        postAPIPage.updatePost(postId, title, content);
    }

    @When("the user deletes the created post")
    public void deleteCreatedPost() {
        LoggerUtil.info("========== Deleting created post ==========");
        postAPIPage.deleteCreatedPost();
    }

    @When("the user retrieves comments for post id {int}")
    public void retrievePostComments(int postId) {
        LoggerUtil.info("========== Retrieving comments for post ID: " + postId + " ==========");
        postAPIPage.getPostComments(postId);
    }

    // Post-specific verification steps
    @Then("the post response status code should be {int}")
    public void verifyPostResponseStatusCode(Integer statusCode) {
        LoggerUtil.info("Verifying post response status code: " + statusCode);
        postAPIPage.verifyStatusCode(statusCode);
    }

    @Then("the post response should not be empty")
    public void verifyPostResponseNotEmpty() {
        LoggerUtil.info("Verifying post response is not empty");
        postAPIPage.verifyResponseNotEmpty();
    }

    @Then("the post response should be in JSON format")
    public void verifyPostResponseIsJSON() {
        LoggerUtil.info("Verifying post response is in JSON format");
        postAPIPage.verifyResponseIsJson();
    }

    @Then("the post response should contain a list of posts")
    public void verifyPostResponseContainsListOfPosts() {
        LoggerUtil.info("Verifying post response contains list of posts");
        postAPIPage.verifyResponseIsArray();
    }

    @Then("the post response should contain a list of comments")
    public void verifyPostResponseContainsListOfComments() {
        LoggerUtil.info("Verifying post response contains list of comments");
        postAPIPage.verifyResponseIsArray();
    }

    @Then("all posts in response should have userId {int}")
    public void verifyAllPostsHaveUserId(int userId) {
        LoggerUtil.info("Verifying all posts have userId: " + userId);
        postAPIPage.verifyAllPostsHaveUserId(userId);
    }

    @Then("the response should contain post with title {string}")
    public void verifyPostTitle(String expectedTitle) {
        LoggerUtil.info("Verifying post title: " + expectedTitle);
        postAPIPage.verifyPostTitle(expectedTitle);
    }

    @Then("the response should contain post with content {string}")
    public void verifyPostContent(String expectedContent) {
        LoggerUtil.info("Verifying post content: " + expectedContent);
        postAPIPage.verifyPostContent(expectedContent);
    }

    @Then("the response should contain post id")
    public void verifyResponseContainsPostId() {
        LoggerUtil.info("Verifying response contains post ID");
        postAPIPage.verifyPostHasRequiredFields();
    }
}
