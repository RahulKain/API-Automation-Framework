package com.demoapi.pages;

import io.restassured.response.Response;
import com.demoapi.api.utils.LoggerUtil;
import com.demoapi.api.utils.ResponseValidator;

/**
 * BasePage class for Page Object Model - contains common page methods
 */
public abstract class BasePage {

    protected Response lastResponse;

    /**
     * Get last response
     */
    public Response getLastResponse() {
        return lastResponse;
    }

    /**
     * Set last response
     */
    protected void setLastResponse(Response response) {
        this.lastResponse = response;
    }

    /**
     * Verify response status code
     */
    public void verifyStatusCode(int expectedStatusCode) {
        LoggerUtil.info("Verifying response status code: " + expectedStatusCode);
        ResponseValidator.validateStatusCode(getLastResponse(), expectedStatusCode);
    }

    /**
     * Verify response body is not empty
     */
    public void verifyResponseNotEmpty() {
        LoggerUtil.info("Verifying response body is not empty");
        ResponseValidator.validateResponseBodyNotEmpty(getLastResponse());
    }

    /**
     * Verify response contains key
     */
    public void verifyResponseContainsKey(String key) {
        LoggerUtil.info("Verifying response contains key: " + key);
        ResponseValidator.validateResponseContainsKey(getLastResponse(), key);
    }

    /**
     * Verify response JSON path value
     */
    public void verifyResponsePath(String jsonPath, Object expectedValue) {
        LoggerUtil.info("Verifying response path: " + jsonPath);
        ResponseValidator.validateResponsePath(getLastResponse(), jsonPath, expectedValue);
    }

    /**
     * Verify response is JSON
     */
    public void verifyResponseIsJson() {
        LoggerUtil.info("Verifying response is JSON");
        ResponseValidator.validateResponseIsJson(getLastResponse());
    }

    /**
     * Verify response time
     */
    public void verifyResponseTime(long maxTimeInMs) {
        LoggerUtil.info("Verifying response time");
        ResponseValidator.validateResponseTime(getLastResponse(), maxTimeInMs);
    }

    /**
     * Verify array size in response
     */
    public void verifyArraySize(String jsonPath, int expectedSize) {
        LoggerUtil.info("Verifying array size at path: " + jsonPath);
        ResponseValidator.validateArraySize(getLastResponse(), jsonPath, expectedSize);
    }

    /**
     * Verify response header exists
     */
    public void verifyHeaderExists(String headerName) {
        LoggerUtil.info("Verifying header exists: " + headerName);
        ResponseValidator.validateHeaderExists(getLastResponse(), headerName);
    }

    /**
     * Verify response header value
     */
    public void verifyHeaderValue(String headerName, String expectedValue) {
        LoggerUtil.info("Verifying header value: " + headerName);
        ResponseValidator.validateHeaderValue(getLastResponse(), headerName, expectedValue);
    }

    /**
     * Get response body as string
     */
    public String getResponseBody() {
        return getLastResponse().getBody().asString();
    }

    /**
     * Get response value using JSON path
     */
    public Object getResponseValue(String jsonPath) {
        return getLastResponse().jsonPath().get(jsonPath);
    }

    /**
     * Get response status code
     */
    public int getResponseStatusCode() {
        return getLastResponse().getStatusCode();
    }

    /**
     * Get response header value
     */
    public String getResponseHeader(String headerName) {
        return getLastResponse().getHeader(headerName);
    }

    /**
     * Get response content type
     */
    public String getResponseContentType() {
        return getLastResponse().getContentType();
    }

    /**
     * Get response time
     */
    public long getResponseTime() {
        return getLastResponse().getTime();
    }

    /**
     * Log response details
     */
    public void logResponseDetails() {
        LoggerUtil.info("Response Status Code: " + getResponseStatusCode());
        LoggerUtil.info("Response Time: " + getResponseTime() + "ms");
        LoggerUtil.info("Response Body: " + getResponseBody());
    }

    /**
     * Verify response is an array
     */
    public void verifyResponseIsArray() {
        LoggerUtil.info("Verifying response is an array");
        String body = getResponseBody();
        assert body.trim().startsWith("[") && body.trim().endsWith("]") : 
            "Response body is not a JSON array";
        LoggerUtil.info("Response is a valid JSON array");
    }
}
