package dev.abarcan.cachingservice.config.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {

        logRequest(request, body);
        long startTime = System.currentTimeMillis();
        ClientHttpResponse response = execution.execute(request, body);
        long elapsedTime = System.currentTimeMillis() - startTime;
        return logResponse(response, elapsedTime);
    }

    private void logRequest(HttpRequest request, byte[] body) {

        if (log.isDebugEnabled()) {
            log.debug("Request {} to {} with headers {}", request.getMethod(), request.getURI(), request.getHeaders());
            String requestBody = new String(body, StandardCharsets.UTF_8);
            if (!ObjectUtils.isEmpty(requestBody)) {
                log.debug("Request body: {}", requestBody);
            }
        }
    }

    private ClientHttpResponse logResponse(ClientHttpResponse response, long elapsedTime) throws IOException {

        if (!log.isDebugEnabled()) {
            return response;
        }

        final ClientHttpResponse responseWrapper = new BufferingClientHttpResponseWrapper(response);
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(responseWrapper.getBody()));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        log.debug("Request execution time: {} ms", elapsedTime);
        log.debug("Response {} with headers {}", responseWrapper.getStatusCode(), responseWrapper.getHeaders());
        if (!ObjectUtils.isEmpty(inputStringBuilder)) {
            log.debug("Response body: {}", inputStringBuilder);

        }
        return responseWrapper;
    }
}
