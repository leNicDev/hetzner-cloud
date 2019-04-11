package de.lenicdev.hetznercloud.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ServerCreateImageRequest extends HetznerCloudRequest {

    @JsonIgnore
    private String serverId;
    private String description;
    private String type;
    private String labels;


    public ServerCreateImageRequest(String serverId, String description, String type, String labels) {
        this.serverId = serverId;
        this.description = description;
        this.type = type;
        this.labels = labels;
    }


    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

}
