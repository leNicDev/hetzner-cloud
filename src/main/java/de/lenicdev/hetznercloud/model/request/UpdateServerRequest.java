package de.lenicdev.hetznercloud.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Map;

public class UpdateServerRequest extends HetznerCloudRequest {

    @JsonIgnore
    private String serverId;
    private String name;
    private Map<String, String> labels;


    public UpdateServerRequest(String serverId) {
        this.serverId = serverId;
    }


    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

}
