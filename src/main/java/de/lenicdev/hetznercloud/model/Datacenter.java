package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class Datacenter {

    private Long id;
    private String name;
    private String description;
    private Location location;
    @JsonProperty("server_types")
    private DatacenterServerTypeInfo serverTypes;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public DatacenterServerTypeInfo getServerTypes() {
        return serverTypes;
    }

}
