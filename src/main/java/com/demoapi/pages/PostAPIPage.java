package com.demoapi.pages;

import com.demoapi.api.endpoints.PostEndpoint;
import com.demoapi.api.models.Post;
import com.demoapi.api.utils.LoggerUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PostAPIPage - Page Object Model for Post Management API
 */
public class PostAPIPage extends BasePage {

    public PostEndpoint postEndpoint;

    public PostAPIPage() {
        this.postEndpoint = new PostEndpoint();
        LoggerUtil.info("PostAPIPage initialized");
    }

    /**
     * Get all posts
     */
    public void getAllPosts() {
        LoggerUtil.info("Fetching all posts");
        setLastResponse(postEndpoint.getAllPosts());
    }

    /**
     * Get posts by user ID
     */
    public void getPostsByUserId(int userId) {
        LoggerUtil.info("Fetching posts for user ID: " + userId);
        setLastResponse(postEndpoint.getPostsByUserId(userId));
    }

    /**
     * Create a new post
     */
    public void createNewPost(int userId, String title, String content) {
        LoggerUtil.info("Creating new post - Title: " + title);
        Post post = new Post(userId, title, content);
        setLastResponse(postEndpoint.createPost(post));
    }

    /**
     * Create a new post with tags
     */
    public void createNewPostWithTags(int userId, String title, String content, List<String> tags) {
        LoggerUtil.info("Creating new post with tags - Title: " + title);
        Post post = new Post(userId, title, content, tags);
        setLastResponse(postEndpoint.createPost(post));
    }

    /**
     * Update existing post
     */
    public void updatePost(int postId, String title, String content) {
        LoggerUtil.info("Updating post ID: " + postId);
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        setLastResponse(postEndpoint.updatePost(postId, post));
    }

    /**
     * Delete post
     */
    public void deletePost(int postId) {
        LoggerUtil.info("Deleting post ID: " + postId);
        setLastResponse(postEndpoint.deletePost(postId));
    }

    /**
     * Delete the last created post
     */
    public void deleteCreatedPost() {
        int postId = getCreatedPostId();
        deletePost(postId);
    }

    /**
     * Get post comments
     */
    public void getPostComments(int postId) {
        LoggerUtil.info("Fetching comments for post ID: " + postId);
        setLastResponse(postEndpoint.getPostComments(postId));
    }

    /**
     * Get created post ID from response
     */
    public int getCreatedPostId() {
        Post post = getLastResponse().as(Post.class);
        LoggerUtil.info("Created Post ID: " + post.getId());
        return post.getId();
    }

    /**
     * Get all posts from response as list
     */
    public List<Post> getAllPostsFromResponse() {
        Post[] posts = getLastResponse().as(Post[].class);
        LoggerUtil.info("Posts retrieved: " + posts.length);
        return new ArrayList<>(Arrays.asList(posts));
    }

    /**
     * Verify post title
     */
    public void verifyPostTitle(String expectedTitle) {
        Post post = getLastResponse().as(Post.class);
        assert post.getTitle().equals(expectedTitle) : 
            "Post title mismatch. Expected: " + expectedTitle + ", Got: " + post.getTitle();
        LoggerUtil.info("Post title verified: " + expectedTitle);
    }

    /**
     * Verify post content
     */
    public void verifyPostContent(String expectedContent) {
        Post post = getLastResponse().as(Post.class);
        assert post.getContent().equals(expectedContent) : 
            "Post content mismatch. Expected: " + expectedContent + ", Got: " + post.getContent();
        LoggerUtil.info("Post content verified: " + expectedContent);
    }

    /**
     * Verify post user ID
     */
    public void verifyPostUserId(int expectedUserId) {
        Post post = getLastResponse().as(Post.class);
        assert post.getUserId() == expectedUserId : 
            "Post userId mismatch. Expected: " + expectedUserId + ", Got: " + post.getUserId();
        LoggerUtil.info("Post userId verified: " + expectedUserId);
    }

    /**
     * Verify post tags
     */
    public void verifyPostTags(List<String> expectedTags) {
        Post post = getLastResponse().as(Post.class);
        assert post.getTags() != null && post.getTags().equals(expectedTags) : 
            "Post tags mismatch. Expected: " + expectedTags + ", Got: " + post.getTags();
        LoggerUtil.info("Post tags verified: " + expectedTags);
    }

    /**
     * Verify all posts have specific userId
     */
    public void verifyAllPostsHaveUserId(int userId) {
        List<Post> posts = getAllPostsFromResponse();
        for (Post post : posts) {
            assert post.getUserId() == userId : 
                "Post userId mismatch. Expected: " + userId + ", Got: " + post.getUserId();
        }
        LoggerUtil.info("All posts verified with userId: " + userId);
    }

    /**
     * Verify post contains required fields
     */
    public void verifyPostHasRequiredFields() {
        Post post = getLastResponse().as(Post.class);
        assert post.getId() > 0 : "Post ID should be present";
        assert post.getTitle() != null : "Post title should be present";
        assert post.getContent() != null : "Post content should be present";
        assert post.getUserId() > 0 : "Post userId should be present";
        assert post.getCreatedAt() != null : "Post createdAt should be present";
        LoggerUtil.info("Post has all required fields");
    }
}
