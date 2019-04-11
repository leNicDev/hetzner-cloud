package de.lenicdev.hetznercloud.model.request;

public class ServerRebuildRequest extends HetznerCloudRequest {

    private String image;


    public ServerRebuildRequest(String url, String image) {
        super(url);
        this.image = image;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
