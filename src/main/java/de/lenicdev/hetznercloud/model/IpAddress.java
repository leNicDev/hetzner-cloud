package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class IpAddress extends AbstractIpAddress {

    @JsonProperty("dns_ptr")
    private String dnsPtr;


    public String getDnsPtr() {
        return dnsPtr;
    }

}
