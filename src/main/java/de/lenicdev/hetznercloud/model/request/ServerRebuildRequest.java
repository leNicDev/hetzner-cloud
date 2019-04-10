package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerRebuildRequest extends HetznerCloudRequest {

    private String image;


    public ServerRebuildRequest(String serverId, String image) {
        super(HetznerCloudEndpoints.SERVER_REBUILD.replace("{serverId}", serverId));
        this.image = image;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
