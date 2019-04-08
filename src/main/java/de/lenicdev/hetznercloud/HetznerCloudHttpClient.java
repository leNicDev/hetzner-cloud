package de.lenicdev.hetznercloud;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import de.lenicdev.hetznercloud.model.exception.HetznerAuthenticationException;
import de.lenicdev.hetznercloud.model.exception.HetznerCloudException;
import de.lenicdev.hetznercloud.model.request.HetznerCloudRequest;
import de.lenicdev.hetznercloud.model.response.ErrorResponse;
import de.lenicdev.hetznercloud.model.response.HetznerCloudResponse;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HetznerCloudHttpClient {

    private static final Logger LOG = LoggerFactory.getLogger(HetznerCloudHttpClient.class);

    private static final MediaType REQUEST_MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");

    private String apiToken;

    private OkHttpClient httpClient;
    private ObjectMapper objectMapper;


    public HetznerCloudHttpClient(String apiToken) {
        // Set the api token
        this.apiToken = apiToken;

        // Initialize HTTP client
        this.httpClient = new OkHttpClient();

        // Initialize Jackson object mapper
        this.objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }


    public <T extends HetznerCloudResponse> T get(HetznerCloudRequest request, Class<T> targetType) throws IOException, HetznerCloudException {
        // Build http request
        final Request httpRequest = new Request.Builder()
                .url(request.getUrl())
                .header("Authorization", "Bearer " + apiToken)
                .get()
                .build();

        try (Response httpResponse = httpClient.newCall(httpRequest).execute()) {
            // Get response body as string
            final String body = getResponseBodyAsString(httpResponse);

            // Parse response body
            return objectMapper.readValue(body, targetType);
        }
    }

    public <T extends HetznerCloudResponse> T post(HetznerCloudRequest request, Class<T> targetType) throws IOException, HetznerCloudException {
        // Build request body
        final String requestBodyJson = objectMapper.writeValueAsString(request);
        final RequestBody requestBody = RequestBody.create(REQUEST_MEDIA_TYPE, requestBodyJson);

        // Build http request
        final Request httpRequest = new Request.Builder()
                .url(request.getUrl())
                .header("Authorization", "Bearer " + apiToken)
                .post(requestBody)
                .build();

        try (Response httpResponse = httpClient.newCall(httpRequest).execute()) {
            // Get response body as string
            final String body = getResponseBodyAsString(httpResponse);

            // Parse response body
            return objectMapper.readValue(body, targetType);
        }
    }

    public <T extends HetznerCloudResponse> T put(HetznerCloudRequest request, Class<T> targetType) throws IOException, HetznerCloudException {
        // Build request body
        final String requestBodyJson = objectMapper.writeValueAsString(request);
        final RequestBody requestBody = RequestBody.create(REQUEST_MEDIA_TYPE, requestBodyJson);

        // Build http request
        final Request httpRequest = new Request.Builder()
                .url(request.getUrl())
                .header("Authorization", "Bearer " + apiToken)
                .put(requestBody)
                .build();

        try (Response httpResponse = httpClient.newCall(httpRequest).execute()) {
            // Get response body as string
            final String body = getResponseBodyAsString(httpResponse);

            // Parse response body
            return objectMapper.readValue(body, targetType);
        }
    }

    public <T extends HetznerCloudResponse> T delete(HetznerCloudRequest request, Class<T> targetType) throws IOException, HetznerCloudException {
        // Build request body
        final String requestBodyJson = objectMapper.writeValueAsString(request);
        final RequestBody requestBody = RequestBody.create(REQUEST_MEDIA_TYPE, requestBodyJson);

        // Build http request
        final Request httpRequest = new Request.Builder()
                .url(request.getUrl())
                .header("Authorization", "Bearer " + apiToken)
                .delete(requestBody)
                .build();

        try (Response httpResponse = httpClient.newCall(httpRequest).execute()) {
            // Get response body as string
            final String body = getResponseBodyAsString(httpResponse);

            // Parse response body
            return objectMapper.readValue(body, targetType);
        }
    }


    /**
     * Get the response body as string and handle possible errors
     * @param httpResponse The http response to read
     * @return The response body as string
     * @throws HetznerCloudException
     * @throws IOException Is thrown when the response body could not be read
     */
    private String getResponseBodyAsString(Response httpResponse) throws HetznerCloudException, IOException {
        // Handle error
        if (httpResponse.code() < 200 || httpResponse.code() >= 300) {
            handleErrorResponse(httpResponse);
            return null;
        }

        // Response body is null -> Return empty list (no results)
        if (httpResponse.body() == null) {
            return null;
        }

        // Get response body as string
        return httpResponse.body().string();
    }

    /**
     * Handle an error response with status < 200 or >= 300
     * @param httpResponse The http error response to handle
     * @throws HetznerCloudException
     */
    private void handleErrorResponse(Response httpResponse) throws HetznerCloudException {
        // No body (no information about the error)
        if (httpResponse.body() == null) {
            throw new HetznerCloudException("An internal error occurred and the Hetzner cloud API did not provide any information.", "internal_error", null);
        }

        // Get response body as string
        final String body;
        try {
            body = httpResponse.body().string();
        } catch (IOException e) {
            throw new HetznerCloudException("An internal error occurred while reading the response body.", "internal_error", null);
        }

        final ErrorResponse response;
        try {
            response = objectMapper.readValue(body, ErrorResponse.class);
        } catch (IOException e) {
            throw new HetznerCloudException(e.getMessage(), "internal_error", body);
        }

        // Authentication error
        if (httpResponse.code() >= 400 && httpResponse.code() < 500) {
            throw new HetznerAuthenticationException(
                    response.getError().getMessage(), response.getError().getCode(), response.getError().getDetails());
        } else {
            throw new HetznerCloudException(
                    response.getError().getMessage(), response.getError().getCode(), response.getError().getDetails());
        }
    }

}
