package de.lenicdev.hetznercloud.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class HetznerCloudRequest {

    @JsonIgnore
    private String url;


    public HetznerCloudRequest() {
    }

    public HetznerCloudRequest(String url) {
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
