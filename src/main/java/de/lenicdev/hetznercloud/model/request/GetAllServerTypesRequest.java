package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class GetAllServerTypesRequest extends HetznerCloudRequest {

    public GetAllServerTypesRequest() {
        super(HetznerCloudEndpoints.GET_ALL_SERVER_TYPES);
    }

}
