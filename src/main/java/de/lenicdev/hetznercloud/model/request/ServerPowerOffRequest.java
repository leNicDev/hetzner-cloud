package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerPowerOffRequest extends HetznerCloudRequest {

    public ServerPowerOffRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_POWER_OFF.replace("{serverId}", serverId));
    }

}
