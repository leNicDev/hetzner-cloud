package de.lenicdev.hetznercloud.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.lenicdev.hetznercloud.model.Image;

public class ServerRebuildResponse extends ActionResponse {

    private Image image;
    @JsonProperty("root_password")
    private String rootPassword;


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getRootPassword() {
        return rootPassword;
    }

    public void setRootPassword(String rootPassword) {
        this.rootPassword = rootPassword;
    }

}
