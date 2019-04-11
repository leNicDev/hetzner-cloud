package de.lenicdev.hetznercloud.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerChangeTypeRequest extends HetznerCloudRequest {

    @JsonIgnore
    private String serverId;

    /**
     * Whether the disk should be upgraded to the disk of the new server type.
     * Set this to <code>false</code> if you're planning to downgrade the server as once a disk has been upgraded,
     * it can't be downgraded anymore.
     */
    @JsonProperty("upgrade_disk")
    private Boolean upgradeDisk;

    /**
     * The new type of the server to upgrade to.
     */
    @JsonProperty("server_type")
    private String serverType;


    public ServerChangeTypeRequest(String serverId, Boolean upgradeDisk, String serverType) {
        this.serverId = serverId;
        this.upgradeDisk = upgradeDisk;
        this.serverType = serverType;
    }


    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public Boolean getUpgradeDisk() {
        return upgradeDisk;
    }

    public void setUpgradeDisk(Boolean upgradeDisk) {
        this.upgradeDisk = upgradeDisk;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

}
