package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class GetAllServerActionsRequest extends HetznerCloudRequest {

    public GetAllServerActionsRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_ACTIONS.replace("{serverId}", serverId));
    }

}
