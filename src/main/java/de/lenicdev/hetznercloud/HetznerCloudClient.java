package de.lenicdev.hetznercloud;

import de.lenicdev.hetznercloud.config.HetznerCloudClientConfig;
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

    private HetznerCloudClientConfig config;

    private HetznerCloudHttpClient httpClient;


    public HetznerCloudClient(String apiToken) {
        this.config = new HetznerCloudClientConfig();
        this.httpClient = new HetznerCloudHttpClient(apiToken);
    }

    public HetznerCloudClient(String apiToken, HetznerCloudClientConfig config) {
        this.config = config;
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
        final GetAllServerTypesRequest request = new GetAllServerTypesRequest(
                config.getUrlConfig().getGetAllServerTypes());
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
        if (request.getUrl() == null) {
            request.setUrl(config.getUrlConfig().getCreateServer());
        }

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
        final GetAllServersRequest request = new GetAllServersRequest(config.getUrlConfig().getGetAllServers());
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
        final GetServerRequest request = new GetServerRequest(config.getUrlConfig().getGetServer(serverId));
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
        if (request.getUrl() == null) {
            request.setUrl(config.getUrlConfig().getUpdateServer(request.getServerId()));
        }

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
        final GetAllServerActionsRequest request = new GetAllServerActionsRequest(
                config.getUrlConfig().getServerActions(serverId));
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
        final GetServerActionRequest request = new GetServerActionRequest(
                config.getUrlConfig().getServerAction(serverId, actionId));
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
        final ServerPowerOnRequest request = new ServerPowerOnRequest(config.getUrlConfig().getServerPowerOn(serverId));
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
        final ServerSoftRebootRequest request = new ServerSoftRebootRequest(
                config.getUrlConfig().getServerSoftReboot(serverId));
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
        final ServerResetRequest resetRequest = new ServerResetRequest(config.getUrlConfig().getServerReset(serverId));
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
        final ServerShutdownRequest request = new ServerShutdownRequest(
                config.getUrlConfig().getServerShutdown(serverId));
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
        final ServerPowerOffRequest request = new ServerPowerOffRequest(
                config.getUrlConfig().getServerPowerOff(serverId));
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
        final ServerResetRootPasswordRequest request = new ServerResetRootPasswordRequest(
                config.getUrlConfig().getServerResetRootPassword(serverId));
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
        if (request.getUrl() == null) {
            request.setUrl(config.getUrlConfig().getServerEnableRescueMode(request.getServerId()));
        }

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
    public Action disableRescueMode(String serverId) throws IOException, HetznerCloudException {
        final ServerDisableRescueModeRequest request = new ServerDisableRescueModeRequest(
                config.getUrlConfig().getServerDisableRescueMode(serverId));
        final ActionResponse response = httpClient.post(request, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Create an image from a server
     *
     * @apiNote Creates an image (snapshot) from a server by copying the contents of its disks. This creates a
     *          snapshot of the current state of the disk and copies it into an image. If the server is currently
     *          running you must make sure that its disk content is consistent. Otherwise, the created image
     *          may not be readable.
     *          To make sure disk content is consistent, we recommend to shut down the server prior to creating
     *          and image.
     *          You can either create a <tt>backup</tt> image that is bound to the server and therefore will be
     *          deleted when the server is deleted, or you can create an <tt>snapshot</tt> image which
     *          is completely independent of the server it was created from and will survive server deletion.
     *          Backup images are only available when the backup option is enabled for the Server.
     *          Snapshot images are billed on a per GB basis.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-create-image-from-a-server">https://docs.hetzner.cloud/#server-actions-create-image-from-a-server</a>
     * @param request All data required to create an image from a server
     * @return The created server image and the requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public ServerCreateImageResponse createServerImage(ServerCreateImageRequest request) throws IOException, HetznerCloudException {
        if (request.getUrl() == null) {
            request.setUrl(config.getUrlConfig().getServerCreateImage(request.getServerId()));
        }

        return httpClient.post(request, ServerCreateImageResponse.class);
    }

    /**
     * Rebuild a server from an image
     *
     * @apiNote Rebuilds a server overwriting its disk with the content of an image, thereby <b>destroying all data</b>
     *          on the target server.
     *          The image can either be one you have created earlier (<tt>backup</tt> or <tt>snapshot</tt> image)
     *          or it can be a completely fresh <tt>system</tt> image provided by us (Hetzner). You can get a list of all
     *          available images with <tt>GET /images</tt>.
     *          Your server will automatically be powered off before the rebuild command executes.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-rebuild-a-server-from-an-image">https://docs.hetzner.cloud/#server-actions-rebuild-a-server-from-an-image</a>
     * @param serverId The id of the server to rebuild
     * @param image The id or name of the image to rebuild the server from
     * @return The requested server action and the new root password
     * @throws IOException
     * @throws HetznerCloudException
     */
    public ServerRebuildResponse rebuildServer(String serverId, String image) throws IOException, HetznerCloudException {
        final ServerRebuildRequest request = new ServerRebuildRequest(
                config.getUrlConfig().getServerRebuild(serverId), image);
        return httpClient.post(request, ServerRebuildResponse.class);
    }

    /**
     * Change the type of a server
     *
     * @apiNote Changes the type (Cores, RAM and disk sizes) of a server.
     *          Server must be powered off for this command to succeed.
     *          This copies the content of its disk, and starts it again.
     *          You can only migrate to server types with the same <tt>storage_type</tt> and equal or bigger disks.
     *          Shrinking disks is not possible as it might destroy data.
     *          If the disk gets upgraded, the server type can not be downgraded any more. If you plan to downgrade
     *          the server type, set <tt>upgrade_disk</tt> to <code>false</code>.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-change-the-type-of-a-server">https://docs.hetzner.cloud/#server-actions-change-the-type-of-a-server</a>
     * @param request All data required to change the type of a server
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public Action changeServerType(ServerChangeTypeRequest request) throws IOException, HetznerCloudException {
        if (request.getUrl() == null) {
            request.setUrl(config.getUrlConfig().getServerChangeType(request.getServerId()));
        }

        final ActionResponse response = httpClient.post(request, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Enable backups for a server
     *
     * @apiNote Enables and configures the automatic daily backup option for the server. Enabling automatic
     *          backups will increase the price of the server by 20%. In return, you will get seven slots
     *          where images of type backup can be stored.
     *          Backups are automatically created daily.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-enable-and-configure-backups-for-a-server">https://docs.hetzner.cloud/#server-actions-enable-and-configure-backups-for-a-server</a>
     * @param serverId The id of the server to enable backups for
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public Action enableServerBackup(String serverId) throws IOException, HetznerCloudException {
        final ServerEnableBackupRequest request = new ServerEnableBackupRequest(
                config.getUrlConfig().getServerEnableBackup(serverId));
        final ActionResponse response = httpClient.post(request, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Disable backups for a server
     *
     * @apiNote Disables the automatic backup option and deletes all existing Backups for a Server. No more
     *          additional charges for backups will be made.
     *          <b>Caution: This immediately removes all existing backups for the server!</b>
     * @see <a href="https://docs.hetzner.cloud/#server-actions-disable-backups-for-a-server">https://docs.hetzner.cloud/#server-actions-disable-backups-for-a-server</a>
     * @param serverId The id of the server to disabled backups for
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public Action disableServerBackup(String serverId) throws IOException, HetznerCloudException {
        final ServerDisableBackupRequest request = new ServerDisableBackupRequest(
                config.getUrlConfig().getServerDisableBackup(serverId));
        final ActionResponse response = httpClient.post(request, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Attach an ISO to a server
     *
     * @apiNote Attaches an ISO to a server. The Server will immediately see it as a new disk. An already attached
     *          ISO will automatically be detached before the new ISO is attached.
     *          Server with attached ISOs have a modified boot order: They will try to boot from the ISO first before
     *          falling back to hard disk.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-attach-an-iso-to-a-server">https://docs.hetzner.cloud/#server-actions-attach-an-iso-to-a-server</a>
     * @param serverId The id of the server to attach the ISO to
     * @param iso ID or name of the ISO to attach to the server
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public Action attachIsoToServer(String serverId, String iso) throws IOException, HetznerCloudException {
        final ServerAttachIsoRequest request = new ServerAttachIsoRequest(
                config.getUrlConfig().getServerAttachIso(serverId), iso);
        final ActionResponse response = httpClient.post(request, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Detach an ISO from a server
     *
     * @apiNote Detaches an ISO from a server. In case no ISO image is attached to the server, the status of the
     *          returned action is immediately set to <tt>success</tt>.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-detach-an-iso-from-a-server">https://docs.hetzner.cloud/#server-actions-detach-an-iso-from-a-server</a>
     * @param serverId The id of the server to detach an ISO from
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public Action detachIsoFromServer(String serverId) throws IOException, HetznerCloudException {
        final ServerDetachIsoRequest request = new ServerDetachIsoRequest(
                config.getUrlConfig().getServerDetachIso(serverId));
        final ActionResponse response = httpClient.post(request, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Change the protection of a server
     *
     * @apiNote Changes the protection configuration of the server.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-change-protection-for-a-server">https://docs.hetzner.cloud/#server-actions-change-protection-for-a-server</a>
     * @param request The changes that should be made to the server's protection
     * @return The requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public Action changeServerProtection(ServerChangeProtectionRequest request) throws IOException, HetznerCloudException {
        if (request.getUrl() == null) {
            request.setUrl(config.getUrlConfig().getServerChangeProtection(request.getServerId()));
        }

        final ActionResponse response = httpClient.post(request, ActionResponse.class);
        return response.getAction();
    }

    /**
     * Request console access to a server
     *
     * @apiNote Requests credentials for remote access via VNC over websocket to keyboard, monitor and mouse for a
     *          server. The provided url is valid for 1 minute, after this period a new url needs to be created
     *          to connect to the server. How long the connection is open after the initial connect is not subject
     *          to this timeout.
     * @see <a href="https://docs.hetzner.cloud/#server-actions-request-console-for-a-server">https://docs.hetzner.cloud/#server-actions-request-console-for-a-server</a>
     * @param serverId The id of the server to request console access for
     * @return The wss url, the password and the requested server action
     * @throws IOException
     * @throws HetznerCloudException
     */
    public ServerConsoleResponse requestServerConsole(String serverId) throws IOException, HetznerCloudException {
        final ServerConsoleRequest request = new ServerConsoleRequest(
                config.getUrlConfig().getServerRequestConsole(serverId));
        return httpClient.post(request, ServerConsoleResponse.class);
    }

}
