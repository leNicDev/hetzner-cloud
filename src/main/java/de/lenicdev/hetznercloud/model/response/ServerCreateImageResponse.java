package de.lenicdev.hetznercloud.model.response;

import de.lenicdev.hetznercloud.model.Image;

public class ServerCreateImageResponse extends ActionResponse {

    private Image image;


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
