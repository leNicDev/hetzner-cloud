package de.lenicdev.hetznercloud.model.response;

import de.lenicdev.hetznercloud.model.ResponseMeta;

public abstract class MetaResponse extends HetznerCloudResponse {

    private ResponseMeta meta;


    public ResponseMeta getMeta() {
        return meta;
    }

    public void setMeta(ResponseMeta meta) {
        this.meta = meta;
    }

}
