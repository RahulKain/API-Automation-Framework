package com.demoapi.api.endpoints;

import com.demoapi.api.models.Post;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.demoapi.api.utils.LoggerUtil;

/**
 * PostEndpoint class for Post Management API endpoints
 */
public class PostEndpoint extends BaseEndpoint {

    private static final String POSTS_ENDPOINT = "/posts";
    private static final String POSTS_BY_ID_ENDPOINT = "/posts/{postId}";
    private static final String POSTS_COMMENTS_ENDPOINT = "/posts/{postId}/comments";

    /**
     * GET /api/v1/posts - Get all posts
     */
    public Response getAllPosts() {
        LoggerUtil.info("Fetching all posts from endpoint: " + POSTS_ENDPOINT);
        resetRequestSpec();
        return getRequestSpec()
                .when()
                .get(POSTS_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * GET /api/v1/posts?userId={userId} - Get posts by user ID
     */
    public Response getPostsByUserId(int userId) {
        LoggerUtil.info("Getting posts for userId: " + userId);
        resetRequestSpec();
        addQueryParameter("userId", String.valueOf(userId));
        return getRequestSpec()
                .when()
                .get(POSTS_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * POST /api/v1/posts - Create new post
     */
    public Response createPost(Post post) {
        LoggerUtil.info("Creating post: " + post.getTitle());
        resetRequestSpec();
        return getRequestSpec()
                .contentType(ContentType.JSON)
                .body(post)
                .when()
                .post(POSTS_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * PUT /api/v1/posts/{postId} - Update post
     */
    public Response updatePost(int postId, Post post) {
        LoggerUtil.info("Updating post ID: " + postId);
        resetRequestSpec();
        addPathParameter("postId", postId);
        return getRequestSpec()
                .contentType(ContentType.JSON)
                .body(post)
                .when()
                .put(POSTS_BY_ID_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * DELETE /api/v1/posts/{postId} - Delete post
     */
    public Response deletePost(int postId) {
        LoggerUtil.info("Deleting post ID: " + postId);
        resetRequestSpec();
        addPathParameter("postId", postId);
        return getRequestSpec()
                .when()
                .delete(POSTS_BY_ID_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    /**
     * GET /api/v1/posts/{postId}/comments - Get post comments
     */
    public Response getPostComments(int postId) {
        LoggerUtil.info("Getting comments for post ID: " + postId);
        resetRequestSpec();
        addPathParameter("postId", postId);
        return getRequestSpec()
                .when()
                .get(POSTS_COMMENTS_ENDPOINT)
                .then()
                .extract()
                .response();
    }
}
