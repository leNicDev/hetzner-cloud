package de.lenicdev.hetznercloud.model.request;

import de.lenicdev.hetznercloud.constant.HetznerCloudEndpoints;

public class ServerAttachIsoRequest extends HetznerCloudRequest {

    /**
     * Id or name of the ISO to attach to the server as listed in <tt>GET /isos</tt>
     */
    private String iso;


    public ServerAttachIsoRequest(String serverId, String iso) {
        super(HetznerCloudEndpoints.SERVER_ATTACH_ISO.replace("{serverId}", serverId));
        this.iso = iso;
    }


    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

}
