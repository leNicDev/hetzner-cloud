package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class Ipv6Address extends AbstractIpAddress {

    @JsonProperty("dns_ptr")
    private List<IpAddress> dnsPtr;


    public List<IpAddress> getDnsPtr() {
        return dnsPtr;
    }

}
