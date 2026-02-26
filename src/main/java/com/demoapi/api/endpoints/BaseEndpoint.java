package com.demoapi.api.endpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import com.demoapi.api.utils.APIConfig;
import com.demoapi.api.utils.LoggerUtil;

/**
 * BaseEndpoint class containing common API endpoint functionality
 */
public class BaseEndpoint {

    protected RequestSpecification requestSpec;

    public BaseEndpoint() {
        initializeRequestSpec();
    }

    /**
     * Initialize RestAssured request specification with base configuration
     */
    protected void initializeRequestSpec() {
        // Set base URI
        RestAssured.baseURI = APIConfig.getBaseUrl();
        
        // Create fresh request specification
        requestSpec = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        LoggerUtil.info("RequestSpec initialized for: " + RestAssured.baseURI);
    }

    /**
     * Get request specification
     */
    public RequestSpecification getRequestSpec() {
        return requestSpec;
    }

    /**
     * Set timeout in request specification
     */
    public void setTimeout(int timeoutInMs) {
        LoggerUtil.info("Timeout set to: " + timeoutInMs + "ms");
    }

    /**
     * Add header to request
     */
    public void addHeader(String headerName, String headerValue) {
        requestSpec = requestSpec.header(headerName, headerValue);
        LoggerUtil.info("Added header: " + headerName + " = " + headerValue);
    }

    /**
     * Add authorization header
     */
    public void addAuthorizationHeader(String token) {
        requestSpec = requestSpec.header("Authorization", "Bearer " + token);
        LoggerUtil.info("Added Authorization header with token");
    }

    /**
     * Add query parameter
     */
    public void addQueryParameter(String paramName, String paramValue) {
        requestSpec = requestSpec.queryParam(paramName, paramValue);
        LoggerUtil.info("Added query parameter: " + paramName + " = " + paramValue);
    }

    /**
     * Add path parameter
     */
    public void addPathParameter(String paramName, Object paramValue) {
        requestSpec = requestSpec.pathParam(paramName, paramValue);
        LoggerUtil.info("Added path parameter: " + paramName + " = " + paramValue);
    }

    /**
     * Reset request specification to default state
     */
    public void resetRequestSpec() {
        initializeRequestSpec();
        LoggerUtil.info("Request specification reset to default state");
    }
}
