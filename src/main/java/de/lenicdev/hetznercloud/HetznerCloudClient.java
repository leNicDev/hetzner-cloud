package de.lenicdev.hetznercloud;

import de.lenicdev.hetznercloud.model.Action;
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
     *
     * @apiNote Gets all server type objects.
     * @see <a href="https://docs.hetzner.cloud/#server-types-get-all-server-types">https://docs.hetzner.cloud/#server-types-get-all-server-types</a>
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
     *
     * @apiNote Creates a new server. Returns preliminary information about the server as well as an action
     *          that covers progress of creation.
     * @see <a href="https://docs.hetzner.cloud/#servers-create-a-server">https://docs.hetzner.cloud/#servers-create-a-server</a>
     * @param request All information needed to create a new server
     * @return Information about the created server and it's components
     * @throws IOException
     */
    public CreateServerResponse createServer(CreateServerRequest request) throws IOException, HetznerCloudException {
        return httpClient.post(request, CreateServerResponse.class);
    }

    /**
     * Get all servers
     *
     * @apiNote Returns all existing server objects.
     * @see <a href="https://docs.hetzner.cloud/#servers-get-all-servers">https://docs.hetzner.cloud/#servers-get-all-servers</a>
     * @return A list of all servers
     * @throws IOException
     */
    public List<Server> getAllServers() throws IOException, HetznerCloudException {
        final GetAllServersRequest request = new GetAllServersRequest();
        final GetAllServersResponse response = httpClient.get(request, GetAllServersResponse.class);
        return response.getServers();
    }

    /**
     * Get a server by its ID
     *
     * @apiNote Returns a specific server object. The server must exist inside the project.
     * @see <a href="https://docs.hetzner.cloud/#servers-get-a-server">https://docs.hetzner.cloud/#servers-get-a-server</a>
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
     *
     * @apiNote Updates a server. You can update a server's name and a server's labels.
     *          Please not that server names must be unique per project and valid hostnames
     *          as per RFC 1123 (i.e. may only contain letters, digits, periods and dashes).
     *          Also note that when updating labels, the server's current set of labels
     *          will be replaced with the labels provided in the request body. So, for
     *          example, if you want to add a new label, you have to provide, all
     *          existing labels plus the new label in the request body.
     * @see <a href="https://docs.hetzner.cloud/#servers-update-a-server">https://docs.hetzner.cloud/#servers-update-a-server</a>
     * @param request The data to update
     * @return The updated server
     */
    public Server updateServer(UpdateServerRequest request) throws IOException, HetznerCloudException {
        final UpdateServerResponse response = httpClient.put(request, UpdateServerResponse.class);
        return response.getServer();
    }

    /**
     * Get a list of all actions available for a server
     *
     * @apiNote Returns all action objects for a server.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-get-all-actions-for-a-server">https://docs.hetzner.cloud/#server-actions-get-all-actions-for-a-server</a>
     * @param serverId The server id
     * @return List of all available actions
     * @throws IOException
     * @throws HetznerCloudException
     */
    public List<Action> getAllServerActions(String serverId) throws IOException, HetznerCloudException {
        final GetAllServerActionsRequest request = new GetAllServerActionsRequest(serverId);
        final GetAllServerActionsResponse response = httpClient.get(request, GetAllServerActionsResponse.class);
        return response.getActions();
    }

    /**
     * Get a specific action of a specific server
     *
     * @apiNote Returns a specific action object for a Server.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-get-a-specific-action-for-a-server">https://docs.hetzner.cloud/#server-actions-get-a-specific-action-for-a-server</a>
     * @param serverId The server id
     * @param actionId The action id
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public Action getServerAction(String serverId, String actionId) throws IOException, HetznerCloudException {
        final GetServerActionRequest request = new GetServerActionRequest(serverId, actionId);
        final ActionResponse response = httpClient.get(request, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Power on a server
     *
     * @apiNote Starts a server by turning its power on
     * @see <a href="https://docs.hetzner.cloud/#server-actions-power-on-a-server">https://docs.hetzner.cloud/#server-actions-power-on-a-server</a>
     * @param serverId The id of the server to power on
     * @return The power on action
     */
    public Action powerOnServer(String serverId) throws IOException, HetznerCloudException {
        final ServerPowerOnRequest request = new ServerPowerOnRequest(serverId);
        final ActionResponse response = httpClient.post(request, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Reboot a server (soft)
     *
     * @apiNote Reboots a server gracefully by sending an ACPI request. The server
     *          operating system must support ACPI and react to the request,
     *          otherwise the server will not reboot.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-soft-reboot-a-server">https://docs.hetzner.cloud/#server-actions-soft-reboot-a-server</a>
     * @param serverId The id of the server to reboot
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public Action rebootServer(String serverId) throws IOException, HetznerCloudException {
        final ServerSoftRebootRequest request = new ServerSoftRebootRequest(serverId);
        final ActionResponse response = httpClient.post(request, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Reset a server (hard)
     *
     * @apiNote Cuts power to a server and starts it again. This forcefully stops
     *          it without giving the server operating system time to gracefully stop.
     *          This may lead to data loss, it's equivalent to pulling the power cord
     *          and plugging it in again. Reset should be used when reboot does not work.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-reset-a-server">https://docs.hetzner.cloud/#server-actions-reset-a-server</a>
     * @param serverId The id of the server to reset
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public Action resetServer(String serverId) throws IOException, HetznerCloudException {
        final ServerResetRequest resetRequest = new ServerResetRequest(serverId);
        final ActionResponse response = httpClient.post(resetRequest, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Shutdown a server
     *
     * @apiNote Shuts down a server gracefully by sending an ACPI shutdown request.
     *          The server operating system must support ACPI and react to the request,
     *          otherwise the server will not shutdown.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-shutdown-a-server">https://docs.hetzner.cloud/#server-actions-shutdown-a-server</a>
     * @param serverId The id of the server to shut down
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public Action shutdownServer(String serverId) throws IOException, HetznerCloudException {
        final ServerShutdownRequest request = new ServerShutdownRequest(serverId);
        final ActionResponse response = httpClient.post(request, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Power off a server
     *
     * @apiNote This forcefully stops it without giving the server operating system
     *          time to gracefully stop. May lead to data loss, equivalent to pulling the power cord.
     *          Power off should only be used when shutdown does not work.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-power-off-a-server">https://docs.hetzner.cloud/#server-actions-power-off-a-server</a>
     * @param serverId The id of the server to power off
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public Action powerOffServer(String serverId) throws IOException, HetznerCloudException {
        final ServerPowerOffRequest request = new ServerPowerOffRequest(serverId);
        final ActionResponse response = httpClient.post(request, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Reset a server's root password
     *
     * @apiNote Resets the root password. Only works for Linux system that are running the qemu guest agent.
     *          Server must be powered on (state <code>on</code>) in order for this operation to succeed.
     *          This will generate a new password for this server and return it.
     *          If this does not succeed you can use the rescue system to netboot the server and manually
     *          change your server password by hand.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-reset-root-password-of-a-server">https://docs.hetzner.cloud/#server-actions-reset-root-password-of-a-server</a>
     * @param serverId The id of the server to reset the root password of
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public ServerResetRootPasswordResponse resetServerRootPassword(String serverId) throws IOException, HetznerCloudException {
        final ServerResetRootPasswordRequest request = new ServerResetRootPasswordRequest(serverId);
        return httpClient.post(request, ServerResetRootPasswordResponse.class);
    }

    /**
     * Enable rescue mode for a server
     *
     * @apiNote Enable the Hetzner Rescue System for this server. The next time a Server with enabled rescue mode
     *          boots it will start a special minimal Linux distribution designed for repair and reinstall.
     *          In case a server cannot boot on its own you can use this to access a server's disks.
     *          Rescue Mode is automatically disabled when you first boot into it or if you do not use it
     *          for 60 minutes. Enabled rescue mode will not reboot your server - you will have to do this yourself.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-enable-rescue-mode-for-a-server">https://docs.hetzner.cloud/#server-actions-enable-rescue-mode-for-a-server</a>
     * @param request All data required to enable rescue mode for a server
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public ServerEnableRescueModeResponse enableRescueMode(ServerEnableRescueModeRequest request) throws IOException, HetznerCloudException {
        return httpClient.post(request, ServerEnableRescueModeResponse.class);
    }

    /**
     * Disable rescue mode for a server
     *
     * @apiNote Disables the Hetzner Rescue System for a server. This makes a server start from its disks on next
     *          reboot. Rescue Mode is automatically disabled when you first boot into it or if you do not use it
     *          for 60 minutes. Disabling rescue mode will not reboot your server - your will have to do this yourself.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-disable-rescue-mode-for-a-server">https://docs.hetzner.cloud/#server-actions-disable-rescue-mode-for-a-server</a>
     * @param serverId The id of the server to disable rescue mode for
     * @return The requested server action
     */
    public Action disabledRescueMode(String serverId) throws IOException, HetznerCloudException {
        final ServerDisableRescueModeRequest request = new ServerDisableRescueModeRequest(serverId);
        final ActionResponse response = httpClient.post(request, ActionResponse.class);
        return response.getAction();
    }

}
