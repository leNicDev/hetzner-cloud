package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerResetRequest extends HetznerCloudRequest {

    public ServerResetRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_RESET.replace("{serverId}", serverId));
    }

}
