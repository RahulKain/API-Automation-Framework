package com.demoapi.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * APIConfig class to manage API configuration
 */
public class APIConfig {

    private static final Properties properties = new Properties();
    private static final String CONFIG_FILE = "application.properties";

    static {
        loadProperties();
    }

    /**
     * Load properties from application.properties file
     */
    private static void loadProperties() {
        try (InputStream input = APIConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found: " + CONFIG_FILE);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties: " + e.getMessage(), e);
        }
    }

    /**
     * Get base URL for API
     */
    public static String getBaseUrl() {
        String baseUrl = properties.getProperty("api.base.url");
        String basePath = properties.getProperty("api.base.path");
        return baseUrl + basePath;
    }

    /**
     * Get request timeout in milliseconds
     */
    public static int getRequestTimeout() {
        String timeout = properties.getProperty("api.request.timeout", "30000");
        return Integer.parseInt(timeout);
    }

    /**
     * Get connection timeout in milliseconds
     */
    public static int getConnectionTimeout() {
        String timeout = properties.getProperty("api.connection.timeout", "10000");
        return Integer.parseInt(timeout);
    }

    /**
     * Check if request logging is enabled
     */
    public static boolean isRequestLoggingEnabled() {
        String logging = properties.getProperty("api.request.logging", "true");
        return Boolean.parseBoolean(logging);
    }

    /**
     * Check if response logging is enabled
     */
    public static boolean isResponseLoggingEnabled() {
        String logging = properties.getProperty("api.response.logging", "true");
        return Boolean.parseBoolean(logging);
    }

    /**
     * Get environment
     */
    public static String getEnvironment() {
        return properties.getProperty("environment", "local");
    }

    /**
     * Get max retries
     */
    public static int getMaxRetries() {
        String retries = properties.getProperty("max.retries", "3");
        return Integer.parseInt(retries);
    }

    /**
     * Get test data path
     */
    public static String getTestDataPath() {
        return properties.getProperty("test.data.path", "src/test/resources/testdata/");
    }

    /**
     * Get property value by key
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Get property value by key with default
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
