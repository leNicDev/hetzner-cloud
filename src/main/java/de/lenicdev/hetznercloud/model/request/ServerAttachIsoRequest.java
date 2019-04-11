package de.lenicdev.hetznercloud.model.request;

public class ServerAttachIsoRequest extends HetznerCloudRequest {

    /**
     * Id or name of the ISO to attach to the server as listed in <tt>GET /isos</tt>
     */
    private String iso;


    public ServerAttachIsoRequest(String url, String iso) {
        super(url);
        this.iso = iso;
    }


    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

}
