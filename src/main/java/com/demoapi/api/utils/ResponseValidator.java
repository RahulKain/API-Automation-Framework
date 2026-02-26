package com.demoapi.api.utils;

import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ResponseValidator class for common response validations
 */
public class ResponseValidator {

    /**
     * Validate response status code
     */
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        LoggerUtil.info("Validating status code: " + expectedStatusCode);
        assertThat("Status code mismatch", response.getStatusCode(), Matchers.equalTo(expectedStatusCode));
    }

    /**
     * Validate response contains key
     */
    public static void validateResponseContainsKey(Response response, String key) {
        LoggerUtil.info("Validating response contains key: " + key);
        assertThat("Response does not contain key: " + key, response.jsonPath().getMap("$"), Matchers.hasKey(key));
    }

    /**
     * Validate response body is not empty
     */
    public static void validateResponseBodyNotEmpty(Response response) {
        LoggerUtil.info("Validating response body is not empty");
        String body = response.getBody().asString();
        assertThat("Response body is empty", body, Matchers.not(Matchers.isEmptyString()));
    }

    /**
     * Validate response path value
     */
    public static void validateResponsePath(Response response, String jsonPath, Object expectedValue) {
        LoggerUtil.info("Validating response jsonPath: " + jsonPath + " with value: " + expectedValue);
        Object actualValue = response.jsonPath().get(jsonPath);
        assertThat("Response path value mismatch", actualValue, Matchers.equalTo(expectedValue));
    }

    /**
     * Validate response time
     */
    public static void validateResponseTime(Response response, long maxTimeInMs) {
        long responseTime = response.getTime();
        LoggerUtil.info("Validating response time. Expected max: " + maxTimeInMs + "ms, Actual: " + responseTime + "ms");
        assertThat("Response time exceeded threshold", responseTime, Matchers.lessThan(maxTimeInMs));
    }

    /**
     * Validate response is JSON
     */
    public static void validateResponseIsJson(Response response) {
        LoggerUtil.info("Validating response is JSON");
        String contentType = response.getContentType();
        assertThat("Response is not JSON format", contentType, Matchers.containsString("application/json"));
    }

    /**
     * Validate response array size
     */
    public static void validateArraySize(Response response, String jsonPath, int expectedSize) {
        LoggerUtil.info("Validating array size at path: " + jsonPath + " with size: " + expectedSize);
        Object array = response.jsonPath().get(jsonPath);
        if (array instanceof java.util.List) {
            java.util.List<?> list = (java.util.List<?>) array;
            assertThat("Array size mismatch", list.size(), Matchers.equalTo(expectedSize));
        } else {
            throw new AssertionError("Path does not contain an array: " + jsonPath);
        }
    }

    /**
     * Validate response header exists
     */
    public static void validateHeaderExists(Response response, String headerName) {
        LoggerUtil.info("Validating header exists: " + headerName);
        String headerValue = response.getHeader(headerName);
        assertThat("Header not found: " + headerName, headerValue, Matchers.notNullValue());
    }

    /**
     * Validate response header value
     */
    public static void validateHeaderValue(Response response, String headerName, String expectedValue) {
        LoggerUtil.info("Validating header value: " + headerName + " = " + expectedValue);
        String headerValue = response.getHeader(headerName);
        assertThat("Header value mismatch", headerValue, Matchers.equalTo(expectedValue));
    }
}
