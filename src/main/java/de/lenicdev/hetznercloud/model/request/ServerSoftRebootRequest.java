package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerSoftRebootRequest extends HetznerCloudRequest {

    public ServerSoftRebootRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_SOFT_REBOOT.replace("{serverId}", serverId));
    }

}
