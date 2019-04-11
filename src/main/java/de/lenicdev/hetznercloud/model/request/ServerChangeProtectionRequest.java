package de.lenicdev.hetznercloud.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ServerChangeProtectionRequest extends HetznerCloudRequest {

    @JsonIgnore
    private String serverId;
    private Boolean delete;
    private Boolean rebuild;


    public ServerChangeProtectionRequest(String serverId, Boolean delete, Boolean rebuild) {
        this.serverId = serverId;
        this.delete = delete;
        this.rebuild = rebuild;
    }


    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public Boolean getRebuild() {
        return rebuild;
    }

    public void setRebuild(Boolean rebuild) {
        this.rebuild = rebuild;
    }

}
