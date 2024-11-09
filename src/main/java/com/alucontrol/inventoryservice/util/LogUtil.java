package com.alucontrol.inventoryservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

// This is a utility class that provides a way to create and manage loggers.
// The purpose of this is to create Loggers and set them up with different levels.
public class LogUtil {

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    private static String getCorrelationId() {
        return MDC.get("correlationId");  // Assuming correlation ID is set in MDC
    }

    private static void info(String message) {
        String correlationId = getCorrelationId();
        logger.info("[Correlation ID: {}] {}", correlationId, message);
    }

    private static void error(String message) {
        String correlationId = getCorrelationId();
        logger.error("[Correlation ID: {}] {}", correlationId, message);
    }

}
