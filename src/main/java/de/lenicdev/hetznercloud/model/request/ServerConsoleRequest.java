package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerConsoleRequest extends HetznerCloudRequest {

    public ServerConsoleRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_REQUEST_CONSOLE.replace("{serverId}", serverId));
    }

}
