package de.lenicdev.hetznercloud.model.response;

import de.lenicdev.hetznercloud.model.Server;

import java.util.List;

public class GetAllServersResponse extends MetaResponse {

    private List<Server> servers;


    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

}
