package de.lenicdev.hetznercloud.model.response;

import de.lenicdev.hetznercloud.model.Server;

public class UpdateServerResponse extends HetznerCloudResponse {

    private Server server;


    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

}
