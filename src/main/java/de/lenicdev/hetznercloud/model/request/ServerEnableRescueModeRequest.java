package de.lenicdev.hetznercloud.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ServerEnableRescueModeRequest extends HetznerCloudRequest {

    @JsonIgnore
    private String serverId;
    private String type;
    @JsonProperty("ssh_keys")
    private List<Long> sshKeys;


    public ServerEnableRescueModeRequest(String serverId, String type) {
        this.serverId = serverId;
        this.type = type;
    }

    public ServerEnableRescueModeRequest(String serverId, List<Long> sshKeys) {
        this.serverId = serverId;
        this.sshKeys = sshKeys;
    }

    public ServerEnableRescueModeRequest(String serverId, String type, List<Long> sshKeys) {
        this.serverId = serverId;
        this.type = type;
        this.sshKeys = sshKeys;
    }


    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Long> getSshKeys() {
        return sshKeys;
    }

    public void setSshKeys(List<Long> sshKeys) {
        this.sshKeys = sshKeys;
    }

}
