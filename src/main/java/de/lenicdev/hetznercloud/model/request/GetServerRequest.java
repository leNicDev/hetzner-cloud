package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class GetServerRequest extends HetznerCloudRequest {

    public GetServerRequest(String serverId) {
        super(HetznerCloudEndpoints.GET_SERVER + serverId);
    }

}
