package com.demoapi.api.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * LoggerUtil class for centralized logging
 */
public class LoggerUtil {

    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    /**
     * Get logger for a specific class
     */
    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    /**
     * Log info message
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Log debug message
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Log warning message
     */
    public static void warn(String message) {
        logger.warn(message);
    }

    /**
     * Log error message
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Log error message with exception
     */
    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    /**
     * Log API request details
     */
    public static void logRequest(String method, String url, String headers, String body) {
        logger.info("======== API REQUEST ========");
        logger.info("Method: " + method);
        logger.info("URL: " + url);
        if (headers != null && !headers.isEmpty()) {
            logger.info("Headers: " + headers);
        }
        if (body != null && !body.isEmpty()) {
            logger.info("Body: " + body);
        }
        logger.info("=============================\n");
    }

    /**
     * Log API response details
     */
    public static void logResponse(int statusCode, String contentType, String body) {
        logger.info("======== API RESPONSE ========");
        logger.info("Status Code: " + statusCode);
        logger.info("Content-Type: " + contentType);
        if (body != null && !body.isEmpty()) {
            logger.info("Body: " + body);
        }
        logger.info("==============================\n");
    }
}
