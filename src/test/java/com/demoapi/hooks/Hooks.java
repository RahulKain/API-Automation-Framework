package com.demoapi.hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.restassured.RestAssured;
import com.demoapi.api.utils.APIConfig;
import com.demoapi.api.utils.LoggerUtil;

/**
 * Hooks class for Cucumber setup and teardown
 */
public class Hooks {

    @Before
    public void beforeScenario() {
        LoggerUtil.info("========== STARTING TEST SCENARIO ==========");
        initializeAPI();
    }

    @After
    public void afterScenario() {
        LoggerUtil.info("========== COMPLETED TEST SCENARIO ==========\n");
        cleanupAPI();
    }

    /**
     * Initialize API configuration
     */
    private void initializeAPI() {
        try {
            // Set base URI
            RestAssured.baseURI = APIConfig.getBaseUrl();
            LoggerUtil.info("Base URI set to: " + RestAssured.baseURI);

            LoggerUtil.info("API configuration initialized successfully");
            LoggerUtil.info("Environment: " + APIConfig.getEnvironment());
            LoggerUtil.info("Request Logging: " + APIConfig.isRequestLoggingEnabled());
            LoggerUtil.info("Response Logging: " + APIConfig.isResponseLoggingEnabled());

        } catch (Exception e) {
            LoggerUtil.error("Error initializing API configuration", e);
            throw new RuntimeException("Failed to initialize API configuration", e);
        }
    }

    /**
     * Cleanup API resources
     */
    private void cleanupAPI() {
        try {
            LoggerUtil.info("Cleaning up API resources");
            // Reset RestAssured to default state
            RestAssured.reset();
            LoggerUtil.info("API cleanup completed successfully");
        } catch (Exception e) {
            LoggerUtil.error("Error during API cleanup", e);
        }
    }
}
