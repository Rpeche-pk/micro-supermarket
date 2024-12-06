package com.peche.app.trace;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.imageio.IIOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Log4j2
public class LoggingFilter extends OncePerRequestFilter {
    private static final String SPACE = " ";
    private static final String ENCODING_UTF8 = "UTF-8";
    private static final String NEXT_LINE = "\n";
    private static final String REQUEST_LAYER = "Service Request";
    private static final String RESPONSE_LAYER = "Service Response";
    private static final String URI_LAYER = "URI           : ";
    private static final String METHOD_LAYER = "Method        : ";
    private static final String HEADERS_LAYER = "Headers       : ";
    private static final String STATUS_CODE_LAYER = "Status code   : ";
    private static final String STATUS_TEXT_LAYER = "Status text   : ";
    private static final String REQUEST_BODY_LAYER = "Request body  : ";
    private static final String RESPONSE_BODY_LAYER = "Response body : ";
    private static final String SEPARATOR = "------------------------------------------------------------------------------------------------------------------------";
    private static final String REQUEST_PREFIX = "Request: ";
    private static final String RESPONSE_PREFIX = "Response: ";
    private static final String REQUEST_ID = "request id=";
    private static final String AUTHORIZATION = "authorization";

    private final AtomicLong id = new AtomicLong(0);


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        long requestId = id.incrementAndGet();
        RequestWrapper requestWrapper = new RequestWrapper(requestId, httpServletRequest);
        ResponseWrapper responseWrapper = new ResponseWrapper(requestId, httpServletResponse);

        httpServletRequest.setCharacterEncoding(ENCODING_UTF8);
        httpServletResponse.setCharacterEncoding(ENCODING_UTF8);

        // Set start time for the request
        httpServletRequest.setAttribute("startTime", System.currentTimeMillis());

        filterChain.doFilter(requestWrapper, responseWrapper);
        if (!httpServletRequest.getRequestURI().contains("swagger")) {
            logRequest(httpServletRequest, requestWrapper);
            logResponse(httpServletRequest, responseWrapper);
        }
    }

    private void logRequest(final HttpServletRequest request, final RequestWrapper requestWrapper) {
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(REQUEST_PREFIX);
        logBuilder.append(REQUEST_ID).append(requestWrapper.getId()).append("; ").append(SPACE);
        logBuilder.append(REQUEST_LAYER).append(NEXT_LINE);
        logBuilder.append(URI_LAYER).append(request.getRequestURL()).append(NEXT_LINE);
        logBuilder.append(METHOD_LAYER).append(request.getMethod()).append(NEXT_LINE);
        logBuilder.append(HEADERS_LAYER).append(new ServletServerHttpRequest(request).getHeaders()).append(NEXT_LINE);

        byte[] requestBodyBytes = requestWrapper.toByteArray();
        if (requestBodyBytes.length > 0) { // Check if request body is not empty
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                Object requestObject = objectMapper.readValue(requestBodyBytes, Object.class);
                String requestBody = objectMapper.writeValueAsString(requestObject);
                logBuilder.append(REQUEST_BODY_LAYER).append(formatJsonString(requestBody)).append(NEXT_LINE);
            } catch (MismatchedInputException | IIOException e) {
                log.warn("Error al leer el cuerpo de la solicitud JSON: ", e);
            } catch (IOException e) {
                log.warn("Error al procesar el cuerpo de la solicitud: ", e);
            }
        } else {
            logBuilder.append(REQUEST_BODY_LAYER).append("Empty").append(NEXT_LINE);
        }

        logBuilder.append(SEPARATOR);

        log.info(logBuilder);
    }

    private void logResponse(final HttpServletRequest request, final ResponseWrapper responseWrapper) {
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append(RESPONSE_PREFIX);

        // Calculate the time taken to process the request
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis() - startTime;

        logBuilder.append(REQUEST_ID).append(responseWrapper.getId()).append("; ").append("Time Taken=").append(endTime).append(SPACE);
        logBuilder.append(RESPONSE_LAYER).append(NEXT_LINE);
        logBuilder.append(STATUS_CODE_LAYER).append(responseWrapper.getStatus()).append(SPACE).append(HttpStatus.valueOf(responseWrapper.getStatus()).getReasonPhrase()).append(NEXT_LINE);
        logBuilder.append(STATUS_TEXT_LAYER).append(HttpStatus.valueOf(responseWrapper.getStatus()).getReasonPhrase()).append(NEXT_LINE);

        StringBuilder headers = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            if (!AUTHORIZATION.equals(key)) {
                headers.append(key).append(":").append('"').append(value).append('"').append(", ");
            }
        }
        if (headers.length() > 2) {
            headers.delete(headers.length() - 2, headers.length());
        }
        logBuilder.append(HEADERS_LAYER).append("[").append(headers).append("]").append(NEXT_LINE);

        byte[] responseBodyBytes = responseWrapper.toByteArray();
        String responseBody = new String(responseBodyBytes, StandardCharsets.UTF_8);

        if (Objects.isNull(responseBody) || responseBody.isBlank()) {
            responseBody = "Empty response body";
        } else if (isJsonString(responseBody)) {
            responseBody = formatJsonString(responseBody);
        }

        logBuilder.append(RESPONSE_BODY_LAYER).append(responseBody).append(NEXT_LINE);
        logBuilder.append(SEPARATOR);
        log.info(logBuilder);
    }

    private boolean isJsonString(String str) {
        try {
            new ObjectMapper().readTree(str);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private String formatJsonString(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (jsonString.isBlank()) {
                jsonString = "{ \"message\": \"Empty JSON\"}";
            }
            Object jsonObject = objectMapper.readValue(jsonString, Object.class);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
        } catch (IOException e) {
            log.warn("Error al formatear la cadena JSON", e);
            return jsonString;
        }
    }
}
