package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class GetServerActionRequest extends HetznerCloudRequest {

    public GetServerActionRequest(String serverId, String actionId) {
        super(HetznerCloudEndpoints.SERVER_ACTION
                .replace("{serverId}", serverId)
                .replace("{actionId}", actionId));
    }

}
