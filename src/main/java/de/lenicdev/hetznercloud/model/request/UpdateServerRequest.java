package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

import java.util.Map;

public class UpdateServerRequest extends HetznerCloudRequest {

    private String name;
    private Map<String, String> labels;


    public UpdateServerRequest(String serverId) {
        super(HetznerCloudEndpoints.UPDATE_SERVER + serverId);
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
