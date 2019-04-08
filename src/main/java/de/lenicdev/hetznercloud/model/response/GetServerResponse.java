package de.lenicdev.hetznercloud.model.response;

import de.lenicdev.hetznercloud.model.Server;

public class GetServerResponse extends HetznerCloudResponse {

    private Server server;


    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

}
