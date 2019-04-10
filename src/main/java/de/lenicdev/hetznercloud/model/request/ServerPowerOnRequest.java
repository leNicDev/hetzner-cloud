package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerPowerOnRequest extends HetznerCloudRequest {

    public ServerPowerOnRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_POWER_ON.replace("{serverId}", serverId));
    }

}
