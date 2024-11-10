package com.alucontrol.inventoryservice.tracking;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;


// Focuses on the control of request logs and request ID.
// This interceptor adds the context and request ID to the MDC,
// allowing you to trace the request along the logs generated by any class during processing.
public class RequestLoggingInterceptor implements HandlerInterceptor {

    private static final String REQUEST_ID = "requestId";
    private static final String USER_ID = "userId";

    // Run before the controller is invoked.
    // Ideal for logging request information such as URL, HTTP method, headers, etc.
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // Generates a new UUID for each request and adds to the MDC
        String requestID = UUID.randomUUID().toString();
        MDC.put(REQUEST_ID, requestID);

        // Extract user ID from request (adjust based on your authentication mechanism)
        String userId = (String) MDC.get(USER_ID);
        MDC.put(USER_ID, userId);

        // Adds the requestId in the response header for tracking
        response.setHeader("X-Request-ID", requestID);

        return true;
    }


    // Executed after completion of the request, including handling exceptions.
    // Useful for recording additional information, such as response status and runtime.
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        if (ex != null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        // Clears the MDC after completion of the request
        MDC.remove(REQUEST_ID);
        MDC.remove(USER_ID);
    }
}
