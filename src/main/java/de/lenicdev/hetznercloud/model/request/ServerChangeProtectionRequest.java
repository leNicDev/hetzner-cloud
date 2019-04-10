package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerChangeProtectionRequest extends HetznerCloudRequest {

    private Boolean delete;
    private Boolean rebuild;


    public ServerChangeProtectionRequest(String serverId, Boolean delete, Boolean rebuild) {
        super(HetznerCloudEndpoints.SERVER_CHANGE_PROTECTION.replace("{serverId}", serverId));
        this.delete = delete;
        this.rebuild = rebuild;
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
