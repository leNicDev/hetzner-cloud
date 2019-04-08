package de.lenicdev.hetznercloud;

import de.lenicdev.hetznercloud.model.Server;
import de.lenicdev.hetznercloud.model.ServerType;
import de.lenicdev.hetznercloud.model.exception.HetznerCloudException;
import de.lenicdev.hetznercloud.model.request.*;
import de.lenicdev.hetznercloud.model.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Client for the Hetzner Cloud API v1
 *
 * @author leNicDev
 */
public class HetznerCloudClient {

    private static final Logger LOG = LoggerFactory.getLogger(HetznerCloudClient.class);

    private HetznerCloudHttpClient httpClient;


    public HetznerCloudClient(String apiToken) {
        this.httpClient = new HetznerCloudHttpClient(apiToken);
    }


    /**
     * Get all available server types
     * @return List of all available server types
     * @throws IOException
     */
    public List<ServerType> getAllServerTypes() throws IOException, HetznerCloudException {
        final GetAllServerTypesRequest request = new GetAllServerTypesRequest();
        final GetAllServerTypesResponse response = httpClient.get(request, GetAllServerTypesResponse.class);
        return response.getServerTypes();
    }

    /**
     * Create a new server
     * @param request All information needed to create a new server
     * @return Information about the created server and it's components
     * @throws IOException
     */
    public CreateServerResponse createServer(CreateServerRequest request) throws IOException, HetznerCloudException {
        return httpClient.post(request, CreateServerResponse.class);
    }

    /**
     * Get all servers
     * @return A list of all servers
     * @throws IOException
     */
    public List<Server> getAllServers() throws IOException, HetznerCloudException {
        final GetAllServersRequest request = new GetAllServersRequest();
        final GetAllServersResponse response = httpClient.get(request, GetAllServersResponse.class);
        return response.getServers();
    }

    /**
     * Get a server by it's ID
     * @param serverId The server ID
     * @return The server associated with the given ID
     */
    public Server getServer(String serverId) throws IOException, HetznerCloudException {
        final GetServerRequest request = new GetServerRequest(serverId);
        final GetServerResponse response = httpClient.get(request, GetServerResponse.class);
        return response.getServer();
    }

    /**
     * Update a server
     * @param request The data to update
     * @return The updated server
     */
    public Server updateServer(UpdateServerRequest request) throws IOException, HetznerCloudException {
        final UpdateServerResponse response = httpClient.put(request, UpdateServerResponse.class);
        return response.getServer();
    }

}
