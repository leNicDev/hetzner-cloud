package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class GetAllServersRequest extends HetznerCloudRequest {

    public GetAllServersRequest() {
        super(HetznerCloudEndpoints.GET_ALL_SERVERS);
    }

}
