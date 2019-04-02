package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public abstract class AbstractIpAddress {

    private String ip;
    private Boolean blocked;


    public String getIp() {
        return ip;
    }

    public Boolean getBlocked() {
        return blocked;
    }

}
