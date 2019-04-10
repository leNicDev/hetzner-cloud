package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerResetRootPasswordRequest extends HetznerCloudRequest {

    public ServerResetRootPasswordRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_RESET_ROOT_PASSWORD.replace("{serverId}", serverId));
    }

}
