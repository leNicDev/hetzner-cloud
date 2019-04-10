package de.lenicdev.hetznercloud.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

import java.util.List;

public class ServerEnableRescueModeRequest extends HetznerCloudRequest {

    private String type;
    @JsonProperty("ssh_keys")
    private List<Long> sshKeys;


    public ServerEnableRescueModeRequest(String serverId) {
        super(HetznerCloudEndpoints.SERVER_ENABLE_RESCUE_MODE.replace("{serverId}", serverId));
    }

    public ServerEnableRescueModeRequest(String serverId, String type) {
        this(serverId);
        this.type = type;
    }

    public ServerEnableRescueModeRequest(String serverId, List<Long> sshKeys) {
        this(serverId);
        this.sshKeys = sshKeys;
    }

    public ServerEnableRescueModeRequest(String serverId, String type, List<Long> sshKeys) {
        this(serverId);
        this.type = type;
        this.sshKeys = sshKeys;
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
