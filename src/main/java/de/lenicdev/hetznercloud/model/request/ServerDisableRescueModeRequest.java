package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerDisableRescueModeRequest extends HetznerCloudRequest {

    public ServerDisableRescueModeRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_DISABLE_RESCUE_MODE.replace("{serverId}", serverId));
    }

}
