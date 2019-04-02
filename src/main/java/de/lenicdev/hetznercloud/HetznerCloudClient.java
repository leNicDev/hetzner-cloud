package de.lenicdev.hetznercloud;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;
import de.lenicdev.hetznercloud.model.ServerType;
import de.lenicdev.hetznercloud.model.request.CreateServerRequest;
import de.lenicdev.hetznercloud.model.response.CreateServerResponse;
import okhttp3.*;

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

    private static final MediaType REQUEST_MEDIA_TYPE = MediaType.get("application/json; charset=utf-8");

    private String apiToken;

    private OkHttpClient httpClient;
    private ObjectMapper objectMapper;


    public HetznerCloudClient(String apiToken) {
        this.apiToken = apiToken;

        this.httpClient = new OkHttpClient();
        this.objectMapper = new ObjectMapper()
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
                .get()
                .build();

        try (Response httpResponse = httpClient.newCall(httpRequest).execute()) {
            // Response body is null -> Return empty list (no results)
            if (httpResponse.body() == null) {
                return new ArrayList<>();
            }

            // Get response body as string
            final String body = httpResponse.body().string();

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
    public CreateServerResponse createServer(CreateServerRequest request) throws IOException {
        final String requestBody = objectMapper.writeValueAsString(request);
        final RequestBody httpRequestBody = RequestBody.create(REQUEST_MEDIA_TYPE, requestBody);

        // Build http request
        final Request httpRequest = new Request.Builder()
                .url(HetznerCloudEndpoints.CREATE_SERVER)
                .post(httpRequestBody)
                .build();

        try (Response httpResponse = httpClient.newCall(httpRequest).execute()) {
            // Response body is null -> Return null
            if (httpResponse.body() == null) {
                return null;
            }

            // Get response body as string
            final String body = httpResponse.body().string();

            // Parse response body
            final CreateServerResponse response = objectMapper.readValue(body, CreateServerResponse.class);

            // Return response
            return response;
        }
    }

}
