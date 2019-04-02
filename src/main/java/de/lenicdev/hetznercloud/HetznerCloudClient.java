package de.lenicdev.hetznercloud;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;
import de.lenicdev.hetznercloud.model.ServerType;
import de.lenicdev.hetznercloud.model.exception.HetznerAuthenticationException;
import de.lenicdev.hetznercloud.model.exception.HetznerCloudException;
import de.lenicdev.hetznercloud.model.request.CreateServerRequest;
import de.lenicdev.hetznercloud.model.response.CreateServerResponse;
import de.lenicdev.hetznercloud.model.response.ErrorResponse;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Client for the Hetzner Cloud API v1
 *
 * @author leNicDev
 */
public class HetznerCloudClient {

    private static final Logger LOG = LoggerFactory.getLogger(HetznerCloudClient.class);

    private static final MediaType REQUEST_MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");

    private String apiToken;

    private OkHttpClient httpClient;
    private ObjectMapper objectMapper;


    public HetznerCloudClient(String apiToken) {
        this.apiToken = apiToken;

        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }


    /**
     * Get all available server types
     * @return List of all available server types
     * @throws IOException
     */
    public List<ServerType> getAllServerTypes() throws IOException {
        // Build http request
        final Request httpRequest = new Request.Builder()
                .url(HetznerCloudEndpoints.GET_ALL_SERVER_TYPES)
                .header("Authorization", "Bearer " + apiToken)
                .get()
                .build();

        try (Response httpResponse = httpClient.newCall(httpRequest).execute()) {
            // Response body is null -> Return empty list (no results)
            if (httpResponse.body() == null) {
                return new ArrayList<>();
            }

            // Get response body as string
            final String body = httpResponse.body().string();

            LOG.debug("getAllServerTypes response: {}", body);

            // Parse response body
            final ServerType[] serverTypes = objectMapper.readValue(body, ServerType[].class);

            // Return server types
            return Arrays.asList(serverTypes);
        }
    }

    /**
     * Create a new server
     * @param request All information needed to create a new server
     * @return Information about the created server and it's components
     * @throws IOException
     */
    public CreateServerResponse createServer(CreateServerRequest request) throws IOException, HetznerCloudException {
        final String requestBody = objectMapper.writeValueAsString(request);
        final RequestBody httpRequestBody = RequestBody.create(REQUEST_MEDIA_TYPE, requestBody);

        // Build http request
        final Request httpRequest = new Request.Builder()
                .url(HetznerCloudEndpoints.CREATE_SERVER)
                .header("Authorization", "Bearer " + apiToken)
                .post(httpRequestBody)
                .build();

        try (Response httpResponse = httpClient.newCall(httpRequest).execute()) {
            // Response body is null -> Return null
            if (httpResponse.body() == null) {
                return null;
            }

            LOG.info("Response status {}", httpResponse.code());

            // Request failed (status not equals 200)
            if (httpResponse.code() < 200 || httpResponse.code() >= 300) {
                handleErrorResponse(httpResponse);
                return null;
            }

            // Get response body as string
            final String body = httpResponse.body().string();

            LOG.debug("createServer response: {}", body);

            // Parse response body
            return objectMapper.readValue(body, CreateServerResponse.class);
        }
    }

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
