package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerDetachIsoRequest extends HetznerCloudRequest {

    public ServerDetachIsoRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_DETACH_ISO.replace("{serverId}", serverId));
    }

}
