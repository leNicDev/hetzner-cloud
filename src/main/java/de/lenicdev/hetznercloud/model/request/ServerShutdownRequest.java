package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerShutdownRequest extends HetznerCloudRequest {

    public ServerShutdownRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_SHUTDOWN.replace("{serverId}", serverId));
    }

}
