package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class Network {

    private IpAddress ipv4;
    private Ipv6Address ipv6;
    @JsonProperty("floating_ips")
    private List<String> floatingIps;


    public IpAddress getIpv4() {
        return ipv4;
    }

    public Ipv6Address getIpv6() {
        return ipv6;
    }

    public List<String> getFloatingIps() {
        return floatingIps;
    }

}
