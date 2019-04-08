package de.lenicdev.hetznercloud.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.lenicdev.hetznercloud.model.ServerType;

import java.util.List;

public class GetAllServerTypesResponse extends HetznerCloudResponse {

    @JsonProperty("server_types")
    private List<ServerType> serverTypes;


    public List<ServerType> getServerTypes() {
        return serverTypes;
    }

    public void setServerTypes(List<ServerType> serverTypes) {
        this.serverTypes = serverTypes;
    }

}
