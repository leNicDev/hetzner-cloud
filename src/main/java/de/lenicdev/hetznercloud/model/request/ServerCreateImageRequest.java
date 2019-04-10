package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerCreateImageRequest extends HetznerCloudRequest {

    private String description;
    private String type;
    private String labels;


    public ServerCreateImageRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_CREATE_IMAGE.replace("{serverId}", serverId));
    }

    public ServerCreateImageRequest(String serverId, String description, String type, String labels) {
        this(serverId);
        this.description = description;
        this.type = type;
        this.labels = labels;
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
